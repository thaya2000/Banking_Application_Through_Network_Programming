package com.yarlnet.BankServer;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;

public class Server extends JFrame implements ActionListener, ChangeListener, Runnable {

    public static Vector clients = new Vector();

    private static int client_port;

    AccessServer lastClient;
    AccessDbase aDbase;

    private static InetAddress localHost = null;

    // GUI's
    AdminEntryLevel adminEntry;
    AdminCreateAcc adminCreate;
    AdminDeleteAcc adminDelete;
    AdminEditAcc adminEdit;
    AdminMainMenu adminMain;
    AdminUpdateAcc adminUpdate;
    AdminViewAccount adminViewAcct;

    ServerSocket socketForClient = null;

    JButton btnAdministrator = new JButton("Administrator ");

    JLabel lblRunning;
    long acctno, balance;

    Thread thClientAccept = null;

    public Server() {
        super(" HTV Bank Server");
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

        btnAdministrator.updateUI();
        btnAdministrator.setContentAreaFilled(true);
        btnAdministrator.setBorderPainted(false);
        btnAdministrator.setBackground(Color.YELLOW);
        btnAdministrator.setForeground(Color.BLACK);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        WindowListener L = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing the server.");
                closeApplication();
            }
        };

        addWindowListener(L);

        JPanel pMain = new JPanel(new BorderLayout());
        // Upper layout with vertical BoxLayout
        JPanel pUpper = new JPanel();
        pUpper.setLayout(new BoxLayout(pUpper, BoxLayout.Y_AXIS)); // Stack vertically

        // Panel for labels at left
        JPanel pLLBL = new JPanel(new GridLayout(5, 5));
        pLLBL.setBackground(Color.YELLOW);
        pLLBL.setBorder(new EmptyBorder(10, 10, 10, 10));
        pLLBL.add(new JLabel(" "));
        pLLBL.add(new JLabel(" "));
        pLLBL.add(new JLabel("     Server is running on host: " + localHost));
        lblRunning = new JLabel("     Currently logged : 0 client(s).");
        pLLBL.add(lblRunning);

        // Panel for labels at right
        JPanel pRLBL = new JPanel(new GridLayout(1, 1));
        JLabel lblBank = new JLabel("HTV BANK");
        lblBank.setHorizontalAlignment(JLabel.CENTER); // Center alignment
        lblBank.setForeground(Color.BLACK); // Font color
        lblBank.setFont(new Font("Arial", Font.BOLD, 44)); // Font style, weight, and size
        pRLBL.add(lblBank);
        pRLBL.setBorder(new EmptyBorder(40, 0, 30, 0));

        JPanel pDLBL = new JPanel(new GridLayout(1, 1)); // Assuming a simple grid layout
        JLabel lblDetails = new JLabel("Welcome to HTV Bank");
        lblDetails.setHorizontalAlignment(JLabel.CENTER); // Center the text
        lblDetails.setForeground(Color.RED); // Set the font color to red
        lblDetails.setFont(new Font("Arial", Font.PLAIN, 18)); // Set the font
        pDLBL.add(lblDetails);
        pDLBL.setBorder(new EmptyBorder(80, 0, 30, 0));

        // Adding panels to upper panel
        pUpper.add(pRLBL);
        pUpper.add(pLLBL);
        pUpper.add(pDLBL);

        // Add the upper panel to the main panel
        pMain.add(pUpper, BorderLayout.NORTH);

        JPanel adminBtns = new JPanel(new BorderLayout());
        JPanel adminDisplay = new JPanel(new FlowLayout());
        adminDisplay.add(new JLabel("Login as : "));

        btnAdministrator.addActionListener(this);
        adminDisplay.add(btnAdministrator);
        adminBtns.add(adminDisplay, BorderLayout.SOUTH);
        adminBtns.setBorder(new EmptyBorder(0, 0, 100, 0));
        pMain.add(adminBtns, BorderLayout.SOUTH);

        // set content pane:
        setContentPane(pMain);
        setSize(600, 500);
        setBounds(100, 20, 600, 500);
        setVisible(true);
        setResizable(true);

        aDbase = new AccessDbase();
        aDbase.connectionDb();
        pause(2000);

        // start threads:
        thClientAccept = new Thread(this);
        thClientAccept.start();
    }

    public void run() {
        Thread thisThread = Thread.currentThread();
        while (thClientAccept == thisThread) {
            try {
                Socket clientConnection = socketForClient.accept();
                System.out.println("Client accepted!");
                lastClient = new AccessServer(this);
                lastClient.connectClient(clientConnection);
            } catch (Exception e) {
                if (thClientAccept != null) // not shutting down yet?
                    System.err.println("Accept client -> " + e);
            }
        }
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == btnAdministrator) {
            btnAdministrator.setEnabled(false);
            adminEntry.setClear();
            adminEntry.setVisible(true);
        } else if (src == adminEntry.logIn) {

            String s = new String(adminEntry.pField.getPassword());

            if ((adminEntry.txtID.getText().equalsIgnoreCase("admin")) &&
                    (s.equals("admin"))) {
                // System.out.println("AdminEntryLevel: Logged In");
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
            adminCreate.setClear();
            adminCreate.setVisible(true);
            System.out.println("Admin Create Account Window Opened");
        } else if (src == adminMain.btnDelete) {
            adminMain.setVisible(false);
            adminDelete.setClear();
            adminDelete.setVisible(true);
            System.out.println("Admin Delete Account Window Opened");
        } else if (src == adminMain.btnEdit) {
            adminMain.setVisible(false);
            adminEdit.setClear();
            adminEdit.setVisible(true);
            System.out.println("Admin Edit Account Window Opened");
        } else if (src == adminMain.btnViewAcct) {
            adminMain.setVisible(false);
            adminViewAcct.setClear();
            adminViewAcct.setVisible(true);
            System.out.println("Admin View Account Window Opened");
        } else if (src == adminMain.btnLogout) {
            adminEntry.setClear();
            adminMain.setVisible(false);
            adminEntry.setVisible(true);
            System.out.println("Admin Main Menu Window Closed");
        } else if (src == adminCreate.btnSubmit) {
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

                    System.out.println("New Account Created for Customer " + adminCreate.fields[1].getText());
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
        } else if (src == adminCreate.btnCancel) {
            adminCreate.setVisible(false);
            adminMain.setVisible(true);
            System.out.println("Admin Create Account Window Closed");
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
            System.out.println("Admin Delete Account Window Closed");
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
            System.out.println("Admin Edit Account Window Closed");
        } else if (src == adminUpdate.btnUpdate) {
            try {
                String s = adminEdit.txtAcctNo.getText();

                String updateQuery = "UPDATE ClientInfo SET Name = '" + adminUpdate.fields[1].getText()
                        + "', Password = '" + adminUpdate.fields[2].getText() + "', AddressLine1 = '"
                        + adminUpdate.fields[3].getText() + "', AddressLine2 = '" + adminUpdate.fields[4].getText()
                        + "',City = '" + adminUpdate.fields[5].getText() + "', Province = '"
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
                System.out.println("Admin Update Account Window Closed");
            } catch (SQLException sqle) {
                System.out.println("Error" + sqle);
            }

        } else if (src == adminUpdate.btnCancel) {
            adminUpdate.setVisible(false);
            adminMain.setVisible(true);
            System.out.println("Admin Update Account Window Closed");
        }
    }

    public static void main(String args[]) {
        try {
            client_port = Integer.parseInt("4444");
        } catch (NumberFormatException e) {
            System.err.println("Wrong number for a port -> " + e);
            System.exit(1);
        }

        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host - probably localhost with no IP!");
        }

        System.out.println("Server is running on host: " + localHost);
        System.out.println("Waiting clients on port: " + client_port);
        new Server();
    }

    private void closeApplication() {
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
        if (clients.contains(clientToDelete)) {
            pause(1500);
            clients.remove(clientToDelete); // remove from vector.
            lblRunning.setText("Currently logged: " + clients.size() + " client(s).");
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'stateChanged'");
    }
}