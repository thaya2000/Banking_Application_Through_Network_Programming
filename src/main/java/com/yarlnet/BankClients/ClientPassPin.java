/****
* File Name		:
* Author		:
* Professor		:
* Project		:
* Description	:
*/

/* Standard Java Packages */
package com.yarlnet.BankClients;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

class ClientPassPin extends JFrame implements ActionListener {
    /* Instance Variables */
    JPanel mainPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JLabel lblhead = new JLabel("", SwingConstants.CENTER);

    JLabel lbl1 = new JLabel("Accountholder :");
    JLabel lbl2 = new JLabel("Account No :");
    JLabel lblVal1 = new JLabel("");
    JLabel lblVal2 = new JLabel("");
    JLabel lblVal3 = new JLabel("");

    JTextField lblName = new JTextField("");
    JTextField lblAcctNo = new JTextField("");
    JPasswordField txtVal1 = new JPasswordField("");
    JPasswordField txtVal2 = new JPasswordField("");
    JPasswordField txtVal3 = new JPasswordField("");
    // JPasswordField txtPassword;
    JButton btnOk = new JButton("OK");
    JButton btnCancel = new JButton("Cancel");
    JLabel lblLeft;
    JLabel lblRight;
    ClientLog clientLg;
    String info;

    public ClientPassPin(ClientLog temp)// Server temp
    {
        /* Instantiate an object of this class to use as ActionListener of the GUI */
        super("Network Bank");
        clientLg = temp;
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
                clientLg.clientMain.setVisible(true);
                // closeApplication();
            }
        };
        // frame.
        addWindowListener(L);

        lblhead.setFont(new Font("", Font.BOLD, 12));
        lblhead.setSize(5, 4);

        lblLeft = new JLabel("               ");
        lblRight = new JLabel("               ");
        lblName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblAcctNo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        txtVal1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        txtVal2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        txtVal3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        lblName.setEditable(false);
        lblAcctNo.setEditable(false);

        lblName.setSize(5, 4);
        lblAcctNo.setSize(5, 4);
        lblVal1.setSize(5, 4);
        lblVal2.setSize(5, 4);
        lblVal3.setSize(5, 4);
        txtVal1.setSize(5, 4);
        txtVal2.setSize(5, 4);
        txtVal3.setSize(5, 4);

        btnOk.updateUI();
        lblhead.updateUI();
        lbl1.updateUI();
        lbl2.updateUI();

        lblName.updateUI();
        lblAcctNo.updateUI();
        lblVal1.updateUI();
        lblVal2.updateUI();
        lblVal3.updateUI();
        txtVal1.updateUI();
        txtVal2.updateUI();
        txtVal3.updateUI();

        mainPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        btnOk.addActionListener(clientLg);
        btnCancel.addActionListener(clientLg);
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(lblhead, BorderLayout.NORTH);
        // .setFont(font));

        centerPanel.add(lbl1);
        centerPanel.add(lblName);

        centerPanel.add(lbl2);
        centerPanel.add(lblAcctNo);

        centerPanel.add(lblVal1);
        centerPanel.add(txtVal1);

        centerPanel.add(lblVal2);
        centerPanel.add(txtVal2);

        centerPanel.add(lblVal3);
        centerPanel.add(txtVal3);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        bottomPanel.add(btnOk);
        bottomPanel.add(btnCancel);
        mainPanel.add(lblLeft, BorderLayout.EAST);
        mainPanel.add(lblRight, BorderLayout.WEST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        // it doesn't work with our JTabbedPane !!! ---> pack();
        // frame.
        setSize(320, 275);
        // frame.
        setBounds(220, 175, 320, 275);

        // show the window:
        // frame.

        setVisible(false);

    }

    /**
     * Name : actionPeformed
     * 
     * @param : ActionEvent
     * @return : void
     *         Description :
     */
    public void actionPerformed(ActionEvent e) {

    }

}// end of class..