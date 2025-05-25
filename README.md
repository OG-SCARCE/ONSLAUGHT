# ONSLAUGHT
A Java-based face recognition login system using JavaFX and OpenCV. Supports real-time face detection via webcam, face registration, and custom matching logic without LBPH. Modular, GUI-driven, and fully portable with relative paths for GitHub compatibility.

# GUI-Based Face Recognition Login System + 2D Shooter Game

## 📋 Project Overview

This project combines a **GUI-based Face Recognition Login System** built in Java using JavaFX and OpenCV, with a **2D Shooter Game** implemented in Java. It features face registration, real-time face login, and a fully playable top-down shooter game. The system demonstrates integration of computer vision with real-time interactive gameplay.

---

## 📌 Features

### 🔐 Face Recognition Login

* 🧠 Face detection using OpenCV
* 🔐 Face login based on image matching (not LBPHFaceRecognizer)
* 👤 Multiple image registration per user
* 🖼 Real-time camera feed for recognition
* 🎨 GUI built with JavaFX
* 💾 Structured folder system for image data
* 🔄 Recognition with multiple attempts and retry options
* 🚫 No hardcoded paths – portable and GitHub-ready
* 🗃 Optional database/file integration for user metadata

### 🎮 2D Java Shooter Game

* 🔫 Classic top-down shooter gameplay
* ⌨️ Mouse controls for movement and shooting
* 🎯 Enemy spawning and collision detection
* 🖼 Simple 2D graphics and animation

---

## 📸 Screenshots

---

## 👨‍💻 Contributors

* **[Aman Patel]((https://github.com/OG-SCARCE))** – Project Lead & Developer
* **[Smith Shukla]((https://github.com/Smith63063))** – Documentation & Review
* **[Kaustubh Kumar]((https://github.com/kaushtubhk))** – JavaFX Developer

---

## ✅ 1. 💪 Development Setup (2 Marks)

### ✅ JDK Version

* **Java SE Development Kit 23**
* Ensure JDK 23 is installed and environment variables are configured.
* [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)

### ✅ IDE Used

* **IntelliJ IDEA Community Edition 2023** (recommended)
* Alternatives: Eclipse or VS Code with JavaFX plugins

### ✅ Clone the Repository

Go to [https://github.com/OG-SCARCE/ONSLAUGHT](https://github.com/OG-SCARCE/ONSLAUGHT) and download the .zip file.

---

## ✅ 2. ✨ Project Structure (1 Mark)

* Organized into `FaceRegistration`, `FaceLogin`, `Main`, and `GameMain` classes
* Folder `registered_faces/` stores user images
* Game assets and logic stored in dedicated package
* JavaFX FXML files and CSS styles are stored separately

---

## ✅ 3. 📃 Recognition Mechanism (1 Mark)

This project uses **custom image matching logic** based on OpenCV features for face recognition instead of LBPHFaceRecognizer.

* Face data is stored in image format during registration
* Images are compared in real-time using OpenCV-based similarity matching

---

## ✅ 4. 📂 Folder Organization (1 Mark)

* **registered\_faces/**: Directory storing images per user
* **resources/**: FXML files, stylesheets, and assets
* **opencv/**: Required native libraries for OpenCV
* **game/**: Contains shooter game logic and resources

---

## ✅ 5. 🧪 Face Recognition Logic (3 Marks)

Face detection and recognition logic is implemented in modular classes:

* `FaceDetection.java`: Handles camera capture and face bounding
* `FaceRegistration.java`: Captures multiple face images per user
* `FaceLogin.java`: Performs login validation using image comparison

---

## ✅ 6. 🎯 Game Module (3 Marks)

* `GameMain.java`: Main class to run the shooter game
* Player and enemy sprites
* Bullet, collision, and scoring logic
* Game loop using standard Java AWT and Swing

---

## ✅ 7. 🎨 UI/UX Design Enhancements (4 Marks)

* Responsive design using JavaFX layouts
* Status messages, tooltips, and feedback on login success/failure
* Navigation between registration, login, and game screens
* Styled using CSS for a modern appearance

---

## ✅ 8. 🏅 Component Placement (2 Marks)

* Grid and VBox layouts for structured UI
* Dynamic feedback labels for user interaction
* Aligned buttons and image panels

---

## ✅ 9. ⌘ Responsiveness & Accessibility (2 Marks)

* Keyboard accessibility for navigation
* Error handling dialogs and alerts
* Camera feedback window resizes dynamically

---

## 🎓 Academic Credit

This README supports project grading under course criteria such as:

* JavaFX UI and layout
* OpenCV face detection and recognition
* Game development and interactivity
* Code structure and modularity
* Visual and functional completeness

> 📄 If you'd like to contribute, fork the repo and raise a pull request. Thanks for checking it out!
