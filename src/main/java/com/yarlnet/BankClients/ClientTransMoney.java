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

class ClientTransMoney extends JFrame implements ActionListener {

    /* Instance Variables */

    JPanel mainPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    // JLabel lbl1 = new JLabel("TRANSFER MONEY",SwingConstants.CENTER);
    JLabel lbl2 = new JLabel("Account No(From) :");
    JLabel lbl3 = new JLabel("Balance          :");
    JLabel lbl4 = new JLabel("Account No(To)   :");
    JLabel lbl5 = new JLabel("Amount           :");
    Color color1 = new Color(0, 0, 0);
    Color color2 = new Color(255, 255, 255);

    JTextField lblAcctNoFr = new JTextField("");
    JTextField lblBal = new JTextField("");
    JTextField txtAcctTo = new JTextField("");
    JTextField txtAmt = new JTextField("");

    JButton btnTrans = new JButton("Transfer");
    JButton btnCancel = new JButton("Cancel");

    JLabel lblLeft;
    JLabel lblRight;
    ClientLog clientLg;

    public ClientTransMoney(ClientLog temp)// Server temp
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

        // font = new Font("SansSerif",Font.BOLD, 14);
        // font1 = new Font ("TimesNewRoman", Font.BOLD , 14);
        // color1 = new Color(255,0,0);
        // color = new Color(0,200,240);
        // color2 = new Color(0,0,255);
        lblLeft = new JLabel("               ");
        lblRight = new JLabel("               ");

        lblAcctNoFr.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblBal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtAcctTo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtAmt.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // adminCreate = new AdminCreateAcc();
        // display[0] = new JLabel("---------------------------------------- " );
        // display = new JLabel(" Main Menu " );
        // display[2] = new JLabel("---------------------------------------- " );
        // display[3] = new JLabel("---------------------------------------- " );
        // display.setFont(font1);
        // display.setForeground(color2);
        // display.setText(
        // "-------------------------------------------------------------------------------------------------------------
        // \n ADMINISTRATOR
        // \n------------------------------------------------------------------------------------------------------------------------------------------------------------\nPlease
        // enter the following Data to continue..\n");
        // display.setEditable(false);
        // lbl1.setFont(new Font("",Font.BOLD,15));

        lblAcctNoFr.setEditable(false);
        lblBal.setEditable(false);

        lblAcctNoFr.setSize(5, 4);
        lblBal.setSize(5, 4);
        txtAcctTo.setSize(5, 4);
        txtAmt.setSize(5, 4);
        // txtAmt.setFont(new Font("",Font.PLAIN,12));
        lbl2.setFont(new Font("", Font.PLAIN, 12));
        lbl3.setFont(new Font("", Font.PLAIN, 12));
        lbl4.setFont(new Font("", Font.PLAIN, 12));
        lbl5.setFont(new Font("", Font.PLAIN, 12));
        // lbl1.setSize(5,4);
        lbl2.setSize(5, 4);
        lbl3.setSize(5, 4);
        lbl4.setSize(5, 4);
        lbl5.setSize(5, 4);
        JLabel lbl1 = new JLabel("Transfer Money", SwingConstants.CENTER);
        lbl1.setFont(new Font("", Font.BOLD, 12));
        lbl1.setSize(5, 4);

        btnTrans.updateUI();
        btnCancel.updateUI();
        lbl1.updateUI();
        lbl2.updateUI();
        lbl3.updateUI();
        lbl4.updateUI();
        lbl5.updateUI();

        lblAcctNoFr.updateUI();
        lblBal.updateUI();
        txtAcctTo.updateUI();
        txtAmt.updateUI();

        mainPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        leftPanel = new JPanel(new GridLayout(3, 1));
        rightPanel = new JPanel(new GridLayout(3, 1));
        btnTrans.addActionListener(clientLg);
        btnCancel.addActionListener(clientLg);
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        mainPanel.add(lbl1, BorderLayout.NORTH);
        // .setFont(font));
        bottomPanel.add(btnTrans);
        bottomPanel.add(btnCancel);

        centerPanel.add(lbl2);
        centerPanel.add(lblAcctNoFr);
        centerPanel.add(lbl3);
        centerPanel.add(lblBal);
        centerPanel.add(lbl4);
        centerPanel.add(txtAcctTo);
        centerPanel.add(lbl5);
        centerPanel.add(txtAmt);

        // centerPanel.add(leftPanel);
        // centerPanel.add(rightPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        mainPanel.add(lblLeft, BorderLayout.EAST);
        mainPanel.add(lblRight, BorderLayout.WEST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        // it doesn't work with our JTabbedPane !!! ---> pack();
        // frame.
        setSize(400, 250);
        // frame.
        setBounds(200, 80, 400, 250);

        // show the window:
        // frame.
        setVisible(false);
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
        Object src = e.getSource();

        if (src == btnTrans) {

            // PENDING.........

        } else if (src == btnCancel) {
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

}// end of class..