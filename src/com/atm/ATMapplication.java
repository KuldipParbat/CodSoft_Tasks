package com.atm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.atm.database.DBManager;
import com.atm.entity.AccountDetails;

import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.sql.SQLException;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class ATMapplication {

	private JFrame frame;
	private JTextField txtEnterAccountNumber;

	public static int account;

	AccountDetails acc = new AccountDetails();
	DBManager db = new DBManager();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMapplication window = new ATMapplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ATMapplication() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Kuldip\\eclipse_cooldeep\\Tasks\\Image\\bank icon_4091867.png"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setToolTipText("WELCOME");
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel welcome = new JLabel("WELCOME");
		welcome.setForeground(new Color(0, 128, 128));
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));
		welcome.setBounds(122, 11, 169, 77);
		panel.add(welcome);

		JLabel instruction = new JLabel("Please insert your card...");
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		instruction.setForeground(new Color(51, 51, 102));
		instruction.setFont(new Font("Tahoma", Font.PLAIN, 16));
		instruction.setBounds(132, 179, 182, 44);
		panel.add(instruction);

		ImageIcon originalIcon = new ImageIcon(
				"C:\\\\Users\\\\Kuldip\\\\Downloads\\\\—Pngtree—vector bank icon_4091867.png");
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		JLabel logo = new JLabel();
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(resizedImage));
		logo.setBounds(122, 44, 193, 173);
		panel.add(logo);

		JTextField txtEnterAccountNumber = new JTextField();
		txtEnterAccountNumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterAccountNumber.setText(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				handleAccountCheck(txtEnterAccountNumber.getText(), instruction);
				
			}
		});

		txtEnterAccountNumber.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		txtEnterAccountNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterAccountNumber.setText("Enter Account Number");
		txtEnterAccountNumber.setBounds(128, 217, 193, 33);
		panel.add(txtEnterAccountNumber);
		txtEnterAccountNumber.setColumns(10);

	}

	public void handleAccountCheck(String accountNumberText, JLabel instruction) {
		try {
			int accountNumber = Integer.parseInt(accountNumberText);
			AccountDetails accountDetails = db.balanceCheck(accountNumber);

			if (accountDetails != null) {
				// Assuming Home is your class where you want to pass the account number
				Home home = new Home();
				home.getFrame().setVisible(true);
				account = accountNumber;
				System.out.println(account);
				getAccount();
			} else {
				instruction.setText("Account not found.");
				Timer timer = new Timer(2000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						instruction.setText("Thank You...");
					}
				});
				timer.setRepeats(false); // Only execute once
				timer.start();
				
				Timer timer1 = new Timer(4000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				timer1.setRepeats(false); // Only execute once
				timer1.start();

			}
		} catch (NumberFormatException ex) {
			instruction.setText("Invalid account number.");
		} catch (SQLException e) {
			e.printStackTrace();
			instruction.setText("Database error.");
		}
	}

	public int getAccount() {
		return account;
	}

}
