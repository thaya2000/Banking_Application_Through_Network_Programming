package com.yarlnet.BankServer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class AdminDeleteAcc extends JFrame implements ActionListener {

	JPanel mainPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JPanel upperPanel = new JPanel();
	JButton btnDelete;
	JButton btnCancel;
	JLabel lblLeft;
	JLabel lblRight;
	Server server;

	JLabel lblInfo = new JLabel("Enter Account no :");

	JTextField txtAcctNo;

	public AdminDeleteAcc(Server temp) {
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

		mainPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel = new JPanel(new GridLayout(3, 2, 8, 8));

		upperPanel = new JPanel(new GridLayout(4, 1, 2, 2));
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		txtAcctNo = new JTextField("", 15);
		btnDelete = new JButton("Delete");

		btnCancel = new JButton("Cancel");

		txtAcctNo.setSize(5, 4);
		lblInfo.setSize(5, 4);
		lblLeft = new JLabel("               ");
		lblRight = new JLabel("               ");
		txtAcctNo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnDelete.addActionListener(server);
		btnCancel.addActionListener(server);
		centerPanel.add(lblInfo);
		centerPanel.add(txtAcctNo);
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		bottomPanel.add(btnDelete);
		bottomPanel.add(btnCancel);
		upperPanel.add(new JLabel(" "));
		JLabel lbl1 = new JLabel("Delete Account", SwingConstants.CENTER);
		lbl1.setFont(new Font("", Font.BOLD, 12));
		lbl1.setSize(5, 4);

		upperPanel.add(lbl1);
		upperPanel.add(new JLabel(" ", SwingConstants.CENTER));
		upperPanel.add(new JLabel(" ", SwingConstants.CENTER));

		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(lblLeft, BorderLayout.EAST);
		mainPanel.add(lblRight, BorderLayout.WEST);

		mainPanel.add(upperPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);

		setSize(350, 225);
		setBounds(150, 80, 350, 225);

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
		txtAcctNo.setText("");
	}

	public void closeApplication() {
		System.exit(0);
	}

}
