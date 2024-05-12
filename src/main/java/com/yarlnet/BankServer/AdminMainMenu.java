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

	public AdminMainMenu(Server temp)// Server temp
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

		// processing window events:
		WindowListener L = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				server.btnAdministrator.setEnabled(true);

				// closeApplication();
			}
		};
		// frame.
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
		centerPanel = new JPanel(new GridLayout(6, 1, 8, 8));

		btnCreate.addActionListener(server);
		btnDelete.addActionListener(server);
		btnEdit.addActionListener(server);
		btnViewAcct.addActionListener(server);
		btnLogout.addActionListener(server);

		JLabel lbl1 = new JLabel("Administrator : Main Menu", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);

		JPanel mainPanel = new JPanel(new BorderLayout());

		// Center panel for buttons with GridLayout for two columns
		JPanel centerPanel = new JPanel(new GridLayout(0, 2)); // 0 means any number of rows, 2 columns

		// Adding buttons to the center panel
		centerPanel.add(btnCreate);
		centerPanel.add(btnDelete);
		centerPanel.add(btnEdit);
		centerPanel.add(btnViewAcct);
		centerPanel.add(btnLogout);

		// Labels

		// Adding components to mainPanel
		mainPanel.add(lbl1, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);

		setContentPane(mainPanel);
		setSize(250, 250);
		setResizable(true);
		setBounds(200, 80, 250, 250);

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

}// end of class...