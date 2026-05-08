# Number Guessing Game

A simple GUI-based Number Guessing Game built with Core Java and JavaFX.

## Project Overview

In this game, players try to guess a randomly generated number between 1 and 100. They have a maximum of 10 attempts to find the correct number. After each guess, the game provides feedback ("Too High!" or "Too Low!") to help the player narrow down their choices.

## Technologies Used

*   **Core Java**: The primary programming language used for the game logic.
*   **JavaFX**: The framework used to create the Graphical User Interface (GUI).
*   **Maven**: The build automation and dependency management tool used to manage the JavaFX libraries.
*   **`java.util.Random`**: The standard Java class used to generate the random target number.

## Code Explanation for Interviews

The entire application logic and GUI setup is contained within `src/main/java/com/numbergame/NumberGuessingGame.java`. Here is a breakdown of the key methods and concepts used:

1.  **`main(String[] args)`**: 
    *   **Purpose**: The standard entry point for any Java program.
    *   **Usage**: It calls `launch(args)`, a static method inherited from `javafx.application.Application`, which initializes the JavaFX runtime and then calls the `start()` method.
2.  **`start(Stage primaryStage)`**: 
    *   **Purpose**: The main entry point specifically for the JavaFX application lifecycle.
    *   **Usage**: This method sets up the entire GUI. It creates the layout container (`VBox`), initializes UI controls (`Label`, `TextField`, `Button`), and adds them to the layout. It also configures the `Scene` and displays it on the `Stage` (the window). Event Handlers for the buttons are attached here using lambda expressions (e.g., `submitButton.setOnAction(event -> handleGuess());`).
3.  **`resetGame()`**: 
    *   **Purpose**: To initialize or reset the game state.
    *   **Usage**: It uses `random.nextInt(100) + 1` to generate a new target number. It resets `attemptsLeft` to 10, clears the input field, re-enables buttons, and updates the feedback labels to start a fresh round.
4.  **`handleGuess()`**: 
    *   **Purpose**: The core logic executed every time the user submits a guess.
    *   **Usage**: 
        *   **Input Parsing**: It reads text from the `TextField` and attempts to parse it into an integer using `Integer.parseInt()`. It uses a `try-catch` block to handle `NumberFormatException` in case the user enters non-numeric text.
        *   **Validation**: It checks if the parsed number is between 1 and 100.
        *   **Logic**: It compares the guess to `targetNumber` and updates the `feedbackLabel` with "Too High", "Too Low", or "Congratulations". It also decrements `attemptsLeft`.
        *   **Game Over Condition**: If `attemptsLeft` reaches 0, it ends the game and reveals the target number.
5.  **`updateAttemptsLabel()`**: 
    *   **Purpose**: A helper method to keep the GUI in sync with the underlying state.
    *   **Usage**: Updates the text of `attemptsLabel` to reflect the current value of `attemptsLeft`.
6.  **`endGame()`**: 
    *   **Purpose**: To handle the UI state when a game concludes (win or loss).
    *   **Usage**: Disables the input text field and submit button so no more guesses can be made, and makes the "Play Again" button visible.

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
