package controller;

import javafx.stage.Stage;
import model.Account;
import model.Customer;
import view.BankingView;
import view.LoginView;

public class BankingController {
    private Stage primaryStage;
    private LoginView loginView;
    private BankingView bankingView;
    private Customer activeCustomer;
    private Account savingsAccount;
    private Account checkingAccount;

    public BankingController(Stage primaryStage, Customer sampleCustomer, Account savingsAccount, Account checkingAccount) {
        this.primaryStage = primaryStage;
        this.activeCustomer = sampleCustomer;
        this.savingsAccount = savingsAccount;
        this.checkingAccount = checkingAccount;

        this.loginView = new LoginView();
        this.bankingView = new BankingView();

        attachEventHandlers();
    }

    public void showLoginScreen() {
        loginView.setMessage("");
        primaryStage.setTitle("Banking System - Login");
        primaryStage.setScene(loginView.getScene());
        primaryStage.show();
    }

    private void attachEventHandlers() {
        // 1. Login button action
        loginView.getLoginButton().setOnAction(event -> handleLogin());

        // 2. Submit transaction button action
        bankingView.getSubmitButton().setOnAction(event -> handleTransaction());

        // 3. Logout button action
        bankingView.getLogoutButton().setOnAction(event -> showLoginScreen());
    }

    private void handleLogin() {
        String email = loginView.getEmailInput().trim();
        String password = loginView.getPasswordInput();

        if (email.isEmpty() || password.isEmpty()) {
            loginView.setMessage("Please enter both email and password.");
            return;
        }

        // Replace or match with your Customer authentication check if needed
        if (activeCustomer != null) {
            bankingView.setCustomerName(activeCustomer.getFullName());
            refreshBalances();
            bankingView.setStatusMessage("");
            primaryStage.setTitle("Banking System - Dashboard");
            primaryStage.setScene(bankingView.getScene());
        } else {
            loginView.setMessage("Invalid credentials.");
        }
    }

    private void handleTransaction() {
        String accountType = bankingView.getSelectedAccount();
        String actionType = bankingView.getSelectedTransactionType();
        String amountText = bankingView.getAmountInput().trim();

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                bankingView.setStatusMessage("Enter an amount greater than 0.");
                return;
            }
        } catch (NumberFormatException ex) {
            bankingView.setStatusMessage("Please enter a valid numeric amount.");
            return;
        }

        Account targetAccount = "Savings".equals(accountType) ? savingsAccount : checkingAccount;
        Account otherAccount = "Savings".equals(accountType) ? checkingAccount : savingsAccount;

        if ("Deposit".equals(actionType)) {
            targetAccount.deposit(amount);
            bankingView.setStatusMessage(String.format("Deposited BWP %.2f into %s.", amount, accountType));
            bankingView.appendTransactionRecord(String.format("DEPOSIT | %s | BWP %.2f", accountType, amount));
        } else if ("Withdraw".equals(actionType)) {
            // Adjust if your withdraw() returns boolean or throws an exception
            targetAccount.withdraw(amount);
            bankingView.setStatusMessage(String.format("Withdrew BWP %.2f from %s.", amount, accountType));
            bankingView.appendTransactionRecord(String.format("WITHDRAW | %s | BWP %.2f", accountType, amount));
        } else if ("Transfer".equals(actionType)) {
            targetAccount.transfer(otherAccount, amount);
            String otherName = "Savings".equals(accountType) ? "Checking" : "Savings";
            bankingView.setStatusMessage(String.format("Transferred BWP %.2f from %s to %s.", amount, accountType, otherName));
            bankingView.appendTransactionRecord(String.format("TRANSFER | %s -> %s | BWP %.2f", accountType, otherName, amount));
        }

        refreshBalances();
        bankingView.clearAmountInput();
    }

    private void refreshBalances() {
        bankingView.setSavingsBalance(savingsAccount.getBalance());
        bankingView.setCheckingBalance(checkingAccount.getBalance());
    }
}