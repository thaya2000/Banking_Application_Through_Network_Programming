package com.yarlnet.BankClients;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class ClientMainMenu extends JFrame implements ActionListener {
	/* Instance Variables */
	JPanel mainPanel = new JPanel(),
			centerPanel = new JPanel();

	JButton btnViewAcc;
	// JButton btnViewLogs;
	JButton btnTransfer;
	JButton btnDeposit;
	JButton btnWithdraw;
	JButton btnAcctOp;
	// JButton btnAvLoan;
	JButton btnExit;
	JLabel lblLeft;
	JLabel lblRight;
	ClientLog clientLg;

	/**
	 * Name :
	 * 
	 * @param :
	 * @return : void
	 *         Description : constructor with Argument
	 */

	public ClientMainMenu(ClientLog temp)// Server temp
	{
		super("Network Bank ");
		/* Instantiate an object of this class to use as ActionListener of the GUI */
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
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// processing window events:
		WindowListener L = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				clientLg.sendToServer("LOGGED_OUT." + clientLg.txtAcctNo.getText().trim());
				clientLg.clientMain.setVisible(false);
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
		btnViewAcc = new JButton("View Account");// .setFont(font);
		btnTransfer = new JButton("Transfer Money");// .setFont(font);
		btnDeposit = new JButton("Deposit");// .setFont(font);
		btnWithdraw = new JButton("Withdraw");
		// btnAvLoan = new JButton("Avail Loan");
		btnAcctOp = new JButton("Account Options");
		btnExit = new JButton("Log Out"); // .setFont(font);
		lblLeft = new JLabel("               ");
		lblRight = new JLabel("               ");

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

		mainPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel = new JPanel(new GridLayout(7, 1, 8, 8));

		btnViewAcc.addActionListener(clientLg);
		// btnViewLogs.addActionListener(clientLg);
		btnTransfer.addActionListener(clientLg);
		btnDeposit.addActionListener(clientLg);
		btnAcctOp.addActionListener(clientLg);
		// btnAvLoan.addActionListener(clientLg);
		// btnDeposit.setEnabled(false);
		btnWithdraw.addActionListener(clientLg);
		btnExit.addActionListener(clientLg);
		JLabel lbl1 = new JLabel("Main Menu", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);

		mainPanel.add(lbl1, BorderLayout.NORTH);// .setFont(font));

		centerPanel.add(btnViewAcc);
		// centerPanel.add(btnViewLogs);
		centerPanel.add(btnTransfer);
		centerPanel.add(btnDeposit);
		centerPanel.add(btnWithdraw);
		// centerPanel.add(btnAvLoan);
		centerPanel.add(btnAcctOp);
		centerPanel.add(btnExit);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);
		mainPanel.add(new JLabel(""), BorderLayout.SOUTH);

		setContentPane(mainPanel);

		// it doesn't work with our JTabbedPane !!! ---> pack();
		// frame.
		setSize(270, 340);
		setResizable(false);
		// frame.
		setBounds(220, 175, 270, 340);

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