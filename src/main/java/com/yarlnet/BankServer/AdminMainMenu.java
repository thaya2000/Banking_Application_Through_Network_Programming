
/****
* File Name		:
* Author		:
* Professor		:
* Project		:
* Description	:
*/

/* Standard Java Packages */
package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

class AdminMainMenu extends JFrame implements ActionListener {

	/* Instance Variables */

	JPanel mainPanel = new JPanel(),
			centerPanel = new JPanel();

	JButton btnCreate;
	JButton btnDelete;
	JButton btnEdit;
	JButton btnViewAcct;
	JButton btnViewReport;
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

		// font = new Font("SansSerif",Font.BOLD, 14);
		// font1 = new Font ("TimesNewRoman", Font.BOLD , 14);
		// color1 = new Color(255,0,0);
		// color = new Color(0,200,240);
		// color2 = new Color(0,0,255);
		btnCreate = new JButton("Create Account");// .setFont(font);
		btnDelete = new JButton("Delete Account");// .setFont(font);
		btnEdit = new JButton("Edit Account");// .setFont(font);
		btnViewAcct = new JButton("View Account");
		// btnLnDet = new JButton("Loan Details");
		btnViewReport = new JButton("View Report");// .setFont(font);
		btnLogout = new JButton("Log Out");// .setFont(font);
		btnCreate.updateUI();
		btnDelete.updateUI();
		btnEdit.updateUI();
		btnViewAcct.updateUI();
		// btnLnDet.updateUI();
		btnViewReport.updateUI();
		btnLogout.updateUI();
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
		centerPanel = new JPanel(new GridLayout(6, 1, 8, 8));

		btnCreate.addActionListener(server);
		btnDelete.addActionListener(server);
		btnEdit.addActionListener(server);
		btnViewAcct.addActionListener(server);
		// btnLnDet.addActionListener(server);
		btnViewReport.addActionListener(server);
		btnLogout.addActionListener(server);
		JLabel lbl1 = new JLabel("Administrator : Main Menu", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);

		mainPanel.add(lbl1, BorderLayout.NORTH);// .setFont(font));
		centerPanel.add(btnCreate);
		centerPanel.add(btnDelete);
		centerPanel.add(btnEdit);
		centerPanel.add(btnViewAcct);
		// centerPanel.add(btnLnDet);
		centerPanel.add(btnViewReport);
		centerPanel.add(btnLogout);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);
		// mainPanel.add(lblRight,BorderLayout.SOUTH);

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