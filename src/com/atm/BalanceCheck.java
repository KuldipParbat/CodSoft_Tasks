package com.atm;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import com.atm.database.DBManager;
import com.atm.entity.AccountDetails;

public class BalanceCheck {

	private JFrame frame;
	private JPasswordField pin;
	private JInternalFrame BalanceSheet;
	
	DBManager db = new DBManager();
	AccountDetails acc = new AccountDetails();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BalanceCheck window = new BalanceCheck();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public BalanceCheck() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
//		Main Panel
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
//		Internal Frame
		JInternalFrame BalanceSheet = new JInternalFrame("Account Info");
		BalanceSheet.setBackground(UIManager.getColor("Button.background"));
		BalanceSheet.setFrameIcon(null);
		BalanceSheet.setEnabled(false);
		BalanceSheet.setBounds(64, 33, 306, 182);
		panel.add(BalanceSheet);
		BalanceSheet.getContentPane().setLayout(null);
		
		JLabel accountNumber = new JLabel("Account Number :");
		accountNumber.setBounds(10, 11, 270, 24);
		BalanceSheet.getContentPane().add(accountNumber);
		
		JLabel accountName = new JLabel("A/C Holder Name :");
		accountName.setBounds(10, 35, 270, 24);
		BalanceSheet.getContentPane().add(accountName);
		
		JLabel mainLabel = new JLabel("Account Balance");
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainLabel.setBounds(87, 59, 116, 24);
		BalanceSheet.getContentPane().add(mainLabel);
		
		JLabel balanceLabel = new JLabel("");
		balanceLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		balanceLabel.setBounds(45, 94, 203, 35);
		BalanceSheet.getContentPane().add(balanceLabel);
			
//		Enter pin section
		pin = new JPasswordField();
		pin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pin.setForeground(Color.BLACK);
		pin.setBackground(Color.WHITE);
		pin.setBounds(109, 113, 213, 39);
		panel.add(pin);
		pin.setColumns(10);
		
		JButton pinButton = new JButton("Enter PIN");
		pinButton.setBackground(SystemColor.activeCaptionBorder);
		pinButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		pinButton.setBounds(155, 176, 119, 39);
		panel.add(pinButton);
		
//		Background Image
		ImageIcon originalIcon = new ImageIcon("C:\\Users\\Kuldip\\eclipse_cooldeep\\Tasks\\Image\\bank icon_4091867.png");
		Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        
		
       
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(resizedImage));
		lblNewLabel.setBounds(76, 0, 279, 258);
		
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("New label");
		label.setBounds(174, 197, 98, 31);
		panel.add(label);
		
		
//		Exit Button
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(10, 218, 89, 32);
		panel.add(btnNewButton);
		
		
//		Back Button
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
		
//		Pin Button Action
		pinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ATMapplication atm = new ATMapplication();
				    AccountDetails accountDetails = db.balanceCheck(atm.getAccount());
				    
				    if (accountDetails != null) {
				        BalanceSheet.setVisible(true);
				        pinButton.setVisible(false);
				        
				        int enteredPin = Integer.parseInt(pin.getText());
				        int originalPin = accountDetails.getPin();
				        
				        if (enteredPin == originalPin) {
				            accountName.setText("A/C Holder's Name: " + accountDetails.getName());
				            accountNumber.setText("Account Number: " + accountDetails.getAccountNumber());
				            balanceLabel.setText(String.valueOf(accountDetails.getBalance()));
				        } else {
				            accountName.setVisible(false);
				            accountNumber.setVisible(false);
				            mainLabel.setText("Incorrect PIN");

				            Timer timer = new Timer(3000, new ActionListener() {
				                @Override
				                public void actionPerformed(ActionEvent e) {
				                    System.exit(0);
				                }
				            });
				            timer.setRepeats(false);  // Only execute once
				            timer.start();
				        }
				    } else {
				        mainLabel.setText("Account not found.");
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
