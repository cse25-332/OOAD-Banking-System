package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BankingView {
    private Label welcomeLabel;
    private Label savingsBalanceLabel;
    private Label checkingBalanceLabel;
    private ComboBox<String> accountSelector;
    private ComboBox<String> transactionTypeSelector;
    private TextField amountField;
    private Button submitButton;
    private Button logoutButton;
    private Label statusLabel;
    private TextArea transactionHistoryArea;
    private Scene scene;

    public BankingView() {
        // Header section
        welcomeLabel = new Label("Welcome, Customer");
        welcomeLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        logoutButton = new Button("Logout");

        HBox headerBar = new HBox(20, welcomeLabel, logoutButton);
        headerBar.setAlignment(Pos.CENTER_LEFT);

        // Account balances display
        savingsBalanceLabel = new Label("Savings Balance: BWP 0.00");
        checkingBalanceLabel = new Label("Checking Balance: BWP 0.00");
        VBox balanceBox = new VBox(6, savingsBalanceLabel, checkingBalanceLabel);
        balanceBox.setPadding(new Insets(10));
        balanceBox.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 4;");

        // Transaction input form
        GridPane actionGrid = new GridPane();
        actionGrid.setHgap(10);
        actionGrid.setVgap(10);

        accountSelector = new ComboBox<>();
        accountSelector.getItems().addAll("Savings", "Checking");
        accountSelector.setValue("Savings");

        transactionTypeSelector = new ComboBox<>();
        transactionTypeSelector.getItems().addAll("Deposit", "Withdraw", "Transfer");
        transactionTypeSelector.setValue("Deposit");

        amountField = new TextField();
        amountField.setPromptText("0.00");

        submitButton = new Button("Submit Transaction");

        actionGrid.add(new Label("Account:"), 0, 0);
        actionGrid.add(accountSelector, 1, 0);
        actionGrid.add(new Label("Action:"), 0, 1);
        actionGrid.add(transactionTypeSelector, 1, 1);
        actionGrid.add(new Label("Amount (BWP):"), 0, 2);
        actionGrid.add(amountField, 1, 2);
        actionGrid.add(submitButton, 1, 3);

        statusLabel = new Label();

        // Transaction history display
        Label historyTitle = new Label("Recent Transactions:");
        transactionHistoryArea = new TextArea();
        transactionHistoryArea.setEditable(false);
        transactionHistoryArea.setPrefHeight(130);

        VBox root = new VBox(14, headerBar, balanceBox, actionGrid, statusLabel, historyTitle, transactionHistoryArea);
        root.setPadding(new Insets(20));

        scene = new Scene(root, 520, 480);
    }

    public Scene getScene() {
        return scene;
    }

    public void setCustomerName(String name) {
        welcomeLabel.setText("Welcome, " + name);
    }

    public void setSavingsBalance(double balance) {
        savingsBalanceLabel.setText(String.format("Savings Balance: BWP %.2f", balance));
    }

    public void setCheckingBalance(double balance) {
        checkingBalanceLabel.setText(String.format("Checking Balance: BWP %.2f", balance));
    }

    public String getSelectedAccount() {
        return accountSelector.getValue();
    }

    public String getSelectedTransactionType() {
        return transactionTypeSelector.getValue();
    }

    public String getAmountInput() {
        return amountField.getText();
    }

    public void clearAmountInput() {
        amountField.clear();
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public void setStatusMessage(String message) {
        statusLabel.setText(message);
    }

    public void appendTransactionRecord(String record) {
        transactionHistoryArea.appendText(record + "\n");
    }
}