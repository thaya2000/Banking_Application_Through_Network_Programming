package com.yarlnet.BankClients;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class ClientViewAcc extends JFrame implements ActionListener {

    JPanel mainPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JLabel lbl1 = new JLabel("\nView Account", SwingConstants.CENTER);

    JLabel lbl2 = new JLabel("Name of Accountholder");
    JLabel lbl3 = new JLabel("Account NO");
    JLabel lbl4 = new JLabel("Balance");
    Color color1 = new Color(0, 0, 0);
    Color color2 = new Color(255, 255, 255);

    JTextField lblName = new JTextField(" ");
    JTextField lblAcctNo = new JTextField(" ");
    JTextField lblBal = new JTextField("");

    JButton btnOk = new JButton("OK");
    JLabel lblLeft;
    JLabel lblRight;
    ClientLog clientLg;

    public ClientViewAcc(ClientLog temp)// Server temp
    {
        super("Network Bank");
        clientLg = temp;
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

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        WindowListener L = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                clientLg.clientMain.setVisible(true);
            }
        };
        addWindowListener(L);
        lblLeft = new JLabel("               ");
        lblRight = new JLabel("               ");
        lblName.setBackground(color2);
        lblAcctNo.setBackground(color2);
        lblBal.setBackground(color2);
        lblName.setBorder(BorderFactory.createLineBorder(color1));
        lblAcctNo.setBorder(BorderFactory.createLineBorder(color1));
        lblBal.setBorder(BorderFactory.createLineBorder(color1));

        lbl1.setFont(new Font("", Font.BOLD, 12));
        lblName.setEditable(false);
        lblAcctNo.setEditable(false);
        lblBal.setEditable(false);
        lblName.setSize(5, 4);
        lblAcctNo.setSize(5, 4);
        lblBal.setSize(5, 4);
        lbl2.setFont(new Font("", Font.PLAIN, 12));
        lbl3.setFont(new Font("", Font.PLAIN, 12));
        lbl4.setFont(new Font("", Font.PLAIN, 12));

        lbl1.setSize(5, 4);
        lbl2.setSize(5, 4);
        lbl3.setSize(5, 4);
        lbl4.setSize(5, 4);

        btnOk.updateUI();
        lbl1.updateUI();
        lbl2.updateUI();
        lbl3.updateUI();
        lbl4.updateUI();
        lblName.updateUI();
        lblAcctNo.updateUI();
        lblBal.updateUI();

        mainPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        leftPanel = new JPanel(new GridLayout(3, 1));
        rightPanel = new JPanel(new GridLayout(3, 1));
        btnOk.addActionListener(this);
        bottomPanel = new JPanel(new FlowLayout());

        mainPanel.add(lbl1, BorderLayout.NORTH);

        centerPanel.add(lbl2);
        centerPanel.add(lblName);
        centerPanel.add(lbl3);
        centerPanel.add(lblAcctNo);
        centerPanel.add(lbl4);

        centerPanel.add(lblBal);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        bottomPanel.add(btnOk);
        mainPanel.add(lblLeft, BorderLayout.EAST);
        mainPanel.add(lblRight, BorderLayout.WEST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setSize(400, 250);
        setBounds(220, 175, 400, 250);
        setResizable(false);
        setVisible(false);

    }

    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();

        if (src == btnOk) {

            setVisible(false);
            clientLg.clientMain.setVisible(true);

        }

    }

    public void setVVVisible() {
        setVisible(true);

    }

    public void setInVVVisible() {
        setVisible(false);

    }

}