import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;

public class FaceLogin {

    static {
        try {
            // DLL path using your provided folder structure and relative path
            String dllPath = System.getProperty("user.dir") + File.separator +
                    ".." + File.separator + // Ek level up jao
                    "ONSLAUGHT (REQUIRED FILES)" + File.separator +
                    "OPEN_CV" + File.separator + "OPEN CV (MASTER)" + File.separator +
                    "build" + File.separator + "java" + File.separator + "x64" + File.separator +
                    "opencv_java4110.dll";

            File dllFile = new File(dllPath);
            if (!dllFile.exists()) {
                System.err.println("❌ DLL file not found at: " + dllFile.getAbsolutePath());
                System.exit(1);
            } else {
                System.load(dllFile.getAbsolutePath());
                System.out.println("✅ Loaded OpenCV DLL from: " + dllFile.getAbsolutePath());
            }
        } catch (UnsatisfiedLinkError e) {
            System.err.println("❌ Failed to load OpenCV DLL: " + e.getMessage());
            System.exit(1);
        }
    }

    public static String loginWithFace() {
        // Haarcascade relative path as per your folder structure
        String cascadePath = System.getProperty("user.dir") + File.separator +
                ".." + File.separator +
                "ONSLAUGHT (REQUIRED FILES)" + File.separator +
                "OPEN_CV" + File.separator + "OPEN CV (MASTER)" + File.separator +
                "data" + File.separator + "haarcascades" + File.separator +
                "haarcascade_frontalface_alt.xml";

        CascadeClassifier faceDetector = new CascadeClassifier(cascadePath);
        if (faceDetector.empty()) {
            System.err.println("❌ Haarcascade XML not found at: " + cascadePath);
            return null;
        }

        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.err.println("❌ Cannot access webcam.");
            return null;
        }

        Mat frame = new Mat();
        try {
            Thread.sleep(2000); // Camera warm-up
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!camera.read(frame) || frame.empty()) {
            System.err.println("❌ Failed to capture image.");
            camera.release();
            return null;
        }
        camera.release();

        Mat gray = new Mat();
        Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

        MatOfRect faces = new MatOfRect();
        faceDetector.detectMultiScale(gray, faces);
        Rect[] faceArray = faces.toArray();

        if (faceArray.length == 0) {
            System.out.println("❌ No face detected.");
            return null;
        }

        Rect faceRect = faceArray[0];
        Mat loginFace = new Mat(gray, faceRect);
        Imgproc.resize(loginFace, loginFace, new Size(100, 100));

        // Registered faces folder relative path - adjust if needed
        File registeredFolder = new File(System.getProperty("user.dir") + File.separator + "registered_faces");
        if (!registeredFolder.exists() || !registeredFolder.isDirectory()) {
            System.err.println("❌ 'registered_faces' folder not found at: " + registeredFolder.getAbsolutePath());
            return null;
        }

        File[] userDirs = registeredFolder.listFiles(File::isDirectory);
        if (userDirs == null || userDirs.length == 0) {
            System.err.println("❌ No registered users found.");
            return null;
        }

        double threshold = 0.7;
        String matchedUser = null;

        for (File userDir : userDirs) {
            File[] faceImages = userDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
            if (faceImages == null) continue;

            for (File img : faceImages) {
                Mat registeredFace = Imgcodecs.imread(img.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
                if (registeredFace.empty()) continue;

                Mat result = new Mat();
                Imgproc.matchTemplate(loginFace, registeredFace, result, Imgproc.TM_CCOEFF_NORMED);
                double score = Core.minMaxLoc(result).maxVal;

                System.out.println("🔍 Score with " + userDir.getName() + ": " + score);
                if (score >= threshold) {
                    matchedUser = userDir.getName();
                    System.out.println("✅ Login successful! Welcome " + matchedUser);
                    return matchedUser;
                }
            }
        }

        System.out.println("❌ Face not recognized.");
        return null;
    }

    public static void main(String[] args) {
        String user = loginWithFace();
        if (user != null) {
            System.out.println("🔓 Access granted to: " + user);
        } else {
            System.out.println("🔒 Access denied.");
        }
    }
}
