
package com.yarlnet.BankClients;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class ClientAcctOptions extends JFrame implements ActionListener {

	JPanel mainPanel = new JPanel(),
			centerPanel = new JPanel();

	JButton btnChPass;
	JButton btnChPin;
	JButton btnTransfer;
	JButton btnCancel;

	JLabel lblLeft;
	JLabel lblRight;
	ClientLog clientLg;

	public ClientAcctOptions(ClientLog temp)// Server temp
	{
		/* Instantiate an object of this class to use as ActionListener of the GUI */
		super("Network Bank");
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

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		WindowListener L = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				clientLg.clientMain.setVisible(true);

			}
		};

		addWindowListener(L);

		btnChPass = new JButton("Change Password");
		btnChPin = new JButton("Change PIN");
		btnCancel = new JButton("Cancel");

		lblLeft = new JLabel("               ");
		lblRight = new JLabel("               ");

		mainPanel = new JPanel(new BorderLayout(10, 10));
		JPanel upperPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		centerPanel = new JPanel(new GridLayout(3, 1, 8, 8));

		btnChPass.addActionListener(clientLg);
		btnChPin.addActionListener(clientLg);
		btnCancel.addActionListener(clientLg);
		JLabel lbl1 = new JLabel("Account Options", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);
		upperPanel.add(lbl1);
		upperPanel.add(new JLabel(" ", SwingConstants.CENTER));
		mainPanel.add(upperPanel, BorderLayout.NORTH);

		centerPanel.add(btnChPass);
		centerPanel.add(btnChPin);
		centerPanel.add(btnCancel);

		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);
		mainPanel.add(new JLabel(" "), BorderLayout.SOUTH);

		setContentPane(mainPanel);

		setSize(270, 210);
		setResizable(false);

		setBounds(220, 175, 270, 210);

		setVisible(false);

	}

	public void actionPerformed(ActionEvent e) {

	}

}