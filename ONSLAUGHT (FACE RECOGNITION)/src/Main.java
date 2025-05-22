import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {
    private Stage window;
    private Scene loginScene, dashboardScene;
    private String loggedInUser;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        // --- Login Grid UI ---
        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(30));
        loginGrid.setVgap(15);
        loginGrid.setHgap(10);
        loginGrid.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Onslaught Login");
        titleLabel.setFont(Font.font("Arial", 24));
        GridPane.setColumnSpan(titleLabel, 2);
        GridPane.setHalignment(titleLabel, HPos.CENTER);

        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button faceLoginButton = new Button("Login with Face");
        Button faceRegisterButton = new Button("Register with Face");

        Label messageLabel = new Label();

        // Style buttons
        String buttonStyle = "-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;";
        loginButton.setStyle(buttonStyle);
        faceLoginButton.setStyle(buttonStyle);
        faceRegisterButton.setStyle(buttonStyle);

        // Add elements to grid
        loginGrid.add(titleLabel, 0, 0, 2, 1);
        loginGrid.add(userLabel, 0, 1);
        loginGrid.add(usernameField, 1, 1);
        loginGrid.add(passLabel, 0, 2);
        loginGrid.add(passwordField, 1, 2);
        loginGrid.add(loginButton, 0, 3);
        loginGrid.add(faceLoginButton, 1, 3);
        loginGrid.add(faceRegisterButton, 0, 4, 2, 1);
        loginGrid.add(messageLabel, 0, 5, 2, 1);

        loginScene = new Scene(loginGrid, 400, 350);

        // --- Dashboard Layout ---
        VBox dashboardLayout = new VBox(20);
        dashboardLayout.setPadding(new Insets(30));
        dashboardLayout.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label();
        welcomeLabel.setFont(Font.font("Arial", 20));

        Button startGameBtn = new Button("Start Game");
        Button settingsBtn = new Button("Settings");
        Button logoutBtn = new Button("Logout");

        // Style buttons
        startGameBtn.setStyle(buttonStyle);
        settingsBtn.setStyle(buttonStyle);
        logoutBtn.setStyle(buttonStyle);

        dashboardLayout.getChildren().addAll(welcomeLabel, startGameBtn, settingsBtn, logoutBtn);
        dashboardScene = new Scene(dashboardLayout, 400, 300);

        // --- Button Actions ---

        // Login
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals("admin") && password.equals("1234")) {
                loggedInUser = username;
                welcomeLabel.setText("Welcome, " + loggedInUser + "!");
                usernameField.clear();
                passwordField.clear();
                messageLabel.setText("");
                window.setScene(dashboardScene);
            } else {
                messageLabel.setText("Invalid credentials.");
            }
        });

        // Face Login
        faceLoginButton.setOnAction(e -> {
            messageLabel.setText("Scanning face...");
            String faceUser = FaceLogin.loginWithFace();
            if (faceUser != null) {
                loggedInUser = faceUser;
                welcomeLabel.setText("Welcome, " + loggedInUser + "!");
                messageLabel.setText("");
                window.setScene(dashboardScene);
            } else {
                messageLabel.setText("Face not recognized. Try again.");
            }
        });

        // Face Registration
        faceRegisterButton.setOnAction(e -> FaceRegistration.registerUser());

        // Logout
        logoutBtn.setOnAction(e -> window.setScene(loginScene));

        // Settings
        settingsBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Settings coming soon!", ButtonType.OK);
            alert.showAndWait();
        });

        // Start Game
        startGameBtn.setOnAction(e -> launchGame());

        // Launch
        window.setScene(loginScene);
        window.setTitle("Onslaught UI");
        window.show();
    }

    private void launchGame() {
        try {
            String classpath = Paths.get(System.getProperty("user.dir"), "OnslaughtGame", "out", "production", "OnslaughtGame").toString();

            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    classpath,
                    "com.onslaught.shooterGame.Main"
            );
            pb.inheritIO();
            pb.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Failed to launch game: " + ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
