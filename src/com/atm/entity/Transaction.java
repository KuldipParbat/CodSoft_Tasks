package com.atm.entity;


import java.sql.Timestamp;

public class Transaction {
    private int id;
    private String accountNumber;
    private Timestamp date;
    private String type;
    private double amount;

    public Transaction(int id, String accountNumber, Timestamp date, String type, double amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    // Getters and toString() method for displaying the transaction
    public int getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Account: %s, Date: %s, Type: %s, Amount: %.2f",
                             id, accountNumber, date, type, amount);
    }
}
