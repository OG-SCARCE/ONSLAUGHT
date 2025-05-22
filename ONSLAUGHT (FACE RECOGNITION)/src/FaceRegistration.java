import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.Scanner;

public class FaceRegistration {
    static {
        try {
            // ✅ DLL path set with relative structure
            String dllRelativePath = "../../ONSLAUGHT (REQUIRED FILES)/OPEN_CV/OPEN CV (MASTER)/build/java/x64/opencv_java4110.dll";
            String dllAbsolutePath = new File(dllRelativePath).getAbsolutePath();
            System.load(dllAbsolutePath);
            System.out.println("✅ OpenCV DLL loaded from: " + dllAbsolutePath);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("❌ Failed to load OpenCV DLL: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        registerUser();
    }

    public static void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to register: ");
        String username = scanner.nextLine().trim();

        // ✅ Haarcascade path set with relative structure
        String cascadeRelativePath = "../../ONSLAUGHT (REQUIRED FILES)/OPEN_CV/OPEN CV (MASTER)/data/haarcascades/haarcascade_frontalface_alt.xml";
        String cascadeAbsolutePath = new File(cascadeRelativePath).getAbsolutePath();

        CascadeClassifier faceDetector = new CascadeClassifier(cascadeAbsolutePath);

        if (faceDetector.empty()) {
            System.out.println("❌ Error: Haar cascade file not loaded from " + cascadeAbsolutePath);
            return;
        }

        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("❌ Error: Camera not found.");
            return;
        }

        // ✅ Face images will be stored under: project_root/registered_faces/<username>
        File userFolder = new File("registered_faces" + File.separator + username);
        if (!userFolder.exists()) userFolder.mkdirs();

        Mat frame = new Mat();
        Mat gray = new Mat();

        System.out.println("📸 Capturing faces. Please look at the camera...");
        System.out.println("⏳ It will automatically quit after capturing enough images.");

        int imagesCaptured = 0;
        int maxImages = 10;

        while (imagesCaptured < maxImages) {
            if (!camera.read(frame) || frame.empty()) continue;

            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

            MatOfRect faces = new MatOfRect();
            faceDetector.detectMultiScale(gray, faces);

            Rect[] faceArray = faces.toArray();
            if (faceArray.length > 0) {
                Rect faceRect = faceArray[0];
                Mat faceROI = new Mat(gray, faceRect);

                Imgproc.resize(faceROI, faceROI, new Size(100, 100));

                String[] existingFiles = userFolder.list((dir, name) -> name.toLowerCase().endsWith(".png"));
                int nextIndex = (existingFiles == null) ? 1 : existingFiles.length + 1;

                String filepath = userFolder.getAbsolutePath() + File.separator + nextIndex + ".png";
                boolean saved = Imgcodecs.imwrite(filepath, faceROI);

                if (saved) {
                    imagesCaptured++;
                    System.out.println("✅ Saved image " + nextIndex + " for user " + username);
                }

                try {
                    Thread.sleep(500); // short delay to avoid multiple fast captures
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        camera.release();
        System.out.println("🎉 Registration completed. Total images saved: " + imagesCaptured);
    }
}
