package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class AdminEntryLevel extends JFrame implements ActionListener {

    /* Instance Variables */
    JPanel mainPanel = new JPanel(),
            upperPanel = new JPanel(),
            centerPanel = new JPanel(),
            buttonPanel = new JPanel();

    JButton logIn;
    JButton cancelLogIn;
    JTextField txtID;
    JPasswordField pField;
    JLabel display, display2;

    Server server;
    // JFrame frame;
    Color color1;
    Font font1;
    Color color2;
    Color color3;
    Font font2;
    JLabel lblLeft;
    JLabel lblRight;

    public AdminEntryLevel(Server temp) {

        server = temp;
        Initialize();

    }

    public void Initialize() {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Couldn't use the system "
                    + "look and feel: " + e);
        }

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // processing window events:
        WindowListener L = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                server.btnAdministrator.setEnabled(true);
                // closeApplication();
            }
        };

        addWindowListener(L);

        logIn = new JButton("Log In");// .setFont(font);
        cancelLogIn = new JButton("Cancel");// .setFont(font);

        lblLeft = new JLabel("           ");
        lblRight = new JLabel("           ");

        JLabel lbl1 = new JLabel("Administrator Verifications", SwingConstants.CENTER);
        lbl1.setFont(new Font("", Font.BOLD, 12));
        lbl1.setSize(5, 4);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel = new JPanel(new GridLayout(3, 3, 8, 8));
        upperPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        logIn.addActionListener(server);
        cancelLogIn.addActionListener(server);
        centerPanel.add(new JLabel(" User ID :"));// .setFont(font));

        txtID = new JTextField("", 15);
        txtID.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        centerPanel.add(txtID);
        centerPanel.add(new JLabel(" Password     :"));// .setFont(font));
        pField = new JPasswordField("", 10);
        pField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centerPanel.add(pField);
        centerPanel.add(logIn);
        centerPanel.add(cancelLogIn);
        upperPanel.add(lbl1);
        upperPanel.add(new JLabel(""));
        // mainPanel.add(display[0],BorderLayout.NORTH);
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        // mainPanel.add(display[2],BorderLayout.NORTH);
        // mainPanel.add(display[3],BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(lblLeft, BorderLayout.EAST);
        mainPanel.add(lblRight, BorderLayout.WEST);

        setContentPane(mainPanel);

        setSize(300, 155);

        setBounds(200, 45, 300, 155);

        setVisible(false);

    }

    public void actionPerformed(ActionEvent e) {

    }

    public void setVVVisible() {

        setVisible(true);

    }

    public void setInVVVisible() {

        setVisible(false);

    }

    public void setClear() {
        txtID.setText("");
        pField.setText("");
        txtID.setFocusable(true);
    }

}