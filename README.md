# Number Guessing Game

A simple GUI-based Number Guessing Game built with Core Java and JavaFX.

## Project Overview

In this game, players try to guess a randomly generated number between 1 and 100. They have a maximum of 10 attempts to find the correct number. After each guess, the game provides feedback ("Too High!" or "Too Low!") to help the player narrow down their choices.

## Technologies Used

*   **Core Java**: The primary programming language used for the game logic.
*   **JavaFX**: The framework used to create the Graphical User Interface (GUI).
*   **Maven**: The build automation and dependency management tool used to manage the JavaFX libraries.
*   **`java.util.Random`**: The standard Java class used to generate the random target number.


## How to Build and Run

Ensure you have Java Development Kit (JDK) 11 or higher and Maven installed on your system.

1.  Open a terminal or command prompt.
2.  Navigate to the project root directory (`d:\javaproj\NumberGuessingGame`).
3.  **Run via Maven (Development)**:
    ```bash
    mvn clean compile
    mvn javafx:run
    ```
4.  **Run as a Standalone Application**:
    To package the game into a double-clickable JAR file that contains everything it needs:
    ```bash
    mvn clean package
    ```
    Once the build succeeds, navigate to the `target` folder. You will find a file named `NumberGuessingGame-1.0-SNAPSHOT-standalone.jar`. 
    You can double-click this file from your Windows File Explorer to play the game natively, or run it via terminal:
    ```bash
    java -jar target/NumberGuessingGame-1.0-SNAPSHOT-standalone.jar
    ```
