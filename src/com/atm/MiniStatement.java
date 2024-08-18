package com.atm;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.atm.database.DBManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class MiniStatement {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    DBManager db = new DBManager();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MiniStatement window = new MiniStatement();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MiniStatement() {
        initialize();
        fetchTransactionData();  // Uncomment to fetch data on initialization
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.activeCaption);
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 564, 283);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Account Number", "Date", "Depositor Name", "Amount","Type"}
        );
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
        
        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        exitButton.setBounds(10, 314, 113, 36);
        frame.getContentPane().add(exitButton);
        
        JButton backButton_1 = new JButton("BACK");
        backButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Home home = new Home();
				home.getFrame().setVisible(true);
				frame.setVisible(false);
        	}
        });
        backButton_1.setBounds(461, 314, 113, 36);
        frame.getContentPane().add(backButton_1);
    }

    private void fetchTransactionData() {
        String query = "SELECT * FROM transaction t JOIN accounts a ON t.account_id = a.id WHERE a.accountNumber = ?";
        ATMapplication atm = new ATMapplication();
        System.out.println(atm.getAccount());
        
        try (Connection con = db.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setInt(1, atm.getAccount());
            System.out.println(atm.getAccount());
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int accountNumber = rs.getInt("accountNumber");
                    String timestamp = rs.getString("timestamp");
                    String depositorName = rs.getString("depositor_name");
                    long amount = rs.getLong("money");
                    String type=rs.getString("type");
                    tableModel.addRow(new Object[]{id, accountNumber, timestamp, depositorName, amount,type});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
	public JFrame getFrame() {
		return frame;
	}
}
