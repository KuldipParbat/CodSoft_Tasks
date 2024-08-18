package com.atm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.atm.entity.AccountDetails;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Home {

	private JFrame frame;

	static AccountDetails accountDetails = new AccountDetails();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
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
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton checkBalance = new JButton("Check Balance");
		checkBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BalanceCheck balanceCheck = new BalanceCheck();
				balanceCheck.getFrame().setVisible(true);
				frame.setVisible(false);
				
			}
		});
		checkBalance.setForeground(new Color(51, 102, 204));
		checkBalance.setFont(new Font("Tahoma", Font.BOLD, 13));
		checkBalance.setBackground(Color.LIGHT_GRAY);
		checkBalance.setBounds(10, 59, 125, 40);
		panel.add(checkBalance);
		
		JButton deposite = new JButton("Deposite");
		deposite.setForeground(new Color(51, 102, 204));
		deposite.setFont(new Font("Tahoma", Font.BOLD, 13));
		deposite.setBackground(Color.LIGHT_GRAY);
		deposite.setBounds(10, 128, 125, 40);
		panel.add(deposite);
		
		JButton transaction = new JButton("Mini Statement");
		transaction.setForeground(new Color(51, 102, 204));
		transaction.setFont(new Font("Tahoma", Font.BOLD, 11));
		transaction.setBackground(Color.LIGHT_GRAY);
		transaction.setBounds(299, 59, 125, 40);
		panel.add(transaction);
		
		JButton withrawal = new JButton("Withdrawal");
		withrawal.setForeground(new Color(51, 102, 204));
		withrawal.setFont(new Font("Tahoma", Font.BOLD, 13));
		withrawal.setBackground(Color.LIGHT_GRAY);
		withrawal.setBounds(299, 128, 125, 40);
		panel.add(withrawal);
		
		JButton Exit = new JButton("EXIT");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Exit.setForeground(new Color(51, 102, 204));
		Exit.setFont(new Font("Tahoma", Font.BOLD, 13));
		Exit.setBackground(Color.LIGHT_GRAY);
		Exit.setBounds(10, 195, 125, 40);
		panel.add(Exit);
		
		JButton changePIN = new JButton("Change PIN");
		changePIN.setForeground(new Color(51, 102, 204));
		changePIN.setFont(new Font("Tahoma", Font.BOLD, 13));
		changePIN.setBackground(Color.LIGHT_GRAY);
		changePIN.setBounds(299, 195, 125, 40);
		panel.add(changePIN);

		
		ImageIcon originalIcon = new ImageIcon("C:\\Users\\Kuldip\\eclipse_cooldeep\\Tasks\\Image\\bank icon_4091867.png");
		Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        
		JLabel logo = new JLabel();
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(resizedImage));
		logo.setBounds(119, 3, 193, 173);
		panel.add(logo);
		
		JLabel logoText = new JLabel("DIGITAL ATM");
		logoText.setHorizontalAlignment(SwingConstants.CENTER);
		logoText.setForeground(Color.DARK_GRAY);
		logoText.setFont(new Font("Tahoma", Font.BOLD, 15));
		logoText.setBounds(158, 152, 118, 32);
		panel.add(logoText);
		
		changePIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePin changePin = new ChangePin();
				changePin.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
		transaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MiniStatement statement = new MiniStatement();
				statement.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
		withrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Withdrawal withdrawal = new Withdrawal();
				withdrawal.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		deposite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositeCash depositeCash = new DepositeCash();
				depositeCash.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
	}
	
	
	
	public JFrame getFrame() {
		return frame;
		
	}

}
