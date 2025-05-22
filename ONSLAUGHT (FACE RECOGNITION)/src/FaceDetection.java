import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class FaceDetection {

    static {
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            System.out.println("✅ OpenCV loaded successfully: " + Core.VERSION);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("❌ OpenCV native library not found: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {

        // ✅ Use correct classifier path based on your directory
        String classifierPath = "opencv/opencv_master/data/haarcascades/haarcascade_frontalface_default.xml";
        CascadeClassifier faceDetector = new CascadeClassifier(classifierPath);

        if (faceDetector.empty()) {
            System.err.println("❌ Haarcascade file not found at: " + classifierPath);
            return;
        }

        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("❌ Camera not found.");
            return;
        }

        JFrame frame = new JFrame("Face Detection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel imageLabel = new JLabel();
        frame.getContentPane().add(imageLabel);
        frame.setVisible(true);

        Mat frameMat = new Mat();

        while (frame.isVisible()) {
            camera.read(frameMat);
            if (!frameMat.empty()) {
                MatOfRect faces = new MatOfRect();
                faceDetector.detectMultiScale(frameMat, faces);

                for (Rect rect : faces.toArray()) {
                    Imgproc.rectangle(
                            frameMat,
                            new Point(rect.x, rect.y),
                            new Point(rect.x + rect.width, rect.y + rect.height),
                            new Scalar(0, 255, 0),
                            2
                    );
                }

                ImageIcon image = new ImageIcon(matToBufferedImage(frameMat));
                imageLabel.setIcon(image);
                frame.pack();
            }
        }

        camera.release();
        frame.dispose();
    }

    public static BufferedImage matToBufferedImage(Mat matrix) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        byte[] data = new byte[matrix.rows() * matrix.cols() * (int) matrix.elemSize()];
        matrix.get(0, 0, data);
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
        image.getRaster().setDataElements(0, 0, matrix.cols(), matrix.rows(), data);
        return image;
    }
}
