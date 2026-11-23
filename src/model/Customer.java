package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String fullName;
    private String email;
    private String passwordHash;
    private List<Account> accounts;

    public Customer(String customerId, String fullName, String email, String passwordHash) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        if (account != null) {
            this.accounts.add(account);
        }
    }

    public boolean validatePassword(String inputPassword) {
        return this.passwordHash != null && this.passwordHash.equals(inputPassword);
    }

    // Getters and Setters
    public String getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public List<Account> getAccounts() { return accounts; }
}