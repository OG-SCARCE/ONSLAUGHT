
# 💥 ONSLAUGHT  
### A JavaFX + OpenCV Powered Face Recognition Login System with a 2D Shooter Game

---

## 📘 Overview

**ONSLAUGHT** is a modular, GUI-based Java application that blends **real-time face recognition** using OpenCV with an interactive **2D top-down shooter game**. Built entirely with **JavaFX**, it supports **face registration**, **login with image-based comparison**, and a **classic shooter gameplay experience**. Designed for portability, the system uses **relative paths**—making it fully GitHub-compatible.

---

## 🧩 Features

### 🔐 Face Recognition Login

- 🎥 Real-time face detection with OpenCV
- 🧠 Image-based login system (custom logic, no LBPH)
- 👤 Multiple image capture per user during registration
- 📸 Integrated webcam feed and bounding box
- 🎨 Clean, responsive GUI built using JavaFX
- 💾 Organized folder system (`registered_faces/`)
- 🔁 Multiple login attempts and retry mechanism
- 🗃️ Optional integration with file/database for user info
- ✅ GitHub-ready: zero hardcoded paths

### 🎮 2D Java Shooter Game

- 🔫 Classic top-down shooter mechanics
- 🖱️ Mouse-driven aiming and shooting
- 👾 Enemy spawning and bullet collisions
- 🌟 Basic animation and real-time scoring

---

## 📸 Screenshots  
*(Insert your gameplay and GUI screenshots here)*

---

## 👨‍💻 Contributors

| Name              | Role                      | GitHub                                     |
|-------------------|---------------------------|---------------------------------------------|
| Aman Patel        | Project Lead & Developer  | [OG-SCARCE](https://github.com/OG-SCARCE)   |
| Smith Shukla      | Documentation & Reviewer  | [Smith63063](https://github.com/Smith63063) |
| Kaustubh Kumar    | JavaFX Developer          | [kaushtubhk](https://github.com/kaushtubhk) |

---

## 🎯 Course Criteria Breakdown

| 📂 Section                          | ✅ Marks |
|------------------------------------|---------|
| 💪 Development Setup               | 2       |
| ✨ Project Structure               | 1       |
| 📃 Recognition Mechanism          | 1       |
| 📂 Folder Organization            | 1       |
| 🧪 Face Recognition Logic         | 3       |
| 🎮 Game Module                    | 3       |
| 🎨 UI/UX Enhancements             | 4       |
| 🏅 Component Placement            | 2       |
| ⌘ Responsiveness & Accessibility | 2       |

---

## ⚙️ Development Setup

### 📌 Prerequisites

- **Java SE Development Kit 23**  
  [Download JDK 23](https://www.oracle.com/java/technologies/javase-downloads.html)

- **IntelliJ IDEA Community Edition 2023+** (recommended)  
  Alternatives: Eclipse or VS Code with JavaFX plugins

---

## 🗂 Project Structure

- `src/Main.java` → Main launcher  
- `FaceRegistration.java` → Handles face image capture  
- `FaceLogin.java` → Authenticates users  
- `GameMain.java` → Entry point for shooter game  
- `registered_faces/` → Stores user face images  
- `resources/` → FXML, CSS, and assets  
- `opencv/` → Native OpenCV libraries  
- `game/` → Game logic and resources  

---

## 🔍 Face Recognition Logic

- **No LBPH used** – Implements custom OpenCV image comparison
- Registration captures multiple facial images (for robustness)
- Login matches real-time camera frames to registered images
- Modular and readable class design (`FaceDetection`, `FaceLogin`, etc.)

---

## 🎨 UI & Game Design

- Built with **JavaFX FXML** and **CSS**
- Screens: Login, Register, Admin Panel, Game Screen
- Game logic uses **AWT/Swing** for simplicity and responsiveness
- Styled with modern layouts: GridPane, VBox, etc.
- Includes feedback dialogs, tooltips, and responsive resizing

---

## 🔧 Installation & Running Guide

### 📥 1. Download the Code

- GitHub: [ONSLAUGHT Repository](https://github.com/OG-SCARCE/ONSLAUGHT)
- Click **Code → Download ZIP**
- Save and extract on Desktop → rename inner folder to `ONSLAUGHT`

---

### 💻 2. Open Project in IntelliJ IDEA

1. Go to `File → New → Project from Existing Sources`
2. Select the `ONSLAUGHT` folder
3. Keep clicking **Next** ➡️ → **Reuse project** when prompted
4. Let IntelliJ finish indexing and setup

---

### 🛠️ 3. Configure Run Settings

1. Open `src/Main.java`
2. Go to **Run → Edit Configurations**
3. Click `+ Add New Configuration → Application`  
   **Fill in:**
   - **Name**: `ONSLAUGHT`
   - **Module**: Select your Java 23 SDK module
   - **Main class**: `Main`
   - **Working directory**: Set to `ONSLAUGHT (FACE RECOGNITION)`
   - **VM options**:

     ```
     --module-path "../ONSLAUGHT (REQUIRED FILES)/OPEN_CV/JAVA SDK/lib" --add-modules javafx.controls,javafx.fxml
     ```

4. ✅ Apply → OK

---

### ▶️ 4. Run the Application

- Click the **green run button** (▶️) to start the app

---

## 📸 Registering a Face

1. Click **Register with Face**
2. Enter a **username** in the terminal when prompted
3. Allow camera access
4. Wait for the system to capture **10 face images**

---

## 🔐 Logging In

- Click **Login with Face**
- System compares current face with registered images
- Access granted upon a successful match

---

## 🔑 Admin Login

- **Username**: `admin`  
- **Password**: `1234`  
- Use for administrative access or testing bypass

---

## 🚀 Start the Game

- Click **Start Game**
- If it doesn’t open immediately, minimize IntelliJ and reopen from taskbar

---

## 🏆 Enjoy the Game

- Shoot enemies, dodge obstacles, and aim for a high score!
- Game records score after each session

---

## 💡 Contribute

- Fork the repo
- Make your changes
- Submit a pull request 🚀

---

## 🌟 Developed by [OG-SCARCE](https://github.com/OG-SCARCE)

> “From face recognition to fast-paced fun — welcome to **ONSLAUGHT**.”
