
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
import java.lang.*;

class ClientViewLog extends JFrame implements ActionListener {

    /* Instance Variables */

    JPanel mainPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel upperPanel = new JPanel();
    StringTokenizer values;
    int maximum;
    String ActNo;
    JTable table;
    JScrollPane jp;
    Object[][] Data;
    int count;
    JButton btnOK;
    ClientLog clientLg;
    String[] head = { "Date", "Time ", "Action", "Remarks" };

    // TableModel myData =
    public ClientViewLog(ClientLog temp) {
        super("Network Bank");
        count = 0;
        clientLg = temp;
        // Initialize();

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

        btnOK = new JButton("OK");

        // table = new JTable(Data,head);
        mainPanel = new JPanel(new BorderLayout());

        table = new JTable(Data, head);

        /*
         * TableColumn column = null;
         * for (int i = 0; i < 5; i++) {
         * column = table.getColumnModel().getColumn(i);
         * if (i == 0) {
         * column.setPreferredWidth(25); //sport column is bigger
         * } else if (i==1) {
         * column.setPreferredWidth(40);
         * } else if (i==2) {
         * column.setPreferredWidth(40);
         * }else if (i==3) {
         * column.setPreferredWidth(60);
         * } else if (i==4) {
         * column.setPreferredWidth(100);
         * }
         * }
         */
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setDragEnabled(false);
        jp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        // table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        upperPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnOK.addActionListener(this);

        upperPanel.add(new JLabel(" "));
        JLabel lbl1 = new JLabel("View Account Logs", SwingConstants.CENTER);
        lbl1.setFont(new Font("", Font.BOLD, 12));
        lbl1.setSize(5, 4);

        upperPanel.add(lbl1);
        upperPanel.add(new JLabel(" ", SwingConstants.CENTER));
        upperPanel.add(new JLabel(" ", SwingConstants.CENTER));
        upperPanel.add(new JLabel("      Account No: " + ActNo, SwingConstants.LEFT));
        upperPanel.add(new JLabel("      Total Account Logs : " + maximum, SwingConstants.LEFT));
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(jp, BorderLayout.CENTER);

        bottomPanel.add(btnOK);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        // it doesn't work with our JTabbedPane !!! ---> pack();
        // frame.
        setSize(400, 400);
        // frame.
        setBounds(150, 80, 400, 400);

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
        JButton src = (JButton) e.getSource();

        if (src == btnOK) {

            setVisible(false);
            clientLg.clientMain.setVisible(true);

        }

    }

    public void setActionCmd(String max, String acctNo) {
        maximum = Integer.parseInt(max);
        Data = new Object[maximum][4];
        ActNo = acctNo;

    }

}// end of class