
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

- `ONSLAUGHT (FACE RECOGNITION)/src/Main.java` → Main launcher  
- `FaceRegistration.java` → Handles face image capture  
- `FaceLogin.java` → Authenticates users  
- `onslaught/shooterGame/GameMain.java` → Entry point for shooter game  
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

## 📥 1. Download the Code

- Visit the GitHub Repository:  
  🔗 [ONSLAUGHT GitHub Repo](https://github.com/OG-SCARCE/ONSLAUGHT)
- Click on **Code > Download ZIP** 📦
- Save the ZIP file to your **Desktop** 🖥️

---

## 📂 2. Extract the Code

- Extract the ZIP file on the Desktop itself.
- You’ll get a folder named: ONSLAUGHT-main
- Open it — inside you’ll find another folder also named: ONSLAUGHT-main
- ✅ Rename this **inner folder** to: ONSLAUGHT

---

## 🛠️ 3. Prepare the Project

- Open the ONSLAUGHT folder
- Right-click inside and **Copy the full path** 📋

---

## 💻 4. Open IntelliJ IDEA

1. Go to **File > New > Project from Existing Sources**
2. Paste the **copied path** and open the folder.
3. Click **Next** ➡️ then again **Next**
4. When prompted with "Yes", click **Yes** ✅
5. Wait for files to load completely ⏳
6. Click **Unmark All**, then only select the **first two files** 🗂️
7. Click **Next** ➡️ → wait for **searching to complete**
8. Keep clicking **Next** ➡️
9. Choose **Reuse**, then click **Create**
10. Let the project open in a **new window**

---

## ⚙️ 5. Setup the Run Configuration

1. Go to:  
   ONSLAUGHT (FACE RECOGNITION) ➡️ src ➡️ Main.java  
   Double-click to open it.

2. Go to **Edit Configurations** 🛠️
   - Click **+ Add New Configuration** → Select **Application**
   - **Name**: ONSLAUGHT
   - ✅ Enable: **Store as project file**

3. Configure:
   - **Module**: Select JDK 23 ☕
   - **-cp**: Set to "ONSLAUGHT (FACE RECOGNITION)"
   - **Main class**: Select Main from the same folder

4. Click **Modify Options**:
   - Enable **Add VM Options** under Java section
   - Paste the following path in VM Options:

     
--module-path "../ONSLAUGHT (REQUIRED FILES)/OPEN_CV/JAVA SDK/lib" --add-modules javafx.controls,javafx.fxml


5. Set **Working Directory** to ONSLAUGHT (FACE RECOGNITION) 📁

6. Click **Apply**, then **OK**

---

## ▶️ 6. Run the Application

- Hit the **green Play button** to run the ONSLAUGHT application 🎉

---

## 😄 7. Register with Face

- Click **Register with Face** 📸
- Enter your **username** in IntelliJ Terminal
- Allow **Camera Access** 🎥
- Wait while the application captures **10 facial images**

---

## 🔐 8. Login with Face

- Click **Login with Face**
- If successfully registered, you’ll be allowed to play the game 🎮  
  ❗ *Note: If not registered, login won’t proceed.*

---

## 🛡️ 9. Admin Login Option

- For administrator access:
  - 👤 Username: admin
  - 🔑 Password: 1234
  - Click **Login**

---

## 🚀 10. Start the Game

- Click **Start Game**
- If the game window doesn’t appear instantly:
  - Click **Minimize** 🗕, then reopen from the **Taskbar** 📌

---

## 🏆 11. Enjoy!

- You’re now ready to play and enjoy **ONSLAUGHT**
- Don’t forget to check your **High Score** after each session 🥇

---

## 💡 Contribute

- Fork the repo
- Make your changes
- Submit a pull request 🚀

---

## 🌟 Developed with 💻 and ☕ by [OG-SCARCE](https://github.com/OG-SCARCE)

> “From face recognition to fast-paced fun — welcome to **ONSLAUGHT**.”
