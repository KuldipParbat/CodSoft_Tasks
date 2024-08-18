package com.atm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.atm.database.DBManager;
import com.atm.entity.AccountDetails;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ChangePin {

	private JFrame frame;
	private JTextField oldPin;
	private JTextField newPin;

	DBManager db = new DBManager();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePin window = new ChangePin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public ChangePin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setEnabled(false);
		internalFrame.setBounds(70, 40, 290, 155);
		frame.getContentPane().add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel sucessMsg = new JLabel("PIN Changed Successfully");
		sucessMsg.setFont(new Font("Tahoma", Font.BOLD, 15));
		sucessMsg.setBounds(38, 28, 211, 58);
		internalFrame.getContentPane().add(sucessMsg);
		internalFrame.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		oldPin = new JTextField();
		oldPin.setBounds(204, 64, 140, 35);
		panel.add(oldPin);
		oldPin.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Current PIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(70, 67, 109, 29);
		panel.add(lblNewLabel);
		
		JLabel lblEnterNewPin = new JLabel("Enter New PIN");
		lblEnterNewPin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterNewPin.setBounds(85, 124, 109, 29);
		panel.add(lblEnterNewPin);
		
		newPin = new JTextField();
		newPin.setColumns(10);
		newPin.setBounds(204, 122, 140, 35);
		panel.add(newPin);
		
		JButton btnNewButton = new JButton("Change PIN");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(148, 188, 125, 35);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(10, 218, 89, 32);
		panel.add(btnNewButton_1);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(335, 218, 89, 32);
		panel.add(btnBack);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ATMapplication atm = new ATMapplication();
				    AccountDetails accountDetails = db.balanceCheck(atm.getAccount());
				    
				    if (accountDetails != null) {
				       
				        
				    	int enteredPin = Integer.parseInt(oldPin.getText());
						int newEnteredPin = Integer.parseInt(newPin.getText());
						int originalPin = accountDetails.getPin();
				        
				        if (enteredPin == originalPin) {
				        	db.changePIN(accountDetails.getAccountNumber(), newEnteredPin);
				        	internalFrame.setVisible(true);
							
				        } else {
				        	sucessMsg.setText("Incorrect PIN");
							internalFrame.setVisible(true);
				        }
				    } else {
				    	sucessMsg.setText("Account not found.");
				    	internalFrame.setVisible(true);
				    }
				} catch (SQLException e1) {
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
