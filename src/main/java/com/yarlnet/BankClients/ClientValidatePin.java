package com.yarlnet.BankClients;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class ClientValidatePin extends JFrame implements ActionListener {

	JPanel mainPanel = new JPanel(),
			centerPanel = new JPanel(),
			bottomPanel = new JPanel();

	JButton logIn;
	JButton cancelLogIn;
	JPasswordField txtPin;

	JLabel display;

	ClientLog clientLg;
	Color color1;
	Font font1;
	Color color2;
	Color color3;
	Font font2;
	String info;
	JLabel lblLeft;
	JLabel lblRight;

	public ClientValidatePin(ClientLog temp)// Server temp
	{
		super("Network Bank ");
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

		logIn = new JButton("OK");
		cancelLogIn = new JButton("Cancel");

		lblLeft = new JLabel("           ");
		lblRight = new JLabel("           ");

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
		mainPanel.add(lbl1, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);
		setContentPane(mainPanel);

		setSize(300, 115);
		setBounds(200, 45, 300, 115);
		setResizable(false);
		setVisible(false);

	}

	public void actionPerformed(ActionEvent e) {

	}

	public void setVVVisible() {
		setVisible(true);
	}

	public void setInVVVisible() {
		setVisible(false);
	}

	public void setClear() {
		txtPin.setText("");
		txtPin.setFocusable(true);
	}

}