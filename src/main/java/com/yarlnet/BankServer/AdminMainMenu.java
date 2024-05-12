package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class AdminMainMenu extends JFrame implements ActionListener {

	JPanel mainPanel = new JPanel(),
			centerPanel = new JPanel();

	JButton btnCreate;
	JButton btnDelete;
	JButton btnEdit;
	JButton btnViewAcct;
	JButton btnLogout;

	JLabel lblLeft;
	JLabel lblRight;
	Server server;

	public AdminMainMenu(Server temp) {
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
				server.btnAdministrator.setEnabled(true);
			}
		};

		addWindowListener(L);

		btnCreate = new JButton("Create Account");
		btnDelete = new JButton("Delete Account");
		btnEdit = new JButton("Edit Account");
		btnViewAcct = new JButton("View Account");
		btnLogout = new JButton("Log Out");

		btnCreate.updateUI();
		btnDelete.updateUI();
		btnEdit.updateUI();
		btnViewAcct.updateUI();
		btnLogout.updateUI();

		lblLeft = new JLabel("               ");
		lblRight = new JLabel("               ");

		mainPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel = new JPanel(new GridLayout(3, 2, 8, 8));
		JPanel logoutPanel = new JPanel(new FlowLayout());
		btnCreate.addActionListener(server);
		btnDelete.addActionListener(server);
		btnEdit.addActionListener(server);
		btnViewAcct.addActionListener(server);
		btnLogout.addActionListener(server);

		JLabel lbl1 = new JLabel("Administrator : Main Menu", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 30));
		lbl1.setSize(5, 4);

		JLabel lbl2 = new JLabel("", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 20));
		lbl1.setSize(5, 20);

		// JPanel mainPanel = new JPanel(new BorderLayout());
		btnCreate.setBackground(Color.YELLOW);
		btnDelete.setBackground(Color.yellow);
		btnEdit.setBackground(Color.YELLOW);
		btnViewAcct.setBackground(Color.yellow);
		btnLogout.setBackground(Color.red);

		centerPanel.add(btnCreate);
		centerPanel.add(btnDelete);
		centerPanel.add(btnEdit);
		centerPanel.add(btnViewAcct);

		logoutPanel.add(btnLogout);

		mainPanel.add(lbl1, BorderLayout.NORTH);
		mainPanel.add(lbl2, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(logoutPanel, BorderLayout.SOUTH);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);

		setContentPane(mainPanel);
		setSize(500, 300);
		setResizable(true);
		setBounds(250, 100, 500, 200);
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void setVVVisible() {
		setVisible(true);
	}

	public void setClear() {
	}

	public void setInVVVisible() {
		setVisible(false);
	}

}