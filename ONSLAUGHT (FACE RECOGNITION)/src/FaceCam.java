import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class FaceCam extends Application {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    private VideoCapture capture;
    private boolean cameraActive = true;

    @Override
    public void start(Stage primaryStage) {
        ImageView imageView = new ImageView();
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 640, 480);

        primaryStage.setTitle("Webcam Preview");
        primaryStage.setScene(scene);
        primaryStage.show();

        capture = new VideoCapture(0); // 0 means default camera
        if (!capture.isOpened()) {
            System.out.println("Error: Cannot open camera.");
            return;
        }

        Thread thread = new Thread(() -> {
            Mat frame = new Mat();
            while (cameraActive) {
                if (capture.read(frame)) {
                    Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2RGB);
                    BufferedImage image = matToBufferedImage(frame);
                    WritableImage fxImage = SwingFXUtils.toFXImage(image, null);
                    Platform.runLater(() -> imageView.setImage(fxImage));
                }
                try { Thread.sleep(33); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });
        thread.setDaemon(true);
        thread.start();

        primaryStage.setOnCloseRequest(e -> {
            cameraActive = false;
            capture.release();
        });
    }

    private BufferedImage matToBufferedImage(Mat mat) {
        int width = mat.width(), height = mat.height(), channels = mat.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        mat.get(0, 0, sourcePixels);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        image.getRaster().setDataElements(0, 0, width, height, sourcePixels);
        return image;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
