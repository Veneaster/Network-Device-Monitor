# 📡 Network Device Monitor (Java)

A small Java console project where you can manage simple network devices.  
You can add devices, remove them, check their (simulated) status and save everything into a file.  
I made this project to practice Java basics and also to have something practical for my Ausbildung applications as *Fachinformatiker Systemintegration*.

## ✨ Features
- Add devices (hostname + IP)
- Remove devices
- Show all saved devices
- Simulated "ping" status check (Online/Offline)
- Save and load devices from `devices.txt`
- Small German comments in the code (#kleineHinweise)

## 📂 Project Structure

NetworkDeviceMonitor/
├── Main.java
├── devices.txt
└── README.md

## ▶️ How to Run
Make sure you have Java installed (Java 17+ recommended).
javac Main.java
java Main

The program automatically creates or updates the `devices.txt` file in the project folder.

## 🧠 Why I built this
I wanted to create something related to networks and devices, but still simple enough to finish in one or two sessions.  
Since Systemintegration often deals with devices, IPs, configs and monitoring, I thought this would be a good small practice project.

It also helped me refresh:
- Java basics  
- working with files  
- lists and objects  
- simple console menus  

## 📌 Notes
This project is not meant to be very advanced.  
It's just a beginner-friendly tool that shows my interest in IT systems, networks and Java programming.
