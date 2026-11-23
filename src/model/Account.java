package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String accountType; // e.g., "Savings", "Checking"
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountNumber, String accountType, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = Math.max(0, initialBalance);
        this.transactions = new ArrayList<>();
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        this.transactions.add(new Transaction("TXN-" + System.currentTimeMillis(), "DEPOSIT", amount));
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        this.transactions.add(new Transaction("TXN-" + System.currentTimeMillis(), "WITHDRAWAL", amount));
        return true;
    }

    public boolean transfer(Account targetAccount, double amount) {
        if (targetAccount == null || !this.withdraw(amount)) {
            return false;
        }
        targetAccount.deposit(amount);
        return true;
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }
}