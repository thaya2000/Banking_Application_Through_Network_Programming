package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

class AdminCreateAcc extends JFrame implements ActionListener {

	/* Instance Variables */

	long s;
	JPanel mainPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JPanel upperPanel = new JPanel();
	JButton btnSubmit;
	JButton btnCancel;
	Server server;
	// AdminMainMenu adminMain;

	JLabel lblAcctNo = new JLabel("Account No:");
	JLabel lblPin = new JLabel("Pin:");
	JLabel lblName = new JLabel("Name :");
	JLabel lblPassWord = new JLabel("Password :");
	JLabel lblAddrLn1 = new JLabel("Address line 1 :");
	JLabel lblAddrLn2 = new JLabel("Address Line 2 :");
	JLabel lblCtyTwn = new JLabel("City/Town :");
	JLabel lblState = new JLabel("Province :");
	JLabel lblPhone = new JLabel("Phone :");

	// AdminEntryLevel ObAdmin;
	JLabel lblLeft;
	JLabel lblRight;
	JTextField fields[];

	public AdminCreateAcc(Server temp)

	{
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

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		WindowListener L = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				server.btnAdministrator.setEnabled(true); // closeApplication();
			}
		};
		addWindowListener(L);

		btnSubmit = new JButton("Submit");
		btnCancel = new JButton("Cancel");
		lblLeft = new JLabel("               ");
		lblRight = new JLabel("               ");
		fields = new JTextField[9];

		mainPanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new GridLayout(9, 2, 5, 5));

		upperPanel = new JPanel(new GridLayout(4, 1, 5, 5));
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		lblAcctNo.setSize(5, 4);
		lblPin.setSize(5, 4);
		lblName.setSize(5, 4);
		lblPassWord.setSize(5, 4);
		lblAddrLn1.setSize(5, 4);
		lblAddrLn2.setSize(5, 4);
		lblCtyTwn.setSize(5, 4);
		lblState.setSize(5, 4);
		lblPhone.setSize(5, 4);

		btnSubmit.addActionListener(server);
		btnCancel.addActionListener(server);
		for (int i = 0; i < 9; i++) {
			fields[(i)] = new JTextField("", 15);
			fields[i].setSize(5, 4);
			fields[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

		fields[0].setEditable(false);
		fields[8].setEditable(false);

		centerPanel.add(lblAcctNo);
		centerPanel.add(fields[0]);
		centerPanel.add(lblName);
		centerPanel.add(fields[1]);
		centerPanel.add(lblPassWord);
		centerPanel.add(fields[2]);
		centerPanel.add(lblAddrLn1);
		centerPanel.add(fields[3]);
		centerPanel.add(lblAddrLn2);
		centerPanel.add(fields[4]);
		centerPanel.add(lblCtyTwn);
		centerPanel.add(fields[5]);
		centerPanel.add(lblState);
		centerPanel.add(fields[6]);
		centerPanel.add(lblPhone);
		centerPanel.add(fields[7]);
		centerPanel.add(lblPin);
		centerPanel.add(fields[8]);

		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);
		upperPanel.add(new JLabel(" "));
		JLabel lbl1 = new JLabel("Create Account", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);

		upperPanel.add(lbl1);
		upperPanel.add(new JLabel(" ", SwingConstants.CENTER));
		upperPanel.add(new JLabel(" ", SwingConstants.CENTER));

		mainPanel.add(upperPanel, BorderLayout.NORTH);
		bottomPanel.add(btnSubmit);
		bottomPanel.add(btnCancel);

		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);
		setSize(400, 400);
		setBounds(150, 80, 400, 400);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void setClear() {
		for (int i = 0; i < 8; i++) {
			fields[i].setText("");
		}
		try {
			server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT AccountNo FROM ClientInfo");
			if (server.aDbase.uprs.last()) {
				s = server.aDbase.uprs.getLong(1) + 1;
			} else {
				s = 1000000;
			}
			String t = Long.toString(s);

			System.out.println(t);
			fields[0].setText(t);

			Random rand = new Random();
			int pin = rand.nextInt(9000) + 1000; // generates a random four-digit number
			fields[8].setText(Integer.toString(pin));

			server.aDbase.uprs.close();
		} catch (java.sql.SQLException sqle) {
			System.out.println("Error:" + sqle);
		}

	}

	public void setVVVisible() {
		setVisible(true);
	}

	public void setInVVVisible() {
		setVisible(false);
	}

}
