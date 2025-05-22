import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FaceAppUI extends Application {

    Label statusLabel = new Label("Welcome! Please choose an option.");

    @Override
    public void start(Stage primaryStage) {
        Button registerBtn = new Button("Register");
        Button loginBtn = new Button("Login");

        registerBtn.setOnAction(e -> {
            statusLabel.setText("Starting face registration...");
            // Call FaceRegistration in a new thread (implement similarly as login)
            new Thread(() -> {
                FaceRegistration.registerUser();  // Assuming you created this method similar to FaceLogin.loginWithFace()
                javafx.application.Platform.runLater(() -> statusLabel.setText("Face registration finished."));
            }).start();
        });

        loginBtn.setOnAction(e -> {
            statusLabel.setText("Starting face login...");
            new Thread(() -> {
                FaceLogin.loginWithFace();
                javafx.application.Platform.runLater(() -> statusLabel.setText("Face login finished."));
            }).start();
        });

        VBox root = new VBox(15, registerBtn, loginBtn, statusLabel);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-font-size: 14px;");

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("Face Recognition App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
