
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
import java.lang.*;

class AdminViewReport extends JFrame implements ActionListener {

  /* Instance Variables */

  JPanel mainPanel = new JPanel();
  JPanel centerPanel = new JPanel();
  JPanel leftPanel = new JPanel();
  JPanel rightPanel = new JPanel();
  JPanel bottomPanel = new JPanel();
  JPanel upperPanel = new JPanel();
  JTable table;
  JScrollPane jp;
  // Object Data2 = new Object[50][6];

  JButton btnOK;
  Server server;
  String[] head = { "Date", "Time ", "AccountNo", "Action", "Remarks", "LogInIP" };

  // TableModel myData =
  public AdminViewReport(Server temp) {
    // server = temp;
    // Initialize();

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
        server.adminMain.setVisible(true);
        // closeApplication();
      }
    };
    // frame.
    addWindowListener(L);

    btnOK = new JButton("OK");

    // table = new JTable(Data,head);
    mainPanel = new JPanel(new BorderLayout());

    /*
     * table = new JTable( new AbstractTableModel() {
     * public String getColumnName(int col) {
     * return columnNames[col].toString();
     * }
     * public int getRowCount() {
     * return rowData.length;
     * }
     * public int getColumnCount() {
     * return columnNames.length;
     * }
     * public Object getValueAt(int row, int col) {
     * return rowData[row][col];
     * }
     * public boolean isCellEditable(int row, int col){
     * return false;
     * }
     * public void setValueAt(Object value, int row, int col) {
     * rowData[row][col] = value;
     * 
     * }
     * }
     * 
     * );
     */
    try {

      // server.aDbase.tmpuprs = server.aDbase.tmpStmt
      // .executeQuery("SELECT Date,Time,AccountNo,Action,Remarks,LogInIp FROM
      // ClientLogs");
      // server.aDbase.tmpuprs.last();

      // int iRow = server.aDbase.tmpuprs.getRow();
      // Object[][] Data = new Object[iRow + 1][6];
      // int i = 1;
      // server.aDbase.tmpuprs = server.aDbase.tmpStmt
      // .executeQuery("SELECT Date,Time,AccountNo,Action,Remarks,LogInIp FROM
      // ClientLogs ORDER BY ID DESC");
      // server.aDbase.tmpuprs.first();
      // while (server.aDbase.tmpuprs.next()) {
      // String date = server.aDbase.tmpuprs.getString(1);
      // String time = server.aDbase.tmpuprs.getString(2);
      // String AcctNo = Long.toString(server.aDbase.tmpuprs.getLong(3));
      // String action = server.aDbase.tmpuprs.getString(4);
      // String remark = server.aDbase.tmpuprs.getString(5);
      // String logIP = server.aDbase.tmpuprs.getString(6);
      // // Data[][i] = { time,AcctNo,action,remark,logIP };

      // Data[i][0] = date;
      // Data[i][1] = time;
      // Data[i][2] = AcctNo;
      // Data[i][3] = action;
      // Data[i][4] = remark;
      // Data[i][5] = logIP;

      // i++;
      // if (i > iRow)
      // break;
      // // System.out.println(Data);
      // // setValueAt(date,i,1);
      // // setValueAt(time,i,2);
      // // setValueAt(AcctNo,i,3);
      // // setValueAt(action,i,4);
      // // setValueAt(remark,i,5);
      // // setValueAt(logIP,i,6);
      // // System.out.println(date + "." + time + "." + AcctNo + "." + action + "." +
      // // remark + "." + logIP + "\n");
      // // i = i+1;
      // // table.add(Data);
      // }

      // table = new JTable(Data, head);

      // table.setEnabled(false);
      // table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      // table.setDragEnabled(false);
      // jp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      // ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
      // table.setPreferredScrollableViewportSize(new Dimension(500, 70));

    } catch (java.sql.SQLException sqle) {
      System.out.println("Error :" + sqle);

    }

    upperPanel = new JPanel(new GridLayout(4, 1, 5, 5));
    bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    btnOK.addActionListener(this);

    upperPanel.add(new JLabel(" "));
    JLabel lbl1 = new JLabel("View Account Logs", SwingConstants.CENTER);
    lbl1.setFont(new Font("", Font.BOLD, 12));
    lbl1.setSize(5, 4);

    upperPanel.add(lbl1);
    upperPanel.add(new JLabel(" ", SwingConstants.CENTER));
    upperPanel.add(new JLabel(" ", SwingConstants.CENTER));

    mainPanel.add(upperPanel, BorderLayout.NORTH);
    mainPanel.add(jp, BorderLayout.CENTER);

    bottomPanel.add(btnOK);

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
    JButton src = (JButton) e.getSource();

    if (src == btnOK) {

      setVisible(false);
      server.adminMain.setVisible(true);

    }

  }

  public void setActionCmd() {

    /*
     * try{
     * 
     * 
     * server.aDbase.tmpuprs = server.aDbase.tmpStmt.
     * executeQuery("SELECT Date,Time,AccountNo,Action,Remarks,LogInIp FROM ClientLogs"
     * );
     * server.aDbase.tmpuprs.last( );
     * 
     * int iRow1 = server.aDbase.tmpuprs.getRow();
     * Object[][] Data1 = new Object[iRow1+1][6];
     * int i = 0;
     * 
     * server.aDbase.tmpuprs = server.aDbase.tmpStmt.
     * executeQuery("SELECT Date,Time,AccountNo,Action,Remarks,LogInIp FROM ClientLogs ORDER BY Date DESC,Time Desc "
     * );
     * 
     * 
     * while ( server.aDbase.tmpuprs.next ( )) {
     * String date = server.aDbase.tmpuprs.getString(1) ;
     * String time = server.aDbase.tmpuprs.getString(2);
     * String AcctNo = Long.toString(server.aDbase.tmpuprs.getLong(3)) ;
     * String action = server.aDbase.tmpuprs.getString(4) ;
     * String remark = server.aDbase.tmpuprs.getString(5);
     * String logIP = server.aDbase.tmpuprs.getString(6);
     * // Data[][i] = { time,AcctNo,action,remark,logIP };
     * 
     * Data1[i][0] = date;
     * Data1[i][1] = time;
     * Data1[i][2] = AcctNo;
     * Data1[i][3] = action;
     * Data1[i][4] = remark;
     * Data1[i][5] = logIP;
     * 
     * i++;
     * if ( i > iRow1 ) break;
     * //System.out.println(Data);
     * //setValueAt(date,i,1);
     * // setValueAt(time,i,2);
     * //setValueAt(AcctNo,i,3);
     * // setValueAt(action,i,4);
     * // setValueAt(remark,i,5);
     * // setValueAt(logIP,i,6);
     * // System.out.println(date + "." + time + "." + AcctNo + "." + action + "." +
     * remark + "." + logIP + "\n");
     * // i = i+1;
     * // table.add(Data);
     * }
     * 
     * table = new JTable ( Data1, head );
     * 
     * jp = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
     * ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
     * mainPanel.updateUI();
     * 
     * }
     * catch(java.sql.SQLException sqle)
     * {
     * System.out.println("Error :" + sqle);
     * 
     * }
     */

    Initialize();

  }

}