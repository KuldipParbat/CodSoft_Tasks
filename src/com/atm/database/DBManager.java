package com.atm.database;

import java.sql.*;

import com.atm.entity.AccountDetails;
import com.atm.entity.Transaction;

public class DBManager {
    private Connection con;

   
    public DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks", "root", "root");
            System.out.println("Connection established successfully.");
        } catch (Exception e) {
            System.out.println("Error during connection: " + e);
        }
    }

    public Connection getConnection(){
		return con;
    	
    }
    
    public AccountDetails balanceCheck(int accountNumber) throws SQLException {
        String query = "SELECT * FROM accounts WHERE accountNumber = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    long balance = rs.getLong("balance");
                    int pin = rs.getInt("pin");
                    return new AccountDetails(id, accountNumber, name, balance, pin);
                } else {
                    return null;
                }
            }
        }
    }

    // Method to execute update queries (deposit, withdrawal, change PIN)
    private boolean executeUpdate(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // Method to deposit an amount into a specific account
    public AccountDetails deposit(int accountNumber, long amount) throws SQLException {
        String query = "UPDATE accounts SET balance = balance + ? WHERE accountNumber = ?";
        if (executeUpdate(query, amount, accountNumber)) {
            return balanceCheck(accountNumber);
        } else {
            return null;
        }
    }

    // Method to withdraw an amount from a specific account
    public AccountDetails withdrawal(int accountNumber, long amount) throws SQLException {
        String balanceQuery = "SELECT balance FROM accounts WHERE accountNumber = ?";
        try (PreparedStatement stmt = con.prepareStatement(balanceQuery)) {
            stmt.setInt(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long currentBalance = rs.getLong("balance");
                    if (currentBalance >= amount) {
                        String updateQuery = "UPDATE accounts SET balance = balance - ? WHERE accountNumber = ?";
                        if (executeUpdate(updateQuery, amount, accountNumber)) {
                            return balanceCheck(accountNumber);
                        }
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                } else {
                    System.out.println("Account not found.");
                }
            }
        }
        return null;
    }

    // Method to change the PIN of a specific account
    public boolean changePIN(int accountNumber, int newPin) throws SQLException {
        String query = "UPDATE accounts SET pin = ? WHERE accountNumber = ?";
        return executeUpdate(query, newPin, accountNumber);
    }
    
    
    
 // Method to store the transaction details in the transaction table
    public void storeTransaction(int accountNumber, String name, long amount, String type) throws SQLException {
        String getIdQuery = "SELECT id FROM accounts WHERE accountNumber = ?";
        try (PreparedStatement stmt = con.prepareStatement(getIdQuery)) {
            stmt.setInt(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int accountId = rs.getInt("id");
                    String insertTransactionQuery = "INSERT INTO transaction (account_id, depositor_name, money, type) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement transactionStmt = con.prepareStatement(insertTransactionQuery)) {
                        transactionStmt.setInt(1, accountId);
                        transactionStmt.setString(2, name);
                        transactionStmt.setLong(3, amount);
                        transactionStmt.setString(4, type);
                        transactionStmt.executeUpdate();
                    }
                }
            }
        }
    }


    
//    private Transaction TransactionData() {
//        try (
//             Statement statement = con.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions")) {
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String accountNumber = resultSet.getString("account_number");
//                String date = resultSet.getString("date");
//                String type = resultSet.getString("type");
//                double amount = resultSet.getDouble("amount");
//                
//                return(new Transaction {id, accountNumber, date, type, amount});
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//		return null;
//    }
}
