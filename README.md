# 🌱 S.A.G.E — Smart Alert Greenhouse Environment App

## Introduction

S.A.G.E (Smart Alert Greenhouse Environment) is an Android application I developed to explore how real-time monitoring and alert systems work in smart environments such as greenhouses, IoT systems, and server-based infrastructures.

The motivation behind this project was to move beyond static apps that simply display information, and instead build something that actively monitors conditions and notifies the user when something goes wrong.

In real-world scenarios, systems need to continuously monitor environmental conditions and alert users immediately when abnormalities occur. This project simulates that behavior using Android’s native notification system and a structured monitoring flow.

---

## Why this project exists

Many monitoring systems fail not because data isn’t available, but because users aren’t alerted at the right time.

For example:

* A greenhouse temperature may rise unexpectedly
* A sensor may stop responding
* A server connection may fail

If the user is not notified immediately, the consequences can be serious.

This app focuses on solving that core problem: timely awareness.

Instead of passively displaying information, S.A.G.E actively watches and alerts.

---

## How the system works

At its core, the application follows a simple but powerful loop:

```id="flow1"
Start App
   │
   ▼
Initialize monitoring components
   │
   ▼
Generate or receive environmental data
   │
   ▼
Evaluate system condition
   │
   ├── If normal → Continue monitoring
   │
   └── If abnormal → Trigger notification alert
                         │
                         ▼
                   Notify the user
                         │
                         ▼
                   Continue monitoring
```

This continuous monitoring ensures that problems are detected and communicated immediately.

---

## Alert decision logic

The alert system follows a clear decision process:

```id="flow2"
        Incoming data
             │
             ▼
   Is the system functioning normally?
        │              │
        │ Yes          │ No
        ▼              ▼
 Continue monitoring   Trigger alert
        │              │
        ▼              ▼
   Wait interval      Notify user
        │
        ▼
   Repeat process
```

This ensures that alerts are meaningful and not triggered unnecessarily.

---

## Key features

* Real-time monitoring simulation
* Instant alert notifications
* Continuous background checking logic
* Native Android implementation using Kotlin
* Clean and structured architecture
* Lightweight and efficient performance

---

## Technical stack

This project was built using modern Android development tools:

* **Language:** Kotlin
* **IDE:** Android Studio
* **UI:** Jetpack Compose
* **Notifications:** Android Notification Manager
* **Version Control:** Git and GitHub

These tools were chosen to follow current industry standards for Android development.

---

## Project structure overview

```id="structure"
SAGE2/
│
├── app/
│   ├── MainActivity.kt        → Entry point of the application
│   ├── DataSimulator.kt      → Simulates monitoring data
│   ├── UI components         → User interface elements
│   └── resources             → App resources
│
├── gradle/                   → Build configuration
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

This structure keeps the application modular and easy to understand.

---

## Example alert scenario

If the system detects an issue such as a connection failure, the user receives an immediate notification:

Example:

⚠️ Server Error
Could not connect to server

This allows the user to respond quickly and take corrective action.

---

## What this project demonstrates

This project reflects important concepts used in real-world applications:

* Real-time monitoring logic
* Event-based notification systems
* Android application architecture
* Structured and maintainable code design

It also mirrors how IoT and monitoring systems function at a conceptual level.

---

## Potential real-world applications

The same architecture can be extended to support:

* Smart greenhouse monitoring
* IoT device monitoring
* Server health monitoring
* Environmental monitoring systems
* Industrial alert systems

This makes the project a strong foundation for future expansion.

---

## Future improvements

Planned enhancements include:

* Integration with real IoT sensors
* MQTT-based real-time communication
* Cloud backend integration
* Background service implementation
* Data visualization dashboard

These additions would transform the simulation into a fully functional monitoring system.

---

## Running the project

To run the project locally:

Clone the repository:

```id="clonecmd"
git clone https://github.com/abrolswastika/SAGE2.git
```

Open the project in Android Studio and run it on an emulator or physical device.

---

## Author

Swastika Abrol
GitHub: https://github.com/abrolswastika

---

## Closing

Thank you for taking the time to explore this project.
Feedback and suggestions are always welcome.





