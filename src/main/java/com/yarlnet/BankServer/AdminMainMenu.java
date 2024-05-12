package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class AdminMainMenu extends JFrame implements ActionListener {

	/* Instance Variables */

	JPanel mainPanel = new JPanel(),
			centerPanel = new JPanel();

	JButton btnCreate;
	JButton btnDelete;
	JButton btnEdit;
	JButton btnViewAcct;
	// JButton btnViewReport;
	// JButton btnLnDet;
	JButton btnLogout;

	JLabel lblLeft;
	JLabel lblRight;
	Server server;

	/**
	 * Name :
	 * 
	 * @param :
	 * @return : void
	 *         Description : constructor with Argument
	 */

	public AdminMainMenu(Server temp)// Server temp
	{
		/* Instantiate an object of this class to use as ActionListener of the GUI */
		server = temp;
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
		// btnLnDet.addActionListener(server);
		// btnViewReport.addActionListener(server);
		btnLogout.addActionListener(server);


		JLabel lbl1 = new JLabel("Administrator : Main Menu", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);

		JPanel mainPanel = new JPanel(new BorderLayout());

		// Center panel for buttons with GridLayout for two columns
		JPanel centerPanel = new JPanel(new GridLayout(0, 2)); // 0 means any number of rows, 2 columns

		// Buttons
		JButton btnCreate = new JButton("Create");
		JButton btnDelete = new JButton("Delete");
		JButton btnEdit = new JButton("Edit");
		JButton btnViewAcct = new JButton("View Account");
		JButton btnLogout = new JButton("Logout");

		// Adding buttons to the center panel
		centerPanel.add(btnCreate);
		centerPanel.add(btnDelete);
		centerPanel.add(btnEdit);
		centerPanel.add(btnViewAcct);
		centerPanel.add(btnLogout);

		// Labels
//		JLabel lbl1 = new JLabel("Top Label");
//		JLabel lblLeft = new JLabel("Left Label");
//		JLabel lblRight = new JLabel("Right Label");

		// Adding components to mainPanel
		mainPanel.add(lbl1, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);


		setContentPane(mainPanel);

		// it doesn't work with our JTabbedPane !!! ---> pack();
		// frame.
		setSize(250, 250);
		setResizable(false);
		// frame.
		setBounds(200, 80, 250, 250);

		// show the window:
		// frame.
		// setVisible(true);

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

	public void setVVVisible() {
		// frame.
		setVisible(true);

	}

	public void setClear() {
	}

	public void setInVVVisible() {
		// frame.
		setVisible(false);

	}
	/*
	 * public static void main(String[] args)
	 * {
	 * new AdminEntryLevel();
	 * }
	 */

}// end of class...