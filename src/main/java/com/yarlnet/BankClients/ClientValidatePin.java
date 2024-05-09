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

class ClientValidatePin extends JFrame implements ActionListener {

	/* Instance Variables */
	JPanel mainPanel = new JPanel(),
			centerPanel = new JPanel(),
			bottomPanel = new JPanel();

	JButton logIn;
	JButton cancelLogIn;
	JPasswordField txtPin;

	JLabel display;

	ClientLog clientLg;
	// JFrame frame;
	Color color1;
	Font font1;
	Color color2;
	Color color3;
	Font font2;
	String info;
	JLabel lblLeft;
	JLabel lblRight;

	/**
	 * Name :
	 * 
	 * @param :
	 * @return : void
	 *         Description : constructor with Argument
	 */

	public ClientValidatePin(ClientLog temp)// Server temp
	{
		/* Instantiate an object of this class to use as ActionListener of the GUI */
		super("Network Bank ");
		// super("Validate Pin");
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
		logIn = new JButton("OK");// .setFont(font);
		cancelLogIn = new JButton("Cancel");// .setFont(font);

		lblLeft = new JLabel("           ");
		lblRight = new JLabel("           ");
		// display[0] = new JLabel("---------------------------------------- " );
		// display = new JLabel("Personal Identification
		// Number(PIN)",SwingConstants.CENTER );
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

		// logIn.updateUI();
		// cancelLogIn.updateUI();
		// fields[0].updateUI();
		// fields[1].updateUI();
		// display.updateUI();

		JLabel lbl1 = new JLabel("Validate PIN", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);

		mainPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel = new JPanel(new GridLayout(1, 2, 8, 8));
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		logIn.addActionListener(clientLg);
		cancelLogIn.addActionListener(clientLg);
		centerPanel.add(new JLabel(" Enter 4 digit PIN :"));// .setFont(font));

		txtPin = new JPasswordField("");
		txtPin.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		centerPanel.add(txtPin);
		bottomPanel.add(logIn);
		bottomPanel.add(cancelLogIn);
		// mainPanel.add(display[0],BorderLayout.NORTH);
		mainPanel.add(lbl1, BorderLayout.NORTH);
		// mainPanel.add(display[2],BorderLayout.NORTH);
		// mainPanel.add(display[3],BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);
		// set content pane:
		// frame.
		setContentPane(mainPanel);

		// it doesn't work with our JTabbedPane !!! ---> pack();
		// frame.
		setSize(300, 115);
		// frame.
		setBounds(200, 45, 300, 115);
		setResizable(false);
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

	public void setVVVisible() {
		// frame.
		setVisible(true);

	}

	public void setInVVVisible() {
		// frame.
		setVisible(false);

	}

	public void setClear() {
		txtPin.setText("");

		txtPin.setFocusable(true);
	}
	/*
	 * public static void main(String[] args)
	 * {
	 * new AdminEntryLevel();
	 * }
	 */

}// end of class...