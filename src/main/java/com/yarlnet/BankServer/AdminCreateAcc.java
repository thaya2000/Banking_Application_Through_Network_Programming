/****
* File Name		:
* Author		:
* Professor		:
* Project		:
* Description	:
*//*


*/
/* Standard Java Packages *//*

package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

class AdminCreateAcc extends JFrame implements ActionListener {

	*/
/* Instance Variables *//*


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
	JLabel lblState = new JLabel("State :");
	JLabel lblPhone = new JLabel("Phone :");

	// AdminEntryLevel ObAdmin;
	JLabel lblLeft;
	JLabel lblRight;
	JTextField fields[];

	public AdminCreateAcc(Server temp)// AdminMainMenu temp )//Server temp

	{
		*/
/* Instantiate an object of this class to use as ActionListener of the GUI *//*

		// adminMain = temp ;
		server = temp;
		Initialize();

	}

	*/
/**
	 * Name : Initialize
	 * 
	 * @param : none
	 * @return : void
	 *         Description : develops the GUI
	 *//*

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

		*/
/*
		 * The default value is: HIDE_ON_CLOSE,
		 * 
		 *//*

		// frame.
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		// processing window events:
		WindowListener L = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				server.btnAdministrator.setEnabled(true); // closeApplication();
			}
		};
		// frame.
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

		*/
/*
		 * leftPanel.add(lblAcctNo);
		 * leftPanel.add(lblName);
		 * leftPanel.add(lblPassWord);
		 * leftPanel.add(lblAddrLn1);
		 * leftPanel.add(lblAddrLn2);
		 * leftPanel.add(lblCtyTwn);
		 * leftPanel.add(lblState);
		 * leftPanel.add(lblPhone);
		 * leftPanel.add(btnSubmit);
		 * leftPanel.add(btnCancel);
		 *//*

		lblAcctNo.setSize(5, 4);
		lblPin.setSize(5, 4);
		lblName.setSize(5, 4);
		lblPassWord.setSize(5, 4);
		lblAddrLn1.setSize(5, 4);
		lblAddrLn2.setSize(5, 4);
		lblCtyTwn.setSize(5, 4);
		lblState.setSize(5, 4);
		lblPhone.setSize(5, 4);

		// btnSubmit.setSize(10,5);
		// btnCancel.setSize(10,5);
		btnSubmit.addActionListener(server);
		btnCancel.addActionListener(server);
		for (int i = 0; i < 9; i++) {

			// fields[(2*i)+1] = new JTextField(labels[i]);
			fields[(i)] = new JTextField("", 15);
			// fields[(2*i)+1].setEditable(false);
			fields[i].setSize(5, 4);
			fields[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

		}

		// System.out.println(s);

		// fields[0].setEditable(false);
		// fields[8].setEditable(false);
		// rightPanel.add(new JLabel(" "));
		// rightPanel.add(new JLabel(" "));

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

		// it doesn't work with our JTabbedPane !!! ---> pack();
		// frame.
		setSize(400, 400);
		// frame.
		setBounds(150, 80, 400, 400);

		// show the window:
		// frame.
		setVisible(true);

	}

	*/
/**
	 * Name : actionPeformed
	 * 
	 * @param : ActionEvent
	 * @return : void
	 *         Description :
	 *//*

	public void actionPerformed(ActionEvent e) {
		*/
/*
		 * Object src=e.getSource();
		 * 
		 * if (src == btnSubmit){
		 * 
		 * 
		 * //PENDING.........
		 * 
		 * 
		 * 
		 * }else if (src==btnCancel){
		 * 
		 * 
		 * //frame.
		 * setVisible(false);
		 * closeApplication();
		 * 
		 * }
		 *//*

	}

	public void setClear() {
		for (int i = 0; i < 8; i++) {
			fields[i].setText("");
		}
		try {

			server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT AccountNo FROM ClientInfo");
			server.aDbase.uprs.last();
			s = server.aDbase.uprs.getLong(1) + 1;
			String t = Long.toString(s);
			System.out.println(t);
			fields[0].setText("    " + t);

			Random rand = new Random();
			double p = 10000 * rand.nextDouble();
			int pin = (int) p;
			if ((pin > 999) && (pin < 10000)) {
				fields[8].setText("    " + Integer.toString(pin));
			} else {
				fields[8].setText("    " + Integer.toString(pin + 1000));

			}

			server.aDbase.uprs.close();
		} catch (java.sql.SQLException sqle) {
			System.out.println("Error:" + sqle);
		}

	}

	public void setVVVisible() {
		// frame.
		setVisible(true);

	}

	public void setInVVVisible() {
		// frame.
		setVisible(false);

	}

}// end of class...
*/



/****
 * File Name     :
 * Author        :
 * Professor     :
 * Project       :
 * Description   :
 */

/* Standard Java Packages */
package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
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
	JLabel lblState = new JLabel("State :");
	JLabel lblPhone = new JLabel("Phone :");

	// AdminEntryLevel ObAdmin;
	JLabel lblLeft;
	JLabel lblRight;
	JTextField fields[];

	public AdminCreateAcc(Server temp)// AdminMainMenu temp )//Server temp

	{
		/* Instantiate an object of this class to use as ActionListener of the GUI */
		// adminMain = temp ;
		server = temp;
		Initialize();

	}

	/**
	 * Name : Initialize
	 *
	 * @param : none
	 * @return : void
	 * Description : develops the GUI
	 */
	public void Initialize() {

		// frame = new JFrame();
		// look & feel setup:
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Couldn't use the system " + "look and feel: " + e);
		}

		/*
		 * The default value is: HIDE_ON_CLOSE,
		 *
		 */
		// processing window events:
		WindowListener L = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				server.btnAdministrator.setEnabled(true); // closeApplication();
			}
		};
		// frame.
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

		// Set font size slightly bigger for labels
		Font labelFont = lblAcctNo.getFont();
		lblAcctNo.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() + 1));
		lblPin.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() + 1));
		lblName.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() + 1));
		lblPassWord.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() + 1));
		lblAddrLn1.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() + 1));
		lblAddrLn2.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() + 1));
		lblCtyTwn.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() + 1));
		lblState.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() + 1));
		lblPhone.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() + 1));

		btnSubmit.addActionListener(server);
		btnCancel.addActionListener(server);
		for (int i = 0; i < 9; i++) {

			fields[(i)] = new JTextField("", 15);
			fields[i].setSize(5, 4);
			fields[i].setBackground(Color.LIGHT_GRAY); // Set text box color to gray
			fields[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

		}

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
		lbl1.setFont(new Font("", Font.BOLD, 14)); // Increase font size slightly
		lbl1.setSize(5, 4);

		upperPanel.add(lbl1);
		upperPanel.add(new JLabel(" ", SwingConstants.CENTER));
		upperPanel.add(new JLabel(" ", SwingConstants.CENTER));

		mainPanel.add(upperPanel, BorderLayout.NORTH);
		bottomPanel.add(btnSubmit);
		bottomPanel.add(btnCancel);

		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);

		// it doesn't work with our JTabbedPane !!! ---> pack();
		// frame.
		setSize(400, 400);
		// frame.
		setBounds(150, 80, 400, 400);

		// show the window:
		// frame.
		setVisible(true);

	}

	/**
	 * Name : actionPeformed
	 *
	 * @param : ActionEvent
	 * @return : void
	 * Description :
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Object src=e.getSource();
		 *
		 * if (src == btnSubmit){
		 *
		 *
		 * //PENDING.........
		 *
		 *
		 *
		 * }else if (src==btnCancel){
		 *
		 *
		 * //frame.
		 * setVisible(false);
		 * closeApplication();
		 *
		 * }
		 */
	}

	public void setClear() {
		for (int i = 0; i < 8; i++) {
			fields[i].setText("");
		}
		try {

			server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT AccountNo FROM ClientInfo");
			server.aDbase.uprs.last();
			s = server.aDbase.uprs.getLong(1) + 1;
			String t = Long.toString(s);
			System.out.println(t);
			fields[0].setText("    " + t);

			Random rand = new Random();
			double p = 10000 * rand.nextDouble();
			int pin = (int) p;
			if ((pin > 999) && (pin < 10000)) {
				fields[8].setText("    " + Integer.toString(pin));
			} else {
				fields[8].setText("    " + Integer.toString(pin + 1000));

			}

			server.aDbase.uprs.close();
		} catch (java.sql.SQLException sqle) {
			System.out.println("Error:" + sqle);
		}

	}

	public void setVVVisible() {
		// frame.
		setVisible(true);

	}

	public void setInVVVisible() {
		// frame.
		setVisible(false);

	}

}// end of class...
