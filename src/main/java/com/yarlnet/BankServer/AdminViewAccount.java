
/****
* File Name		:
* Author		:
* Professor		:
* Project		:
* Description	:
*/

/* Standard Java Packages */
package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.sql.*;

class AdminViewAccount extends JFrame implements ActionListener {

   /* Instance Variables */

   long s;
   JPanel mainPanel = new JPanel();
   JPanel centerPanel = new JPanel();
   JPanel leftPanel = new JPanel();
   JPanel rightPanel = new JPanel();
   JPanel bottomPanel = new JPanel();
   JPanel upperPanel = new JPanel();
   JPanel bottomestPanel = new JPanel();
   JPanel inbottomPanel = new JPanel();

   JButton btnDbBegin;
   JButton btnDbBwd;
   JButton btnDbFwd;
   JButton btnDbEnd;
   JTextField txtAcctNo;
   JButton btnSearch;
   long lstAccountno;
   Server server;
   // AdminMainMenu adminMain;

   JLabel lblAcctNo = new JLabel("Account No:");
   JLabel lblName = new JLabel("Name :");
   JLabel lblValidity = new JLabel("Validity :");
   JLabel lblBalance = new JLabel("Balance :");

   JLabel lblAddrLn1 = new JLabel("Address line 1 :");
   JLabel lblAddrLn2 = new JLabel("Address Line 2 :");
   JLabel lblCtyTwn = new JLabel("City/Town :");
   JLabel lblState = new JLabel("Province :");
   JLabel lblPhone = new JLabel("Phone :");

   // AdminEntryLevel ObAdmin;
   JLabel lblLeft;
   JLabel lblRight;
   JTextField fields[];

   public AdminViewAccount(Server temp)// AdminMainMenu temp )//Server temp

   {
      /* Instantiate an object of this class to use as ActionListener of the GUI */
      // adminMain = temp ;
      server = temp;
      Initialize();

   }

   /**
    * Name : Initialize
    * 
    * @param : none
    * @return : void
    *         Description : develops the GUI
    */
   public void Initialize() {

      // frame = new JFrame();
      // look & feel setup:
      try {
         UIManager.setLookAndFeel(
               UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
         System.err.println("Couldn't use the system "
               + "look and feel: " + e);
      }

      /*
       * The default value is: HIDE_ON_CLOSE,
       * 
       */
      // frame.
      setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

      // processing window events:
      WindowListener L = new WindowAdapter() {
         public void windowClosing(WindowEvent e) {

            server.adminMain.setVisible(true);
            // closeApplication();
         }
      };
      // frame.
      addWindowListener(L);

      btnSearch = new JButton("Search");
      btnDbBegin = new JButton("  |<  ");
      btnDbBwd = new JButton("  <<  ");
      btnDbFwd = new JButton("  >>  ");
      btnDbEnd = new JButton("  >|  ");
      lblLeft = new JLabel("               ");
      lblRight = new JLabel("               ");
      fields = new JTextField[9];
      txtAcctNo = new JTextField("", 10);
      mainPanel = new JPanel(new BorderLayout());
      centerPanel = new JPanel(new GridLayout(10, 2, 5, 5));

      upperPanel = new JPanel(new GridLayout(3, 1, 5, 5));
      bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      inbottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      bottomestPanel = new JPanel(new GridLayout(2, 1));

      txtAcctNo.setSize(5, 4);
      lblAcctNo.setSize(5, 4);
      lblName.setSize(5, 4);
      lblBalance.setSize(5, 4);
      lblValidity.setSize(5, 4);
      lblAddrLn1.setSize(5, 4);
      lblAddrLn2.setSize(5, 4);
      lblCtyTwn.setSize(5, 4);
      lblState.setSize(5, 4);
      lblPhone.setSize(5, 4);

      // btnSubmit.setSize(10,5);
      // btnCancel.setSize(10,5);
      btnDbBegin.addActionListener(this);
      btnDbBwd.addActionListener(this);
      btnDbFwd.addActionListener(this);
      btnDbEnd.addActionListener(this);
      btnSearch.addActionListener(this);
      for (int i = 0; i < 9; i++) {

         // fields[(2*i)+1] = new JTextField(labels[i]);
         fields[(i)] = new JTextField("", 15);
         // fields[(2*i)+1].setEditable(false);
         fields[i].setSize(5, 4);
         fields[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
         fields[i].setEditable(false);

      }

      // System.out.println(s);

      txtAcctNo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

      // rightPanel.add(new JLabel(" "));
      // rightPanel.add(new JLabel(" "));

      centerPanel.add(lblAcctNo);
      centerPanel.add(fields[0]);
      centerPanel.add(lblName);
      centerPanel.add(fields[1]);
      centerPanel.add(lblBalance);
      centerPanel.add(fields[2]);
      centerPanel.add(lblValidity);
      centerPanel.add(fields[3]);
      centerPanel.add(lblAddrLn1);
      centerPanel.add(fields[4]);
      centerPanel.add(lblAddrLn2);
      centerPanel.add(fields[5]);
      centerPanel.add(lblCtyTwn);
      centerPanel.add(fields[6]);
      centerPanel.add(lblState);
      centerPanel.add(fields[7]);
      centerPanel.add(lblPhone);
      centerPanel.add(fields[8]);
      centerPanel.add(new JLabel(""));
      centerPanel.add(new JLabel(""));

      mainPanel.add(centerPanel, BorderLayout.CENTER);
      mainPanel.add(lblLeft, BorderLayout.EAST);
      mainPanel.add(lblRight, BorderLayout.WEST);
      upperPanel.add(new JLabel(" "));
      JLabel lbl1 = new JLabel("View Account", SwingConstants.CENTER);
      lbl1.setFont(new Font("", Font.BOLD, 12));
      lbl1.setSize(5, 4);

      upperPanel.add(lbl1);
      upperPanel.add(new JLabel(" ", SwingConstants.CENTER));

      mainPanel.add(upperPanel, BorderLayout.NORTH);
      bottomPanel.add(btnDbBegin);
      bottomPanel.add(btnDbBwd);
      bottomPanel.add(btnDbFwd);
      bottomPanel.add(btnDbEnd);
      inbottomPanel.add(new JLabel("Enter Account No"));
      inbottomPanel.add(txtAcctNo);
      inbottomPanel.add(btnSearch);
      bottomestPanel.add(inbottomPanel);
      bottomestPanel.add(bottomPanel);

      mainPanel.add(bottomestPanel, BorderLayout.SOUTH);

      setContentPane(mainPanel);

      // it doesn't work with our JTabbedPane !!! ---> pack();
      // frame.
      setSize(400, 400);
      // frame.
      setBounds(150, 80, 400, 400);

      // show the window:
      // frame.
      setVisible(true);
      setResizable(false);

   }

   /**
    * Name : actionPeformed
    * 
    * @param : ActionEvent
    * @return : void
    *         Description :
    */
   public void actionPerformed(ActionEvent e) {
      JButton src = (JButton) e.getSource();

      if (src == btnDbBegin) {

         try {

            server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery(
                  "SELECT AccountNo,Name,AddressLine1,AddressLine2,City,Province,Phone,Validity FROM ClientInfo");

            server.aDbase.uprs.first();

            long acctno = server.aDbase.uprs.getLong(1);
            System.out.println(acctno);
            String AcNo = Long.toString(acctno);

            boolean val = server.aDbase.uprs.getBoolean(8);
            String valid;
            if (val)
               valid = "Yes";
            else
               valid = "No";
            txtAcctNo.setText(AcNo);
            fields[0].setText(AcNo);
            fields[1].setText(" " + server.aDbase.uprs.getString(2));

            fields[3].setText(" " + valid);
            fields[4].setText(" " + server.aDbase.uprs.getString(3));
            fields[5].setText(" " + server.aDbase.uprs.getString(4));
            fields[6].setText(" " + server.aDbase.uprs.getString(5));
            fields[7].setText(" " + server.aDbase.uprs.getString(6));
            fields[8].setText(" " + server.aDbase.uprs.getString(7));

            server.aDbase.uprs.close();

            server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT Balance FROM ClientAccStatus");
            server.aDbase.uprs.first();
            long balance = server.aDbase.uprs.getLong(1);
            String Bal = Long.toString(balance);
            fields[2].setText(" Rs " + Bal + "\\-");

            server.aDbase.uprs.close();

         }

         catch (SQLException sqle) {
            System.out.println("Error :" + sqle);
         }

      }

      else if (src == btnDbEnd) {

         try {

            server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery(
                  "SELECT AccountNo,Name,AddressLine1,AddressLine2,City,Province,Phone,Validity FROM ClientInfo");

            server.aDbase.uprs.last();

            long acctno = server.aDbase.uprs.getLong(1);
            System.out.println(acctno);
            String AcNo = Long.toString(acctno);

            boolean val = server.aDbase.uprs.getBoolean(8);
            String valid;
            if (val)
               valid = "Yes";
            else
               valid = "No";
            txtAcctNo.setText(AcNo);
            fields[0].setText(AcNo);
            fields[1].setText(" " + server.aDbase.uprs.getString(2));

            fields[3].setText(" " + valid);
            fields[4].setText(" " + server.aDbase.uprs.getString(3));
            fields[5].setText(" " + server.aDbase.uprs.getString(4));
            fields[6].setText(" " + server.aDbase.uprs.getString(5));
            fields[7].setText(" " + server.aDbase.uprs.getString(6));
            fields[8].setText(" " + server.aDbase.uprs.getString(7));

            server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT Balance FROM ClientAccStatus");
            server.aDbase.uprs.last();
            long balance = server.aDbase.uprs.getLong(1);
            String Bal = Long.toString(balance);
            fields[2].setText(" Rs " + Bal + "\\-");
            server.aDbase.uprs.close();

         }

         catch (SQLException sqle) {
            System.out.println("Error :" + sqle);
         }
      } else if (src == btnDbBwd) {
         try {

            String s = fields[0].getText();
            long act = Long.parseLong(s);
            System.out.println(act);
            act = act - 1;
            String Actt = Long.toString(act);
            server.aDbase.uprs = server.aDbase.stmt.executeQuery(
                  "SELECT AccountNo,Name,AddressLine1,AddressLine2,City,Province,Phone,Validity FROM ClientInfo WHERE AccountNo = "
                        + Actt);

            server.aDbase.uprs.next();

            long acctno = server.aDbase.uprs.getLong(1);
            System.out.println(acctno);
            String AcNo = Long.toString(acctno);

            boolean val = server.aDbase.uprs.getBoolean(8);
            String valid;
            if (val)
               valid = "Yes";
            else
               valid = "No";
            txtAcctNo.setText(AcNo);
            fields[0].setText(AcNo);
            fields[1].setText(" " + server.aDbase.uprs.getString(2));

            fields[3].setText(" " + valid);
            fields[4].setText(" " + server.aDbase.uprs.getString(3));
            fields[5].setText(" " + server.aDbase.uprs.getString(4));
            fields[6].setText(" " + server.aDbase.uprs.getString(5));
            fields[7].setText(" " + server.aDbase.uprs.getString(6));
            fields[8].setText(" " + server.aDbase.uprs.getString(7));

            server.aDbase.uprs = server.aDbase.stmt
                  .executeQuery("SELECT Balance FROM ClientAccStatus WHERE AccountNo = " + Actt);
            server.aDbase.uprs.next();
            long balance = server.aDbase.uprs.getLong(1);
            String Bal = Long.toString(balance);
            fields[2].setText(" Rs " + Bal + "\\-");
            server.aDbase.uprs.close();

         }

         catch (SQLException sqle) {
            JOptionPane.showMessageDialog(this,
                  "End of Record",
                  "Error",
                  JOptionPane.ERROR_MESSAGE);
            System.out.println("Error :" + sqle);
         } catch (java.lang.NumberFormatException num) {

            System.out.println("Error :" + num);
         }

      } else if (src == btnDbFwd)

      {
         try {

            String s = fields[0].getText();
            long act = Long.parseLong(s);
            System.out.println(act);
            act = act + 1;
            String Actt = Long.toString(act);
            server.aDbase.uprs = server.aDbase.stmt.executeQuery(
                  "SELECT AccountNo,Name,AddressLine1,AddressLine2,City,Province,Phone,Validity FROM ClientInfo WHERE AccountNo = "
                        + Actt);

            server.aDbase.uprs.next();

            long acctno = server.aDbase.uprs.getLong(1);
            System.out.println(acctno);
            String AcNo = Long.toString(acctno);

            boolean val = server.aDbase.uprs.getBoolean(8);
            String valid;
            if (val)
               valid = "Yes";
            else
               valid = "No";
            txtAcctNo.setText(AcNo);
            fields[0].setText(AcNo);
            fields[1].setText(" " + server.aDbase.uprs.getString(2));

            fields[3].setText(" " + valid);
            fields[4].setText(" " + server.aDbase.uprs.getString(3));
            fields[5].setText(" " + server.aDbase.uprs.getString(4));
            fields[6].setText(" " + server.aDbase.uprs.getString(5));
            fields[7].setText(" " + server.aDbase.uprs.getString(6));
            fields[8].setText(" " + server.aDbase.uprs.getString(7));

            server.aDbase.uprs = server.aDbase.stmt
                  .executeQuery("SELECT Balance FROM ClientAccStatus WHERE AccountNo = " + Actt);
            server.aDbase.uprs.next();
            long balance = server.aDbase.uprs.getLong(1);
            String Bal = Long.toString(balance);
            fields[2].setText(" Rs " + Bal + "\\-");
            server.aDbase.uprs.close();

         }

         catch (SQLException sqle) {
            JOptionPane.showMessageDialog(this,
                  "End of Record",
                  "Error",
                  JOptionPane.ERROR_MESSAGE);
            System.out.println("Error :" + sqle);
         } catch (java.lang.NumberFormatException num) {

            System.out.println("Error :" + num);
         }

      } else if (src == btnSearch) {
         try {

            long act = Long.parseLong(txtAcctNo.getText().trim());
            System.out.println(act);

            String Actt = Long.toString(act);
            server.aDbase.uprs = server.aDbase.stmt.executeQuery(
                  "SELECT AccountNo,Name,AddressLine1,AddressLine2,City,Province,Phone,Validity FROM ClientInfo WHERE AccountNo = "
                        + Actt);

            server.aDbase.uprs.next();

            long acctno = server.aDbase.uprs.getLong(1);
            System.out.println(acctno);
            String AcNo = Long.toString(acctno);

            boolean val = server.aDbase.uprs.getBoolean(8);
            String valid;
            if (val)
               valid = "Yes";
            else
               valid = "No";

            fields[0].setText(AcNo);
            fields[1].setText(" " + server.aDbase.uprs.getString(2));

            fields[3].setText(" " + valid);
            fields[4].setText(" " + server.aDbase.uprs.getString(3));
            fields[5].setText(" " + server.aDbase.uprs.getString(4));
            fields[6].setText(" " + server.aDbase.uprs.getString(5));
            fields[7].setText(" " + server.aDbase.uprs.getString(6));
            fields[8].setText(" " + server.aDbase.uprs.getString(7));

            server.aDbase.uprs = server.aDbase.stmt
                  .executeQuery("SELECT Balance FROM ClientAccStatus WHERE AccountNo = " + Actt);
            server.aDbase.uprs.next();
            long balance = server.aDbase.uprs.getLong(1);
            String Bal = Long.toString(balance);
            fields[2].setText(" Rs " + Bal + "\\-");
            server.aDbase.uprs.close();

         }

         catch (SQLException sqle) {
            JOptionPane.showMessageDialog(this,
                  "Record not found!!",
                  "Error",
                  JOptionPane.ERROR_MESSAGE);
            System.out.println("Error :" + sqle);
         } catch (java.lang.NumberFormatException num) {

            System.out.println("Error :" + num);
         }

      }
   }

   public void setClear() {
      for (int i = 0; i < 8; i++) {
         fields[i].setText("");
      }
      try {
         server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery(
               "SELECT AccountNo,Name,AddressLine1,AddressLine2,City,Province,Phone,Validity FROM ClientInfo");
         server.aDbase.uprs.first();

         long acctno = server.aDbase.uprs.getLong(1);
         System.out.println(acctno);
         String AcNo = Long.toString(acctno);

         boolean val = server.aDbase.uprs.getBoolean(8);
         String valid;
         if (val)
            valid = "Yes";
         else
            valid = "No";
         txtAcctNo.setText(AcNo);
         fields[0].setText(AcNo);
         fields[1].setText(" " + server.aDbase.uprs.getString(2));

         fields[3].setText(" " + valid);
         fields[4].setText(" " + server.aDbase.uprs.getString(3));
         fields[5].setText(" " + server.aDbase.uprs.getString(4));
         fields[6].setText(" " + server.aDbase.uprs.getString(5));
         fields[7].setText(" " + server.aDbase.uprs.getString(6));
         fields[8].setText(" " + server.aDbase.uprs.getString(7));

         server.aDbase.uprs.close();

         server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT Balance FROM ClientAccStatus");
         server.aDbase.uprs.first();
         long balance = server.aDbase.uprs.getLong(1);
         String Bal = Long.toString(balance);
         fields[2].setText(" Rs " + Bal + "\\-");

         server.aDbase.uprs.close();

      }

      catch (SQLException sqle) {
         System.out.println("Error :" + sqle);
      }

   }

   public void setVVVisible() {
      // frame.
      setVisible(true);

   }

   public void setInVVVisible() {
      // frame.
      setVisible(false);

   }

   /*
    * public static void main(String[] args)
    * {
    * new AdminCreateAcc();
    * }
    */

}// end of class...
