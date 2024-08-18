package com.atm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.atm.database.DBManager;
import com.atm.entity.AccountDetails;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DepositeCash {

	private JFrame frame;
	private JTextField accountNumber;
	private JTextField totalAmount;
	private JTextField depositor;

	DBManager db = new DBManager();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositeCash window = new DepositeCash();
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
	public DepositeCash() {
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
		
		JLabel lblNewLabel = new JLabel("Cash Deposite");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 21));
		lblNewLabel.setBounds(112, 11, 229, 38);
		panel.add(lblNewLabel);
		
		JLabel accountNumberLabel = new JLabel("Enter A/C No.");
		accountNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		accountNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountNumberLabel.setBounds(36, 56, 87, 25);
		panel.add(accountNumberLabel);
		
		accountNumber = new JTextField();
		accountNumber.setBounds(164, 58, 177, 25);
		panel.add(accountNumber);
		accountNumber.setColumns(10);
		
		JLabel totalAmountLabel = new JLabel("Total Amount");
		totalAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalAmountLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalAmountLabel.setBounds(36, 92, 87, 25);
		panel.add(totalAmountLabel);
		
		totalAmount = new JTextField();
		totalAmount.setColumns(10);
		totalAmount.setBounds(164, 94, 177, 25);
		panel.add(totalAmount);
		
		JLabel depositorLabel = new JLabel("Name of Depositor");
		depositorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		depositorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		depositorLabel.setBounds(36, 130, 118, 25);
		panel.add(depositorLabel);
		
		depositor = new JTextField();
		depositor.setColumns(10);
		depositor.setBounds(164, 132, 177, 25);
		panel.add(depositor);
		
		JButton btnNewButton = new JButton("Deposite Cash");
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		btnNewButton.setForeground(SystemColor.desktop);
		btnNewButton.setBounds(269, 183, 155, 38);
		panel.add(btnNewButton);
		
		JButton backButton_1 = new JButton("BACK");
		backButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		backButton_1.setBounds(311, 225, 113, 36);
		panel.add(backButton_1);
		
		JButton exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(10, 225, 113, 36);
		panel.add(exitButton);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ATMapplication atm = new ATMapplication();
				    AccountDetails accountDetails = db.balanceCheck(atm.getAccount());
				   
				
				if (accountDetails != null) {
			        
					String name = depositor.getText();
					long balance = Long.parseLong(totalAmount.getText());
					int account = Integer.parseInt(accountNumber.getText());
					
					db.deposit(account, balance);
					db.storeTransaction(account, name,balance, "Deposite");
					sucessMsg.setText("Cash Deposite Successfully");
					internalFrame.setVisible(true);
					btnNewButton.setVisible(false);
					Timer timer = new Timer(3000, new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    Home home = new Home();
		                    home.getFrame().setVisible(true);
		                    internalFrame.setVisible(false);
		                }
		            });
		            timer.setRepeats(false);  
		            timer.start();
		
			    } else {
			        sucessMsg.setText("Account not found.");
			        internalFrame.setVisible(true);
			        frame.setVisible(false);
			        Timer timer = new Timer(3000, new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    System.exit(0);
		                }
		            });
		            timer.setRepeats(false);  
		            timer.start();
			    }
			} catch (SQLException e1) {
			    e1.printStackTrace();
			}
				
			}
		});
	}
	
	
	public JFrame getFrame() {
		return frame;
	}
}
