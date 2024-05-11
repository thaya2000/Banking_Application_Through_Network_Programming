package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class AdminUpdateAcc extends JFrame implements ActionListener {

	/* Instance Variables */

	JPanel mainPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JPanel upperPanel = new JPanel();
	JButton btnUpdate;
	JButton btnCancel;

	JLabel lblLabels[];

	JLabel lblLeft;
	JLabel lblRight;
	JTextField fields[];
	Server server;
	String labels[] = { "Account No :", "Name :", "Password :", "Address Line 1 :", "Address Line 2 :", "City/Town :",
			"Province :", "Phone :"
	};

	public AdminUpdateAcc(Server temp)// AdminMainMenu temp )//Server temp

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

		btnUpdate = new JButton("Update");
		btnCancel = new JButton("Cancel");
		lblLeft = new JLabel("               ");
		lblRight = new JLabel("               ");
		fields = new JTextField[8];
		lblLabels = new JLabel[8];

		mainPanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new GridLayout(8, 2, 5, 5));

		upperPanel = new JPanel(new GridLayout(4, 1, 5, 5));
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		btnUpdate.addActionListener(server);
		btnCancel.addActionListener(server);

		for (int i = 0; i < 8; i++) {
			lblLabels[i] = new JLabel(labels[i]);
			fields[(i)] = new JTextField("", 15);
			lblLabels[i].setSize(5, 4);
			fields[i].setSize(5, 4);
			fields[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			centerPanel.add(lblLabels[i]);
			centerPanel.add(fields[i]);
		}

		// centerPanel.add(new JLabel(""));
		bottomPanel.add(btnUpdate);

		// centerPanel.add(new JLabel(""));
		bottomPanel.add(btnCancel);

		// centerPanel.add(leftPanel);
		// centerPanel.add(rightPanel);

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);
		upperPanel.add(new JLabel(" "));
		JLabel lbl1 = new JLabel("Update Account", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);

		upperPanel.add(lbl1);
		upperPanel.add(new JLabel(" ", SwingConstants.CENTER));
		upperPanel.add(new JLabel(" ", SwingConstants.CENTER));

		mainPanel.add(upperPanel, BorderLayout.NORTH);

		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);

		// it doesn't work with our JTabbedPane !!! ---> pack();
		// frame.
		setSize(400, 400);
		// frame.
		setBounds(150, 80, 400, 400);

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

	public void closeApplication() {
		System.exit(0);
	}
	/*
	 * public static void main(String[] args)
	 * {
	 * new AdminUpdateAcc();
	 * }
	 */

}// end of class...
