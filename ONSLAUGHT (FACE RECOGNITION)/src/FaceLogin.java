import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;

public class FaceLogin {

    static {
        try {
            String dllPath = System.getProperty("user.dir") + "\\opencv\\opencv_master\\build\\java\\x64\\opencv_java4110.dll";
            File dllFile = new File(dllPath);
            if (!dllFile.exists()) {
                System.err.println("❌ DLL file not found: " + dllFile.getAbsolutePath());
                System.exit(1);
            } else {
                System.out.println("✅ DLL found: " + dllFile.getAbsolutePath());
                System.load(dllFile.getAbsolutePath());
            }
        } catch (UnsatisfiedLinkError e) {
            System.err.println("❌ Failed to load DLL: " + e.getMessage());
            System.exit(1);
        }
    }

    public static String loginWithFace() {
        String cascadePath = System.getProperty("user.dir") + "\\opencv\\opencv_master\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
        CascadeClassifier faceDetector = new CascadeClassifier(cascadePath);

        if (faceDetector.empty()) {
            System.err.println("❌ Haarcascade file not found at: " + cascadePath);
            return null;
        }

        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.err.println("❌ Cannot open camera");
            return null;
        }

        Mat frame = new Mat();
        Mat gray = new Mat();

        System.out.println("📸 Capturing image for face login...");
        try {
            Thread.sleep(2000); // Camera warm-up
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!camera.read(frame) || frame.empty()) {
            System.err.println("❌ Failed to capture image from camera.");
            camera.release();
            return null;
        }
        camera.release();

        Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

        MatOfRect faces = new MatOfRect();
        faceDetector.detectMultiScale(gray, faces);

        if (faces.toArray().length == 0) {
            System.out.println("❌ No face detected. Please try again.");
            return null;
        }

        Rect faceRect = faces.toArray()[0];
        Mat loginFace = new Mat(gray, faceRect);
        Imgproc.resize(loginFace, loginFace, new Size(100, 100));

        File registeredFolder = new File(System.getProperty("user.dir") + "\\registered_faces");
        if (!registeredFolder.exists() || !registeredFolder.isDirectory()) {
            System.err.println("❌ Registered faces folder not found: " + registeredFolder.getAbsolutePath());
            return null;
        }

        File[] userDirs = registeredFolder.listFiles(File::isDirectory);
        if (userDirs == null || userDirs.length == 0) {
            System.err.println("❌ No registered users found.");
            return null;
        }

        boolean matched = false;
        double threshold = 0.7;
        String matchedUser = null;

        for (File userDir : userDirs) {
            File[] faceImages = userDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
            if (faceImages == null) continue;

            for (File faceImg : faceImages) {
                Mat registeredFace = Imgcodecs.imread(faceImg.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
                if (registeredFace.empty()) continue;

                Mat result = new Mat();
                Imgproc.matchTemplate(loginFace, registeredFace, result, Imgproc.TM_CCOEFF_NORMED);
                Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
                double score = mmr.maxVal;

                System.out.println("🔍 Matching score for " + userDir.getName() + ": " + score);

                if (score >= threshold) {
                    System.out.println("✅ Login successful! Welcome " + userDir.getName());
                    matched = true;
                    matchedUser = userDir.getName();
                    break;
                }
            }
            if (matched) break;
        }

        if (!matched) {
            System.out.println("❌ Face not recognized. Login failed.");
            return null;
        }

        return matchedUser;
    }

    public static void main(String[] args) {
        String user = loginWithFace();
        if (user != null) {
            System.out.println("Face recognized: " + user);
        } else {
            System.out.println("Face not recognized.");
        }
    }
}
