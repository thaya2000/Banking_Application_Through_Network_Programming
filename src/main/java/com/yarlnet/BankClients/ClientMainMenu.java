package com.yarlnet.BankClients;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class ClientMainMenu extends JFrame implements ActionListener {
	JPanel mainPanel = new JPanel(),
			centerPanel = new JPanel();

	JButton btnViewAcc;
	JButton btnTransfer;
	JButton btnDeposit;
	JButton btnWithdraw;
	JButton btnAcctOp;
	JButton btnExit;
	JLabel lblLeft;
	JLabel lblRight;
	ClientLog clientLg;

	public ClientMainMenu(ClientLog temp)// Server temp
	{
		super("Network Bank ");
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

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		WindowListener L = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				clientLg.sendToServer("LOGGED_OUT." + clientLg.txtAcctNo.getText().trim());
				clientLg.clientMain.setVisible(false);
			}
		};
		addWindowListener(L);
		btnViewAcc = new JButton("View Account");
		btnTransfer = new JButton("Transfer Money");
		btnDeposit = new JButton("Deposit");
		btnWithdraw = new JButton("Withdraw");
		btnAcctOp = new JButton("Account Options");
		btnExit = new JButton("Exit");
		lblLeft = new JLabel("               ");
		lblRight = new JLabel("               ");

		btnViewAcc.setBackground(Color.YELLOW);
		btnTransfer.setBackground(Color.yellow);
		btnDeposit.setBackground(Color.YELLOW);
		btnWithdraw.setBackground(Color.yellow);
		btnAcctOp.setBackground(Color.yellow);
		btnExit.setBackground(Color.red);

		mainPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel = new JPanel(new GridLayout(3, 2, 8, 8));

		btnViewAcc.addActionListener(clientLg);
		btnTransfer.addActionListener(clientLg);
		btnDeposit.addActionListener(clientLg);
		btnAcctOp.addActionListener(clientLg);
		btnWithdraw.addActionListener(clientLg);
		btnExit.addActionListener(clientLg);
		JLabel lbl1 = new JLabel("Main Menu", SwingConstants.CENTER);

		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);

		mainPanel.add(lbl1, BorderLayout.NORTH);

		centerPanel.add(btnViewAcc);
		centerPanel.add(btnTransfer);
		centerPanel.add(btnDeposit);
		centerPanel.add(btnWithdraw);
		centerPanel.add(btnAcctOp);
		centerPanel.add(btnExit);

		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);
		mainPanel.add(new JLabel(""), BorderLayout.SOUTH);

		setContentPane(mainPanel);
		setSize(500, 150);
		setResizable(true);
		setBounds(220, 175, 500, 150);
		setVisible(false);

	}

	public void actionPerformed(ActionEvent e) {
	}

}