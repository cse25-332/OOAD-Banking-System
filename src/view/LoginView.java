package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView {
    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label messageLabel;
    private Scene scene;

    public LoginView() {
        Label titleLabel = new Label("Online Banking Login");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));

        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(12);

        Label emailLabel = new Label("Email:");
        emailField = new TextField();
        emailField.setPromptText("Enter your email");

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        formGrid.add(emailLabel, 0, 0);
        formGrid.add(emailField, 1, 0);
        formGrid.add(passwordLabel, 0, 1);
        formGrid.add(passwordField, 1, 1);

        loginButton = new Button("Login");
        loginButton.setPrefWidth(120);

        messageLabel = new Label();

        VBox root = new VBox(18, titleLabel, formGrid, loginButton, messageLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));

        scene = new Scene(root, 400, 280);
    }

    public Scene getScene() {
        return scene;
    }

    public String getEmailInput() {
        return emailField.getText();
    }

    public String getPasswordInput() {
        return passwordField.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}