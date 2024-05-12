package com.yarlnet.BankServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class AccessServer extends Thread {
  Socket clientConnection = null;
  BufferedReader inClient = null;
  PrintStream outClient = null;

  Thread thClient = null;
  StringTokenizer values;
  String strName = new String(" ");
  String strAcctNo = new String(" ");
  String strAction = new String(" ");
  Server server;

  public AccessServer(Server temp) {
    server = temp;
  }

  public void connectClient(Socket clientConnection) {
    this.clientConnection = clientConnection;
    try {
      inClient = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
      outClient = new PrintStream(clientConnection.getOutputStream());
    } catch (IOException e) {
      System.err.println("connectClient -> " + e);
    }

    thClient = new Thread(this);
    thClient.start();

    System.out.println("Starting access...");
  }

  public void run() {
    Thread thisThread = Thread.currentThread();
    while (thClient == thisThread) {
      String inputLine = null;
      try {
        inputLine = inClient.readLine();
      } catch (IOException ioe) {
        if (thClient != null) {
          System.err.println("inClient.readLine() -> " + ioe);
        }
      }
      if (inputLine != null)
        clientTalks(inputLine);
    }
  }

  public void clientTalks(String msg) {

    if (thClient == null) {
      System.out.println("CLIENT (ignored ): " + msg);
      return;
    }

    System.out.println("CLIENT: " + msg);

    if (msg.equals("Hello_Server")) {
      sendToClient("Welcome_Client");
    } else if (msg.equals(" ")) {

    } else {
      String message = msg;
      values = new StringTokenizer(message, ".");
      String cmd = values.nextToken();
      if (cmd.equals("LOGIN")) {

        String AcctNo1 = new String();
        String AcctNo2 = new String();
        String Name1 = new String();
        String Name2 = new String();
        String pWord1 = new String();
        String pWord2 = new String();

        while (values.hasMoreTokens()) {
          AcctNo1 = values.nextToken();
          Name1 = values.nextToken();
          pWord1 = values.nextToken();
        }
        try {
          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT AccountNo,Name,Password,Validity FROM ClientInfo WHERE AccountNo = " + AcctNo1);

          System.out.println("SELECT AccountNo,Name,Password,Validity FROM ClientInfo WHERE AccountNo = " + AcctNo1);
          server.aDbase.uprs.next();
          long acctno = server.aDbase.uprs.getLong(1);
          Name2 = server.aDbase.uprs.getString(2);
          pWord2 = server.aDbase.uprs.getString(3);
          boolean val = server.aDbase.uprs.getBoolean(4);

          AcctNo2 = Long.toString(acctno);
          server.aDbase.uprs.close();
          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT LogInStatus FROM ClientAccStatus WHERE AccountNo = " + AcctNo1);
          server.aDbase.uprs.next();
          boolean logValidity = server.aDbase.uprs.getBoolean(1);
          if ((val) && (!logValidity)) {
            if ((AcctNo1.equals(AcctNo2)) && (pWord1.equals(pWord2)) && (Name1.equals(Name2))) {
              strName = Name1;
              strAcctNo = AcctNo1;
              server.aDbase.stmt
                  .executeUpdate("UPDATE ClientAccStatus SET LogInStatus = True  WHERE AccountNo = " + strAcctNo);

              System.out.println("UPDATE ClientAccStatus SET LogInStatus = True  WHERE AccountNo = " + strAcctNo);

              // long id = 1; // Default ID value if no records are found

              // if (server.aDbase.uprs.next()) {
              // server.aDbase.uprs.last(); // Move to the last row
              // id = server.aDbase.uprs.getLong(1) + 1; // Get the last ID and increment
              // }
              // System.out.println("Heeloooo::: " + id);

              strAction = "Log in Success";
              sendToClient("LOGSUCCESS");

            } else if ((AcctNo1.equals(AcctNo2)) && (pWord1.equals("admin")) && (Name1.equals("admin"))) {
              strName = "Administrator : " + Name2;
              server.clients.addElement(server.lastClient);
              server.lblRunning.setText("Currently logged :" + server.clients.size() + " client(s)");

              sendToClient("LOGSUCCESS");
            } else {
              strName = Name1;
              strAcctNo = AcctNo1;

              strAction = "Log in Fail";
              server.aDbase.uprs.last();
              long id = server.aDbase.uprs.getLong(1) + 1;

            }
          } else if (!val) {
            strName = Name1;
            strAcctNo = AcctNo1;

            strAction = "Account Not Valid";
            sendToClient("ACCOUNT_NOT_VALID");
            server.aDbase.uprs.last();
            long id = server.aDbase.uprs.getLong(1) + 1;
          } else if (logValidity) {
            strName = Name1;
            strAcctNo = AcctNo1;

            strAction = "Trying to Log In as Duplicate";
            sendToClient("LOGGED_IN_DUPLICATE");

          }
        } catch (java.sql.SQLException sqle) {
          System.out.println("Error LC " + sqle);
          strName = Name1;
          strAcctNo = AcctNo1;
          strAction = "Account does not exist...given an error message";
          sendToClient("ACCOUNT_DOES_NOT_EXIST");

        } catch (java.lang.NumberFormatException nle) {
          sendToClient("ERROR.LOGIN");
          System.out.println("Error" + nle);
        }

      } else if (cmd.equals("VIEW_LOGS")) {
        String AcctNo = values.nextToken();
        int i = 0;
        try {
          String dtLog = new String("");

          while (server.aDbase.uprs.next()) {
            dtLog = dtLog + server.aDbase.uprs.getString(1) + "$" + server.aDbase.uprs.getString(2) + "$"
                + server.aDbase.uprs.getString(4) + "$" + server.aDbase.uprs.getString(5) + ".";
            i++;
          }
          strAction = "Viewing Account Logs.. ";
          sendToClient("ACCOUNT_LOGS_TO_CLIENT." + AcctNo + "." + Integer.toString(i) + "." + dtLog);
          System.out.println(" i = " + i);
        } catch (java.sql.SQLException sqle) {
          System.out.println("Error : " + sqle);
        }
      } else if (cmd.equals("FORCED_LOGGED_OUT")) {
        try {
          if (values.hasMoreTokens()) {
            String AcctNo = values.nextToken();
            server.aDbase.stmt
                .executeUpdate("UPDATE ClientAccStatus SET LogInStatus = False  WHERE AccountNo = " + AcctNo);
          } else {
            System.out.println("No tokens available");
          }

        } catch (java.sql.SQLException sqle) {
          strAction = "Forced Logged Out Failed";
          System.out.println("Error FL:" + sqle);
        } catch (java.util.NoSuchElementException nle) {
          strAction = "Terminated port..\nNo clients at present.. ";
          System.out.println("Error FLN:" + nle);
        }
      } else if (cmd.equals("ACCT_OPTIONS")) {
        String Pin = values.nextToken();
        String AcctNo = values.nextToken();
        try {
          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT AccountNo,Pin FROM ClientInfo WHERE AccountNo = " + AcctNo);
          server.aDbase.uprs.next();
          long pinAct = server.aDbase.uprs.getLong(2);
          server.aDbase.uprs.close();
          strAction = "Request for Account Options ..\nChecking PIN..";
          if (Long.parseLong(Pin) == pinAct) {
            strAction = "PIN Validation Success..\nAccount Options Allowed..";
            sendToClient("ACCT_OPTIONS_ALLOWED");

          } else {
            strAction = "PIN Validation failed..\nAccount Options Not Allowed..";
            server.aDbase.uprs.last();
            long id = server.aDbase.uprs.getLong(1) + 1;
            sendToClient("INCORRECT_PIN");

          }
        } catch (java.sql.SQLException sqle) {
          System.out.println("Error :" + sqle);
        } catch (java.lang.NumberFormatException nle) {
          sendToClient("ERROR.ACCT_OPTION");
          System.out.println("Error" + nle);
        }

      } else if (cmd.equals("LOGGED_OUT")) {
        String AcctNo = values.nextToken();
        try {

          server.aDbase.stmt
              .executeUpdate("UPDATE ClientAccStatus SET LogInStatus = False  WHERE AccountNo = " + AcctNo);

          System.out.println("UPDATE ClientAccStatus SET LogInStatus = False  WHERE AccountNo = " + AcctNo);

          strAction = "Logged Out Success";
          sendToClient("LOGGED_OUT_SUCCESS");
          System.out.println("Logged Out Success");

          System.out.println("Removing client from server..");
          // AccessServer t;
          // t = (AccessServer) this;

          server.removeClient(this);

        } catch (java.sql.SQLException sqle) {
          strAction = "Logged Out Failed";
          sendToClient("LOGGED_OUT_FAILED");
          System.out.println("Error at Logout:" + sqle);
        }

        // try {
        // System.out.println("Removing client from server..");
        // AccessServer t;
        // t = (AccessServer) this;
        // server.removeClient(t);
        // } catch (ArrayIndexOutOfBoundsException ae) {
        // server.txtInfo.setText("No client with index: " +
        // server.tabbedPane.getSelectedIndex());
        // }

      } else if (cmd.equals("VIEWACC")) {
        String AcctNo = values.nextToken();
        try {
          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT AccountNo,Name,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
          server.aDbase.uprs.next();
          String Name = server.aDbase.uprs.getString(2);
          long bal = server.aDbase.uprs.getLong(3);
          String balance = Long.toString(bal);
          strAction = "Viewing  Account";
          sendToClient("VIEWACC." + AcctNo + "." + Name + "." + balance);
        } catch (java.sql.SQLException sqle) {
          System.out.println("Error :" + sqle);

        }

      } else if (cmd.equals("PASSWORD_CHANGE")) {
        String AcctNo = values.nextToken();
        String oldPw = values.nextToken();
        String newPw1 = values.nextToken();
        String newPw2 = values.nextToken();
        if (newPw1.equals(newPw2)) {
          try {
            server.aDbase.uprs = server.aDbase.stmt
                .executeQuery("SELECT AccountNo,Password FROM ClientInfo WHERE AccountNo = " + AcctNo);
            server.aDbase.uprs.next();
            String passwrd = server.aDbase.uprs.getString(2);
            if (passwrd.equals(oldPw)) {
              strAction = "Password Changed Successfully";
              server.aDbase.stmt
                  .executeUpdate("UPDATE ClientInfo SET Password  = '" + newPw1 + "'  WHERE AccountNo = " + AcctNo);
            } else {
              strAction = "Password Change Failed..\nIncorrect old password";
            }

          } catch (java.sql.SQLException sqle) {

            System.out.println("Error :" + sqle);
          }

        } else {

          sendToClient("PASSWORD_CHANGE_FAILED_INCORRECT_NEWPASSWORD");

        }
      } else if (cmd.equals("PIN_CHANGE")) {

        String AcctNo = values.nextToken();
        String oldPin = values.nextToken();
        String newPin1 = values.nextToken();
        String newPin2 = values.nextToken();
        try {
          if (Long.parseLong(newPin1) == Long.parseLong(newPin2)) {

            server.aDbase.uprs = server.aDbase.stmt
                .executeQuery("SELECT AccountNo,Pin FROM ClientInfo WHERE AccountNo = " + AcctNo);
            server.aDbase.uprs.next();
            long pin = server.aDbase.uprs.getLong(2);
            if (pin == Long.parseLong(oldPin)) {
              strAction = "PIN Changed Successfully";
              sendToClient("PIN_CHANGE_SUCCESS");
              server.aDbase.stmt
                  .executeUpdate("UPDATE ClientInfo SET Pin  = " + newPin1 + "  WHERE AccountNo = " + AcctNo);
            } else {
              strAction = "PIN Change Failed..\nIncorrect old PIN";
            }
          }

          else {

            sendToClient("PIN_CHANGE_FAILED_INCORRECT_NEWPIN");

          }
        } catch (java.sql.SQLException sqle) {

          System.out.println("Error :" + sqle);
        } catch (java.lang.NumberFormatException nle) {
          sendToClient("ERROR.PIN_CHANGE");
          System.out.println("Error" + nle);
        }

      } else if (cmd.equals("TRANS")) {
        String Pin = values.nextToken();
        String AcctNo = values.nextToken();
        try {
          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT AccountNo,Pin FROM ClientInfo WHERE AccountNo = " + AcctNo);
          server.aDbase.uprs.next();
          long pinAct = server.aDbase.uprs.getLong(2);
          server.aDbase.uprs.close();
          strAction = "Request for Transaction ..\nChecking PIN..";
          if (Long.parseLong(Pin) == pinAct) {
            server.aDbase.uprs = server.aDbase.stmt
                .executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
            server.aDbase.uprs.next();

            long bal = server.aDbase.uprs.getLong(2);

            server.aDbase.uprs.close();
            String balance = Long.toString(bal);

            strAction = "PIN Validation Success..\nRequest for Transaction ..";
            if (bal > 500) {
              strAction = strAction + ": Allowed";
              sendToClient("TRANS." + AcctNo + "." + balance);
            } else {
              strAction = strAction + ": Not Allowed (Min Bal Rs 500\\-";
              sendToClient("TRANS_NOT_ALLOWED");

            }
          } else {
            strAction = "Request for Transaction Failed..\nIncorrect PIN";
            sendToClient("INCORRECT_PIN");
          }
        } catch (java.sql.SQLException sqle) {
          System.out.println("Error :" + sqle);
        } catch (java.lang.NumberFormatException nle) {
          sendToClient("ERROR.TRANS");
          System.out.println("Error" + nle);
        }

      } else if (cmd.equals("TRANSACTION")) {
        String AcctNo = values.nextToken();
        try {
          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
          server.aDbase.uprs.next();

          long bal1 = server.aDbase.uprs.getLong(2);
          String clntAcctTo = values.nextToken();

          String clntAmt = values.nextToken();
          if (clntAcctTo.equals(AcctNo)) {
            strAction = "Transaction Failed";
            sendToClient("TRANSACTION_FAILURE");
          } else {
            long Amt = Long.parseLong(clntAmt);
            server.aDbase.uprs.close();
            if ((bal1 - Amt) >= 500) {
              server.aDbase.uprs = server.aDbase.stmt
                  .executeQuery("SELECT AccountNo,Validity FROM ClientInfo WHERE AccountNo = " + clntAcctTo);
              server.aDbase.uprs.next();
              if (server.aDbase.uprs.getBoolean(2)) {
                server.aDbase.uprs = server.aDbase.stmt
                    .executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + clntAcctTo);
                server.aDbase.uprs.next();
                long bal2 = server.aDbase.uprs.getLong(2);
                bal2 = bal2 + Amt;
                bal1 = bal1 - Amt;

                System.out.println(bal1 + "." + bal2 + "." + AcctNo + "." + clntAcctTo);
                server.aDbase.stmt
                    .executeUpdate("UPDATE ClientAccStatus SET Balance = " + bal1 + " WHERE AccountNo = " + AcctNo);
                server.aDbase.stmt
                    .executeUpdate("UPDATE ClientAccStatus SET Balance = " + bal2 + " WHERE AccountNo = " + clntAcctTo);

                // server.aDbase.uprs.close();
                strAction = "Transaction Success";
                sendToClient("TRANSACTION_SUCCESS");
                //
              } else {
                sendToClient("TRANSACTION_FAILED_ACCOUNT_NOT_VALID");
              }
              // server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET Balance =
            } else {
              strAction = "Transaction Failed";
              sendToClient("FAILED_MIN_BAL_TRANS");
            }

          }

        } catch (java.sql.SQLException sqle) {
          // if(sqle.equals("invalid cursor state"))
          {
            strAction = "Transaction Failed";
            sendToClient("TRANSACTION_FAILURE");
          }
          System.out.println("Error :" + sqle);
        }

        catch (java.lang.NumberFormatException nle) {
          sendToClient("ERROR.TRANSACTION");
          System.out.println("Error" + nle);
        }

      } else if (cmd.equals("DEP")) {
        String AcctNo = values.nextToken();
        String Name = values.nextToken();
        String pWord = values.nextToken();
        try {
          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT AccountNo,Balance,Name FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
          server.aDbase.uprs.next();
          long bal = server.aDbase.uprs.getLong(2);
          String balance = Long.toString(bal);
          String clntName = server.aDbase.uprs.getString(3);
          strName = "Administrator : " + clntName;
          strAcctNo = AcctNo;
          strAction = "Deposit in progress for " + clntName;
          server.pause(2000);
          sendToClient("DEPOSIT_ALLOWED." + AcctNo + "." + clntName + "." + balance);
        } catch (java.sql.SQLException sqle) {
          System.out.println("Error :" + sqle);
        }

      } else if (cmd.equals("WITHDRAW")) {
        String pin = values.nextToken();
        String AcctNo = values.nextToken();
        String Name = values.nextToken();
        try {

          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT AccountNo,Pin FROM ClientInfo WHERE AccountNo = " + AcctNo);
          server.aDbase.uprs.next();
          long pinAct = server.aDbase.uprs.getLong(2);
          server.aDbase.uprs.close();
          strAction = "Request for withdrawal...\n Checking PIN";
          if (Long.parseLong(pin) == pinAct) {
            server.aDbase.uprs = server.aDbase.stmt
                .executeQuery("SELECT AccountNo,Balance,Name FROM ClientAccStatus WHERE AccountNo =" + AcctNo);
            server.aDbase.uprs.next();
            long bal = server.aDbase.uprs.getLong(2);

            if (bal > 500) {
              strAction = "PIN Validation Success..\nWithdrawal Allowed .";
              String balance = Long.toString(bal);
              sendToClient("WITHDRAW_ALLOWED." + AcctNo + "." + server.aDbase.uprs.getString(3) + "." + balance);
            } else {
              strAction = strAction + ": Not Allowed (Bal Rs 500\\-)";
              sendToClient("WITHDRAW_NOT_ALLOWED");
            }
          } else {
            sendToClient("INCORRECT_PIN");

          }
        } catch (java.sql.SQLException sqle) {

          sendToClient("WITHDRAW_NOT_ALLOWED");
          System.out.println("Error :" + sqle);

        }

      } else if (cmd.equals("WITHDRAW_IN_PROGRESS")) {
        String AcctNo = values.nextToken();
        String clntAmt = values.nextToken();
        try {

          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
          server.aDbase.uprs.next();
          long bal = server.aDbase.uprs.getLong(2);
          long Amt = Long.parseLong(clntAmt);
          if (((bal - Amt) >= 500)) {

            server.aDbase.uprs.close();
            bal = bal - Amt;
            server.aDbase.stmt
                .executeUpdate("UPDATE ClientAccStatus SET Balance = " + bal + " WHERE AccountNo = " + AcctNo);

            server.pause(1000);

            strAction = "Rs " + Amt + "\\- Withdrawn....Withdraw Success ";
            sendToClient("WITHDRAW_SUCCESS");
            //

            // server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET Balance =
          } else {
            strAction = "Withdraw Failed";
            sendToClient("FAILED_MIN_BAL_WTH");
          }
        }

        catch (java.sql.SQLException sqle) {

          strAction = "Withdraw Failed";
          sendToClient("WITHDRAW_FAILURE");

        } catch (java.lang.NumberFormatException nle) {
          sendToClient("ERROR.WITHDRAWAL");
          System.out.println("Error" + nle);
        }

      } else if (cmd.equals("DEPOSIT_IN_PROGRESS")) {
        String AcctNo = values.nextToken();
        String dep = values.nextToken();

        try {
          server.aDbase.uprs = server.aDbase.stmt
              .executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
          server.aDbase.uprs.next();
          long bal = server.aDbase.uprs.getLong(2);
          long depAmt = Long.parseLong(dep);
          bal = bal + depAmt;
          String balance = Long.toString(bal);
          server.aDbase.stmt
              .executeUpdate("UPDATE ClientAccStatus SET Balance = " + balance + " WHERE AccountNo = " + AcctNo);
          server.pause(1000);
          strAction = "Deposit Success";
          sendToClient("DEPOSIT_SUCCESS");

        } catch (java.sql.SQLException sqle) {

          System.out.println("Error :" + sqle);
          strAction = "Deposit Failed";
          sendToClient("DEPOSIT_FAILED");
        }

        catch (java.lang.NumberFormatException nle) {
          sendToClient("ERROR.DEPOSIT");
          System.out.println("Error" + nle);
        }

      }
    }

  }

  public void sendToClient(String msg) {
    outClient.println(msg);
    System.out.println("Client Port   : " + clientConnection.getPort());
    System.out.println("Server -> Client : " + msg);
    if (outClient.checkError())
      System.err.println("Cannot send -> " + msg);
  }

  public void closeEverything() {
    try {
      thClient = null;
      clientConnection.close();
      inClient.close();
      outClient.close();
    } catch (Exception e) {
      System.err.println("On closeEverything() -> " + e);
    }
  }

}