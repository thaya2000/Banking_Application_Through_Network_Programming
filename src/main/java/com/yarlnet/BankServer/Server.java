package com.yarlnet.BankServer;

import javax.swing.*;
import javax.swing.event.*; // for ChangeListener of JTabbedPane.
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class Server extends JFrame implements ActionListener, ChangeListener, Runnable {

    // a list of the clients that are logged currently:
    public static Vector clients = new Vector();

    // number of ports to receive messages from clients:
    private static int client_port;

    // object to AccessServer class
    AccessServer lastClient;
    AccessDbase aDbase;

    // keeps server's address:
    private static InetAddress localHost = null;

    // GUI's
    AdminEntryLevel adminEntry;
    AdminCreateAcc adminCreate;
    AdminDeleteAcc adminDelete;
    AdminEditAcc adminEdit;
    AdminMainMenu adminMain;
    AdminUpdateAcc adminUpdate;
    AdminViewAccount adminViewAcct;
    AdminViewReport adminView;

    // server sockets for receiving connection from clients:
    ServerSocket socketForClient = null;
    // visual interface:

    String Date;

    // button for termination of a client:
    JButton btnTerminate = new JButton("Terminate ");
    JButton btnAdministrator = new JButton("Administrator ");
    JLabel lblRunning; // label to show how many clients are logged.
    JLabel timeRunning; // label to show the updated time.
    long acctno, balance;
    ImageIcon icon = new ImageIcon("pic.gif"); // picture for tabbedPane.
    JTextArea txtInfo = new JTextArea(); // text area for information.
    JTabbedPane tabbedPane = new JTabbedPane(); // tabs to select clients.
    JPanel pInnerTab = new JPanel(new BorderLayout()); // panel inside the tabbedPane.
    JLabel lblDateRunning;

    // thread that cares about accepting new clients:
    Thread thClientAccept = null;
    // thread that cares about updating the client info:
    Thread thUpdateClientInfo = null;
    // thread that cares about updating the date:
   // Thread clockThread = null;
    //Thread dateThread = null;
    String dtString = new String("");;
    String currentTime = new String("");

    public Server() {

        super(" Bank Server");

        try {
            socketForClient = new ServerSocket(client_port);
        } catch (IOException ioe) {
            System.err.println("Cannot open server socket: " + ioe);
            System.exit(0);
        }

        adminEntry = new AdminEntryLevel(this);
        adminCreate = new AdminCreateAcc(this);
        adminDelete = new AdminDeleteAcc(this);
        adminEdit = new AdminEditAcc(this);
        adminMain = new AdminMainMenu(this);
        adminUpdate = new AdminUpdateAcc(this);
        adminViewAcct = new AdminViewAccount(this);
        setDisplay();

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Couldn't use the system "
                    + "look and feel: " + e);
        }

        // update look & feel for those components created in
        // declaration (if required):
        btnTerminate.updateUI();
        btnAdministrator.updateUI();
        txtInfo.updateUI();
        tabbedPane.updateUI();
        pInnerTab.updateUI();

        /*
         * The default value is: HIDE_ON_CLOSE,
         * we need to ask user "Are you sure?" when there are clients logged.
         */
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // processing window events:
        WindowListener L = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeApplication();
            }
        };

        addWindowListener(L);

        // prepare the layout:
        JPanel pMain = new JPanel(new BorderLayout()); // main layout.
        JPanel pUpper = new JPanel(new GridLayout(1, 2)); // upper layout.
        JPanel pLLBL = new JPanel(new GridLayout(5, 1)); // for labels at left.
        JPanel pRLBL = new JPanel(new GridLayout(1, 1)); // for labels at right.

        lblRunning = new JLabel("     Currently logged : 0 client(s).");
        lblDateRunning = new JLabel("");

        pLLBL.add(new JLabel(" "));
        pLLBL.add(new JLabel(" "));
        pLLBL.add(lblDateRunning);
        pLLBL.add(new JLabel("     Server is running on host: " + localHost));
        pLLBL.add(lblRunning);
        timeRunning = new JLabel(" ");
        pRLBL.add(timeRunning);

        pUpper.add(pLLBL);
        pUpper.add(pRLBL);

        JPanel pBtns = new JPanel(new FlowLayout()); // for "Terminate" button.

        btnTerminate.addActionListener(this);
        pBtns.add(btnTerminate);

        // shown inside the tabbedPane:
        pInnerTab.add(txtInfo, BorderLayout.CENTER);
        pInnerTab.add(pBtns, BorderLayout.SOUTH);

        pMain.add(pUpper, BorderLayout.NORTH);
        tabbedPane.addChangeListener(this);
        pMain.add(tabbedPane, BorderLayout.CENTER);

        JPanel adminBtns = new JPanel(new BorderLayout());
        JPanel adminDisplay = new JPanel(new FlowLayout());
        adminDisplay.add(new JLabel("Login as : "));

        btnAdministrator.addActionListener(this);
        adminDisplay.add(btnAdministrator);

        adminBtns.add(adminDisplay, BorderLayout.SOUTH);
        pMain.add(adminBtns, BorderLayout.SOUTH);
        // set content pane:
        setContentPane(pMain);

        // it doesn't work with our JTabbedPane !!! ---> pack();
        setSize(600, 500);
        setBounds(100, 20, 600, 500);

        // show the window:
        setVisible(true);

        setResizable(false);

        aDbase = new AccessDbase();
        aDbase.connectionDb();
        pause(2000);
        adminView = new AdminViewReport(this);

        // start threads:
        thClientAccept = new Thread(this);
        thUpdateClientInfo = new Thread(this);
       // clockThread = new Thread(this);
       // dateThread = new Thread(this);
        thClientAccept.start(); // start accepting new clients.
        thUpdateClientInfo.start(); // start to care about updating the info.
      //  clockThread.start();
      //  dateThread.start(); // start to care about the time.
    }

    public void run() {

        Thread thisThread = Thread.currentThread();

        // thread that cares about accepting new clients:
        while (thClientAccept == thisThread) {
            try {
                // wait for client to connect, and then validate connection:
                // use temporary pointer for new connection:
                Socket clientConnection = socketForClient.accept();

                System.out.println("Client accepted!");

                // register a new client:
                // addNewTab();
                lastClient = new AccessServer(this);
                // clients.addElement(lastClient);
                // lblRunning.setText("Currently logged :" + clients.size() + " client(s)");

                // // connect client to AccessServer:
                lastClient.connectClient(clientConnection);

            } catch (Exception e) {
                if (thClientAccept != null) // not shutting down yet?
                    System.err.println("Accept client -> " + e);
            }
        }

        // thread that cares about updating the client info:
        // automatic update of info for selected client:
        while (thUpdateClientInfo == thisThread) {
            if (clients.size() > 0) // no clients?
                showClientData(lastClient.clientConnection.getPort());
            thUpdateClientInfo.yield(); // this thread isn't so important, think of others.
            pause(1200); // update every 1.2 of a second.
        }

        /*while (clockThread == thisThread) {
            iterateTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("clock thread -> " + e);
            }
        }
        while (dateThread == thisThread) {
            iterateDate();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("clock thread -> " + e);
            }
        }*/
    }

/*private void iterateDate() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date according to MySQL DATE format 'YYYY-MM-DD'
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Get the formatted date and time strings
        String dateString = now.format(formatter);

        // Update the label to display the formatted date
        lblDateRunning.setText("     Date : " + dateString);

        // Store the formatted date and time strings for later use if needed
        dtString = dateString;
    }*/

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        // JButton tempBtn = (JButton)e.getSource();
        JButton src = (JButton) e.getSource();
        if (src == btnTerminate) { // terminate the client
            try {
                AccessServer t; // temporary pointer.
                t = (AccessServer) clients.get(tabbedPane.getSelectedIndex());
                t.sendToClient("TERMINATED");
                removeClient(t);
            } catch (ArrayIndexOutOfBoundsException ae) {
                txtInfo.setText("No client with index: " + tabbedPane.getSelectedIndex());
            }
        } else if (src == btnAdministrator) {
            btnAdministrator.setEnabled(false);
            adminEntry.setClear();
            adminEntry.setVisible(true);

        } else if (src == adminEntry.logIn) {

            String s = new String(adminEntry.pField.getPassword());

            if ((adminEntry.txtID.getText().equalsIgnoreCase("admin")) &&
                    (s.equals("admin"))) {
                System.out.println("AdminEntryLevel: Logged In");
                adminEntry.setVisible(false);
                adminMain.setClear();
                adminMain.setVisible(true);
            } else {
                System.out.println("AdminEntryLevel:Log In Failed");
                JOptionPane.showMessageDialog(adminEntry,
                        "Log In Failed",
                        "Admin Entry Level",
                        JOptionPane.ERROR_MESSAGE);

            }
        } else if (src == adminEntry.cancelLogIn) {
            adminEntry.setVisible(false);
            btnAdministrator.setEnabled(true);

        } else if (src == adminMain.btnCreate) {
            adminMain.setVisible(false);
            // adminCreate.setClear();
            adminCreate.setVisible(true);
            System.out.println("Admin Create Acc");
        } else if (src == adminMain.btnDelete) {
            adminMain.setVisible(false);
            adminDelete.setClear();
            adminDelete.setVisible(true);
            System.out.println("Admin Delete Acc");
        } else if (src == adminMain.btnEdit) {
            adminMain.setVisible(false);
            adminEdit.setClear();
            adminEdit.setVisible(true);
            System.out.println("Admin Edit Acc");
        } else if (src == adminMain.btnViewAcct) {
            adminMain.setVisible(false);
            adminViewAcct.setClear();
            adminViewAcct.setVisible(true);

        } /*else if (src == adminMain.btnViewReport) {
            adminMain.setVisible(false);
            adminView.setActionCmd();
            adminView.setVisible(true);
        }*/ else if (src == adminMain.btnLogout) {
            adminEntry.setClear();
            adminMain.setVisible(false);
            adminEntry.setVisible(true);
        }

        else if (src == adminCreate.btnSubmit) {
            try {
                if ((adminCreate.fields[1].getText().trim().equals(""))
                        || (adminCreate.fields[2].getText().trim().equals(""))
                        || (adminCreate.fields[3].getText().trim().equals(""))
                        || (adminCreate.fields[4].getText().trim().equals(""))
                        || (adminCreate.fields[5].getText().trim().equals(""))
                        || (adminCreate.fields[6].getText().trim().equals(""))
                        || (adminCreate.fields[7].getText().trim().equals(""))) {

                    JOptionPane.showMessageDialog(adminCreate,
                            "Fields are incomplete.\n Status :Error",
                            "Admin Create Account",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    aDbase.stmt.executeUpdate("INSERT INTO ClientInfo" + " VALUES( " + adminCreate.fields[0].getText()
                            + ",'" + adminCreate.fields[1].getText() + "','" + adminCreate.fields[2].getText() + "','"
                            + adminCreate.fields[3].getText() + "','" + adminCreate.fields[4].getText() + "','"
                            + adminCreate.fields[5].getText() + "','" + adminCreate.fields[6].getText() + "'," + "'"
                            + adminCreate.fields[7].getText() + "',True," + adminCreate.fields[8].getText() + " )");
                    aDbase.stmt.executeUpdate("INSERT INTO ClientAccStatus" + " VALUES( "
                            + adminCreate.fields[0].getText() + ",'" + adminCreate.fields[1].getText()
                            + "', 500, False)");
                    System.out.println("Dbase Created");
                    JOptionPane.showMessageDialog(adminCreate,
                            "Account No : " + adminCreate.fields[0].getText() + "\nName          : "
                                    + adminCreate.fields[1].getText() + "\nPassword    : "
                                    + adminCreate.fields[2].getText() + "\nPin    : " + adminCreate.fields[8].getText()
                                    + "\nStatus        : Account Created",
                            "Admin Create Account",
                            JOptionPane.INFORMATION_MESSAGE);
                    adminCreate.setVisible(false);
                    adminMain.setVisible(true);
                }
            } catch (SQLException sqle) {
                System.out.println("Error AccCre:" + sqle);
            }
        }

        else if (src == adminCreate.btnCancel) {
            adminCreate.setVisible(false);
            adminMain.setVisible(true);
        } else if (src == adminDelete.btnDelete) {
            try {
                String s = adminDelete.txtAcctNo.getText();
                String updateQuery = "UPDATE ClientInfo SET Validity = False WHERE AccountNo  = " + s;
                aDbase.stmt.executeUpdate(updateQuery);
                aDbase.uprs = aDbase.stmt.executeQuery("SELECT Name FROM ClientInfo WHERE AccountNo = " + s);
                aDbase.uprs.next();
                String name = aDbase.uprs.getString(1);
                JOptionPane.showMessageDialog(adminDelete,
                        "Account No : " + s + "\nName          : " + name + " \nStatus        : Deleted",
                        "Admin Delete Account",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Dbase Deleted");
                adminDelete.setVisible(false);
                adminMain.setVisible(true);
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(adminDelete,
                        "Invalid Account Number",
                        "Admin Delete Account",
                        JOptionPane.ERROR_MESSAGE);

                System.out.println("Error:" + sqle);
            }
        } else if (src == adminDelete.btnCancel) {
            adminDelete.setVisible(false);
            adminMain.setVisible(true);
        } else if (src == adminEdit.btnEdit) {
            try {

                String s = adminEdit.txtAcctNo.getText();
                aDbase.uprs = aDbase.stmt.executeQuery("SELECT * FROM ClientInfo WHERE AccountNo = " + s);

                aDbase.uprs.next();
                adminUpdate.fields[0].setText(s);
                adminUpdate.fields[0].setEditable(false);
                adminUpdate.fields[1].setText(aDbase.uprs.getString(2));
                adminUpdate.fields[2].setText(aDbase.uprs.getString(3));
                adminUpdate.fields[3].setText(aDbase.uprs.getString(4));
                adminUpdate.fields[4].setText(aDbase.uprs.getString(5));
                adminUpdate.fields[5].setText(aDbase.uprs.getString(6));
                adminUpdate.fields[6].setText(aDbase.uprs.getString(7));
                adminUpdate.fields[7].setText(aDbase.uprs.getString(8));
                adminEdit.setVisible(false);
                adminUpdate.setVisible(true);
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(adminEdit,
                        "Invalid Account Number",
                        "Admin Edit Account",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (src == adminEdit.btnCancel) {
            adminEdit.setVisible(false);
            adminMain.setVisible(true);
        } else if (src == adminUpdate.btnUpdate) {
            try {

                String s = adminEdit.txtAcctNo.getText();

                String updateQuery = "UPDATE ClientInfo SET Name = '" + adminUpdate.fields[1].getText()
                        + "', Password = '" + adminUpdate.fields[2].getText() + "', AddressLine1 = '"
                        + adminUpdate.fields[3].getText() + "', AddressLine2 = '" + adminUpdate.fields[4].getText()
                        + "',City = '" + adminUpdate.fields[5].getText() + "', State = '"
                        + adminUpdate.fields[6].getText() + "', Phone = '" + adminUpdate.fields[7].getText()
                        + "', Validity = True WHERE AccountNo = " + s;

                aDbase.stmt.executeUpdate(updateQuery);
                aDbase.uprs = aDbase.stmt.executeQuery("SELECT Name,Password FROM ClientInfo WHERE AccountNo = " + s);
                aDbase.uprs.next();

                String name = aDbase.uprs.getString(1);
                String pword = aDbase.uprs.getString(2);
                JOptionPane.showMessageDialog(adminUpdate,
                        "Account No : " + s + "\nName          : " + name + "\nPassword    : " + pword
                                + "\nStatus        : Updated",
                        "Admin Update Account",
                        JOptionPane.INFORMATION_MESSAGE);
                adminUpdate.setVisible(false);
                adminMain.setVisible(true);
            } catch (SQLException sqle) {

                System.out.println("Error" + sqle);

            }

        } else if (src == adminUpdate.btnCancel) {
            adminUpdate.setVisible(false);
            adminMain.setVisible(true);

        } else if (src == adminViewAcct.btnDbBegin) {
            try {
                String query1 = " SELECT * FROM ClientInfo ";
                String query2 = " SELECT Balance FROM ClientAccStatus ";
                aDbase.tmpuprs = aDbase.tmpStmt.executeQuery(query1);

                aDbase.tmpuprs.first();
                acctno = aDbase.uprs.getLong(1);
                String AcNo = Long.toString(acctno);

                boolean val = aDbase.uprs.getBoolean(9);
                String valid;
                if (val)
                    valid = "Yes";
                else
                    valid = "No";

                adminViewAcct.fields[0].setText(" " + AcNo);
                adminViewAcct.fields[1].setText(" " + aDbase.uprs.getString(2));

                adminViewAcct.fields[3].setText(" " + valid);
                adminViewAcct.fields[4].setText(" " + aDbase.uprs.getString(4));
                adminViewAcct.fields[5].setText(" " + aDbase.uprs.getString(5));
                adminViewAcct.fields[6].setText(" " + aDbase.uprs.getString(6));
                adminViewAcct.fields[7].setText(" " + aDbase.uprs.getString(7));
                adminViewAcct.fields[8].setText(" " + aDbase.uprs.getString(8));

                balance = aDbase.tmpuprs.getLong(1);
                String Bal = Long.toString(balance);
                adminViewAcct.fields[2].setText(" Rs " + Bal + "\\-");

                System.out.println("Admin View Acct   |<");
            } catch (SQLException sqle) {
                System.out.println("Error :" + sqle);
            }

        } else if (src == adminViewAcct.btnDbBwd) {
            try {
                if (!aDbase.uprs.isFirst()) {

                    aDbase.uprs.previous();
                    aDbase.tmpuprs.previous();
                    acctno = aDbase.uprs.getLong(1);
                    String AcNo = Long.toString(acctno);

                    boolean val = aDbase.uprs.getBoolean(9);
                    String valid;
                    if (val)
                        valid = "Yes";
                    else
                        valid = "No";

                    adminViewAcct.fields[0].setText(" " + AcNo);
                    adminViewAcct.fields[1].setText(" " + aDbase.uprs.getString(2));

                    adminViewAcct.fields[3].setText(" " + valid);
                    adminViewAcct.fields[4].setText(" " + aDbase.uprs.getString(4));
                    adminViewAcct.fields[5].setText(" " + aDbase.uprs.getString(5));
                    adminViewAcct.fields[6].setText(" " + aDbase.uprs.getString(6));
                    adminViewAcct.fields[7].setText(" " + aDbase.uprs.getString(7));
                    adminViewAcct.fields[8].setText(" " + aDbase.uprs.getString(8));

                    balance = aDbase.tmpuprs.getLong(1);
                    String Bal = Long.toString(balance);
                    adminViewAcct.fields[2].setText(" Rs " + Bal + "\\-");

                    System.out.println("Admin View Acct   <<");
                }
            } catch (SQLException sqle) {
                System.out.println("Error :" + sqle);
            }

        } else if (src == adminViewAcct.btnDbFwd) {
            try {
                if (!aDbase.uprs.isLast()) {
                    aDbase.uprs.next();
                    aDbase.tmpuprs.next();
                    acctno = aDbase.uprs.getLong(1);
                    String AcNo = Long.toString(acctno);

                    boolean val = aDbase.uprs.getBoolean(9);
                    String valid;
                    if (val)
                        valid = "Yes";
                    else
                        valid = "No";

                    adminViewAcct.fields[0].setText(" " + AcNo);
                    adminViewAcct.fields[1].setText(" " + aDbase.uprs.getString(2));

                    adminViewAcct.fields[3].setText(" " + valid);
                    adminViewAcct.fields[4].setText(" " + aDbase.uprs.getString(4));
                    adminViewAcct.fields[5].setText(" " + aDbase.uprs.getString(5));
                    adminViewAcct.fields[6].setText(" " + aDbase.uprs.getString(6));
                    adminViewAcct.fields[7].setText(" " + aDbase.uprs.getString(7));
                    adminViewAcct.fields[8].setText(" " + aDbase.uprs.getString(8));

                    balance = aDbase.tmpuprs.getLong(1);
                    String Bal = Long.toString(balance);
                    adminViewAcct.fields[2].setText(" Rs " + Bal + "\\-");

                    System.out.println("Admin View Acct   >>");
                }
            } catch (SQLException sqle) {
                System.out.println("Error :" + sqle);
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object src = e.getSource();
        if (src == tabbedPane) { // click on a tab
            showClientData(lastClient.clientConnection.getPort());
        }
    }

    // public void addNewTab(int clientPort, String clientInfo) {
    // try {
    // int curTabs = tabbedPane.getTabCount();
    // if (curTabs == 0) { // no tabs in tabbedPane?
    // tabbedPane.addTab("Client " + clientPort, icon, pInnerTab);
    // JTextArea txtInfo = new JTextArea(clientInfo); // Display client info in a
    // text area
    // pInnerTab.add(txtInfo, BorderLayout.CENTER);
    // tabbedPane.setSelectedIndex(0);
    // } else {
    // // add empty tab, component from Tab#0 will be used:
    // tabbedPane.addTab("Client " + clientPort, icon, null);
    // JTextArea txtInfo = new JTextArea(clientInfo); // Display client info in a
    // text area
    // tabbedPane.setSelectedIndex(curTabs);
    // }
    // } catch (Exception e) {
    // System.err.println("addNewTab() -> " + e);
    // }
    // }

    public void addNewTab(int clientPort, String clientInfo) {
        try {
            JPanel clientPanel = new JPanel(new BorderLayout()); // Create a new panel for each client
            JTextArea txtInfo = new JTextArea(clientInfo); // Display client info in a text area
            clientPanel.add(new JScrollPane(txtInfo), BorderLayout.CENTER); // Add text area to client panel

            tabbedPane.addTab("Client " + clientPort, icon, clientPanel); // Add client panel to the new tab
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1); // Select the new tab
        } catch (Exception e) {
            System.err.println("addNewTab() -> " + e);
        }
    }

    private void removeClientTab(int clientPort) {
        try {
            System.out.println("removeClientTab()");
            System.out.println("removeClient in Progress (" + clientPort + ")");
            int tabCount = tabbedPane.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                String tabTitle = tabbedPane.getTitleAt(i);
                if (tabTitle.contains(Integer.toString(clientPort))) {
                    tabbedPane.removeTabAt(i);
                    System.out.println("Tab removed: " + tabTitle);
                    break; // Stop searching after removing the tab
                }
            }
        } catch (Exception e) {
            System.err.println("removeClientTab() -> " + e);
        }
    }

    // private void removeLastTab() {
    // System.out.println("removeLastTab()");
    // try {
    // int curTabs = tabbedPane.getTabCount();
    // tabbedPane.removeTabAt(curTabs - 1);
    // if (curTabs > 1)
    // tabbedPane.setSelectedIndex(0);
    // } catch (Exception e) {
    // System.err.println("removeLastTab() -> " + e);
    // }
    // }

    // private void showClientData() {
    // try {
    // AccessServer temp; // temporary pointer.
    // temp = (AccessServer) clients.get(tabbedPane.getSelectedIndex());
    // String sInfo = temp.getInfo();
    // if (!sInfo.equals(txtInfo.getText())) // update text only when required.
    // txtInfo.setText(sInfo);
    // } catch (ArrayIndexOutOfBoundsException ae) {
    // txtInfo.setText("No client with index: " + tabbedPane.getSelectedIndex());
    // }
    // }

    // private void showClientData(int clientPort) {
    // try {
    // // Find the AccessServer instance associated with the provided client port
    // AccessServer temp = null;
    // for (Object client : clients) {
    // AccessServer accessServer = (AccessServer) client;
    // if (accessServer.clientConnection.getPort() == clientPort) {
    // temp = accessServer;
    // break;
    // }
    // }

    // if (temp != null) {
    // String sInfo = temp.getInfo();
    // if (!sInfo.equals(txtInfo.getText())) // update text only when required.
    // txtInfo.setText(sInfo);
    // } else {
    // txtInfo.setText("No client with port: " + clientPort);
    // }
    // } catch (Exception e) {
    // System.err.println("showClientData() -> " + e);
    // }
    // }

    public void showClientData(int clientPort) {
        try {
            // Find the index of the tab associated with the provided client port
            int tabCount = tabbedPane.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                Component tabComponent = tabbedPane.getComponentAt(i);
                if (tabComponent instanceof JPanel) {
                    JPanel panel = (JPanel) tabComponent;
                    AccessServer accessServer = (AccessServer) panel.getClientProperty("AccessServer");
                    if (accessServer != null && accessServer.clientConnection.getPort() == clientPort) {
                        // Update the text area in the panel with the client information
                        JTextArea txtInfo = (JTextArea) panel.getClientProperty("InfoTextArea");
                        if (txtInfo != null) {
                            String sInfo = accessServer.getInfo();
                            if (!sInfo.equals(txtInfo.getText())) {
                                txtInfo.setText(sInfo);
                            }
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("showClientData() -> " + e);
        }
    }

    public static void main(String args[]) {

        // // validate parameter count:
        // if (args.length != 1) {
        // System.err.println("Wrong parameters! Usage:");
        // System.err.println("java Server <client_port> ");
        // System.exit(1);
        // }

        // // process parameters:
        // try {
        // client_port = Integer.parseInt(args[0]);
        // } catch (NumberFormatException e) {
        // System.err.println("Wrong number for a port -> " + e);
        // System.exit(1);
        // }

        // process parameters:
        try {
            client_port = Integer.parseInt("4444");
        } catch (NumberFormatException e) {
            System.err.println("Wrong number for a port -> " + e);
            System.exit(1);
        }

        // get address of the server:
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host - probably localhost with no IP!");
        }

        // print out the info (the same info is also shown on the server's
        // GUI window).
        System.out.println("Server is running on host: " + localHost);
        System.out.println("Waiting clients on port: " + client_port);
        // create & start server GUI engine:
        new Server();

    }

    private void closeApplication() {
        // ask user if he/she is sure to shut down the server when
        // there are clients running:
        if (clients.size() > 0) {
            int result;
            result = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to shut down the SERVER?\n" +
                            "All clients will be terminated!",
                    "Close anyway?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (result != 0) // no, cancel.
                return;
            for (int i = clients.size() - 1; i >= 0; i--) {
                AccessServer temp;
                temp = (AccessServer) clients.get(i);
                temp.sendToClient("TERMINATED");
                removeClient(temp);
            }
        }

        // stop the server's threads:
        thClientAccept = null;

        try {
            socketForClient.close();
        } catch (IOException e) {
            System.err.println("On close -> " + e);
        }
        System.exit(0);
    }

    public void setDisplay() {
        adminEntry.setVisible(false);
        adminCreate.setVisible(false);
        adminDelete.setVisible(false);
        adminEdit.setVisible(false);
        adminMain.setVisible(false);
        adminUpdate.setVisible(false);
        adminViewAcct.setVisible(false);
    }

    public void removeClient(AccessServer clientToDelete) {
        if (clients.contains(clientToDelete)) { // check if not removed already.
            // close sockets, streams, stop threads:
            pause(1500);
            // clientToDelete.closeEverything();
            clients.remove(clientToDelete); // remove from vector.
            lblRunning.setText("Currently logged: " + clients.size() + " client(s).");
            // removeLastTab();
            removeClientTab(clientToDelete.clientConnection.getPort());
        }
    }
}