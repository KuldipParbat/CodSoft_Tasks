package com.atm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.atm.database.DBManager;
import com.atm.entity.AccountDetails;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class Withdrawal {

	private JFrame frame;
	private JTextField EnterAmount;
	private JTextField EnterPin;

	AccountDetails accountDetails = new AccountDetails();
	DBManager db = new DBManager();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdrawal window = new Withdrawal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Withdrawal() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setEnabled(false);
		internalFrame.setBounds(10, 40, 414, 155);
		frame.getContentPane().add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel sucessMsg = new JLabel("");
		sucessMsg.setHorizontalAlignment(SwingConstants.CENTER);
		sucessMsg.setFont(new Font("Tahoma", Font.BOLD, 15));
		sucessMsg.setBounds(10, 28, 378, 58);
		internalFrame.getContentPane().add(sucessMsg);
		internalFrame.setVisible(false);
		
		
		JPanel Amount_Panel = new JPanel();
		Amount_Panel.setBackground(SystemColor.activeCaption);
		Amount_Panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(Amount_Panel);
		Amount_Panel.setLayout(null);
		
		EnterAmount = new JTextField();
		EnterAmount.setBounds(117, 97, 195, 41);
		Amount_Panel.add(EnterAmount);
		EnterAmount.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("WITHDRAWAL");
		lblNewLabel.setBounds(134, 31, 165, 35);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 21));
		Amount_Panel.add(lblNewLabel);
		
	
		
		JButton AmountButton = new JButton("Enter Amount");
		AmountButton.setBounds(153, 170, 117, 35);
		
		Amount_Panel.add(AmountButton);
		
		JButton exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(10, 214, 113, 36);
		Amount_Panel.add(exitButton);
		
		JButton backButton_1 = new JButton("BACK");
		backButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		backButton_1.setBounds(311, 214, 113, 36);
		Amount_Panel.add(backButton_1);
		
		JPanel Pin_Panel = new JPanel();
		Pin_Panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(Pin_Panel);
		Pin_Panel.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("WITHDRAWAL");
		lblNewLabel1.setBounds(134, 31, 165, 35);
		lblNewLabel1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 21));
		Pin_Panel.add(lblNewLabel1);
		
		EnterPin = new JTextField();
		EnterPin.setBounds(117, 97, 195, 41);
		Pin_Panel.add(EnterPin);
		EnterPin.setColumns(10);
		
		JButton PinButton = new JButton("Enter PIN");
		PinButton.setBounds(153, 170, 117, 35);
		Pin_Panel.add(PinButton);
		
		
		AmountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pin_Panel.setVisible(true);
				Amount_Panel.setVisible(false);
			}
		});
		
		PinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				ATMapplication atm = new ATMapplication();
			    AccountDetails accountDetails;
				
				accountDetails = db.balanceCheck(atm.getAccount());
				
			    
			    if (accountDetails != null) {
			    	
			    
					Pin_Panel.setVisible(false);
					int amount = Integer.parseInt(EnterAmount.getText());
					int pin = Integer.parseInt(EnterPin.getText());
					
					
					if(pin == accountDetails.getPin()) {
						if(amount<accountDetails.getBalance()) {
							db.withdrawal(accountDetails.getAccountNumber(), amount);
							db.storeTransaction(accountDetails.getAccountNumber(), accountDetails.getName(),amount, "Withdrawal");
							internalFrame.setVisible(true);
							sucessMsg.setText( amount+" Amount Withdrawal Succesfully");
						}else {
							internalFrame.setVisible(true);
							sucessMsg.setText("Insufficient balance.");
						}
						
					}else {
						internalFrame.setVisible(true);
						sucessMsg.setText("Incorrect PIN");
						
					}
					
				}else {
					internalFrame.setVisible(true);
					sucessMsg.setText("Account not found.");
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Timer timer = new Timer(3000, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
					Home home = new Home();
					home.getFrame().setVisible(true);
					frame.setVisible(false);

	            }
	        });
	        timer.setRepeats(false);  // Only execute once
	        timer.start();
			}
		});
	}

	
	public JFrame getFrame() {
		return frame;
	}
}
