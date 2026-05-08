package com.numbergame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

/**
 * NumberGuessingGame is a simple JavaFX application where the user tries to
 * guess
 * a randomly generated number between 1 and 100 within a limited number of
 * attempts.
 * 
 * This class extends `Application`, which is the entry point for JavaFX
 * applications.
 */
public class NumberGuessingGame extends Application {

    // --- Class Variables (State) ---
    // These variables hold the current state of the game.
    private int targetNumber;
    private int attemptsLeft;
    private static final int MAX_ATTEMPTS = 10;
    private static final int MAX_RANGE = 100;
    private Random random = new Random();

    // --- GUI Components ---
    // These are the visual elements the user interacts with.
    private Label instructionLabel;
    private TextField guessInput;
    private Button submitButton;
    private Label feedbackLabel;
    private Label attemptsLabel;
    private Button restartButton;

    /**
     * The main() method is the standard entry point for Java applications.
     * In a JavaFX application, it simply calls the `launch()` method provided by
     * the `Application` class, which sets up the JavaFX runtime and eventually
     * calls `start()`.
     * 
     * @param args Command line arguments passed to the program.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start() method is the main entry point for all JavaFX applications.
     * It is called after the init() method has returned, and after the system is
     * ready
     * for the application to begin running.
     * 
     * @param primaryStage The primary window (stage) created by the JavaFX
     *                     platform.
     */
    @Override
    public void start(Stage primaryStage) {
        // 1. Initialize the GUI layout
        // A VBox stacks its children vertically from top to bottom.
        VBox root = new VBox(15); // 15 is the spacing between elements
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #1e1e1e;"); // Dark background

        // 2. Create and configure GUI components
        // Title label
        Label titleLabel = new Label("Number Guessing Game");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: #ecf0f1;");

        instructionLabel = new Label("Guess a number between 1 and " + MAX_RANGE + ".");
        instructionLabel.setFont(Font.font("Arial", 14));
        instructionLabel.setStyle("-fx-text-fill: #bdc3c7;");

        guessInput = new TextField();
        guessInput.setPromptText("Enter your guess here...");
        guessInput.setMaxWidth(200);
        guessInput.setStyle(
                "-fx-control-inner-background: #2d2d2d; -fx-text-fill: white; -fx-prompt-text-fill: #7f8c8d;");

        // This is Event Handling: Pressing 'Enter' inside the text field submits the
        // guess
        guessInput.setOnAction(event -> handleGuess());

        submitButton = new Button("Submit Guess");
        submitButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");

        // This is Event Handling: When the submit button is clicked, execute
        // handleGuess()
        submitButton.setOnAction(event -> handleGuess());

        feedbackLabel = new Label("Waiting for your first guess...");
        feedbackLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        attemptsLabel = new Label();
        attemptsLabel.setFont(Font.font("Arial", 14));
        attemptsLabel.setStyle("-fx-text-fill: #bdc3c7;");

        restartButton = new Button("Play Again");
        restartButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");
        restartButton.setVisible(false); // Hidden initially

        // Event Handling: When restart is clicked, call resetGame()
        restartButton.setOnAction(event -> resetGame());

        // 3. Add components to the layout
        root.getChildren().addAll(
                titleLabel,
                instructionLabel,
                guessInput,
                submitButton,
                feedbackLabel,
                attemptsLabel,
                restartButton);

        // 4. Initialize game logic state
        resetGame();

        // 5. Setup Scene and Stage
        // A Scene is the container for all content in a scene graph.
        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("Number Guessing Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Initializes or resets the game state.
     * It generates a new random number, resets the attempts counter,
     * and clears the user interface for a new round.
     */
    private void resetGame() {
        // Generate a number between 1 (inclusive) and 100 (inclusive)
        targetNumber = random.nextInt(MAX_RANGE) + 1;
        attemptsLeft = MAX_ATTEMPTS;

        // Update GUI
        feedbackLabel.setText("Good luck!");
        feedbackLabel.setStyle("-fx-text-fill: #ecf0f1;");
        updateAttemptsLabel();

        guessInput.clear();
        guessInput.setDisable(false); // Enable input
        submitButton.setDisable(false); // Enable submit button
        restartButton.setVisible(false); // Hide restart button

        // Set focus back to the text field for immediate typing
        guessInput.requestFocus();
    }

    /**
     * Handles the logic when the user submits a guess.
     * It reads the input, validates it, compares it to the target number,
     * updates the remaining attempts, and determines if the game is over
     * (win/loss).
     */
    private void handleGuess() {
        String inputText = guessInput.getText();

        try {
            // Parse the string input into an integer.
            // This can throw a NumberFormatException if the input isn't a valid number.
            int guess = Integer.parseInt(inputText);

            // Validation: Check if the guess is within bounds
            if (guess < 1 || guess > MAX_RANGE) {
                feedbackLabel.setText("Please enter a number between 1 and " + MAX_RANGE + ".");
                feedbackLabel.setStyle("-fx-text-fill: #e74c3c;"); // Red color for error
                return; // Exit the method early, don't count as an attempt
            }

            // Deduct an attempt for a valid guess
            attemptsLeft--;
            updateAttemptsLabel();

            // Core Logic: Compare guess to target
            if (guess == targetNumber) {
                // User Guessed Correctly -> WIN
                feedbackLabel.setText("Congratulations! " + targetNumber + " is correct!");
                feedbackLabel.setStyle("-fx-text-fill: #27ae60;"); // Green color for success
                endGame();
            } else if (guess < targetNumber) {
                // Guess is too low
                feedbackLabel.setText("Too Low! Try a higher number.");
                feedbackLabel.setStyle("-fx-text-fill: #e67e22;"); // Orange color
            } else {
                // Guess is too high
                feedbackLabel.setText("Too High! Try a lower number.");
                feedbackLabel.setStyle("-fx-text-fill: #e67e22;"); // Orange color
            }

            // Check if user ran out of attempts -> LOSS
            if (attemptsLeft == 0 && guess != targetNumber) {
                feedbackLabel.setText("Game Over! The number was " + targetNumber + ".");
                feedbackLabel.setStyle("-fx-text-fill: #c0392b;"); // Dark red for game over
                endGame();
            }

        } catch (NumberFormatException e) {
            // Catch block executes if Integer.parseInt() fails (e.g. input was "abc")
            feedbackLabel.setText("Invalid input! Please enter a whole number.");
            feedbackLabel.setStyle("-fx-text-fill: #e74c3c;");
        }

        // Clear the input field for the next guess
        guessInput.clear();
        guessInput.requestFocus();
    }

    /**
     * Helper function to centralize updating the attempts label text.
     */
    private void updateAttemptsLabel() {
        attemptsLabel.setText("Attempts remaining: " + attemptsLeft);
    }

    /**
     * Handles the end of the game state by disabling inputs
     * and showing the "Play Again" button.
     */
    private void endGame() {
        guessInput.setDisable(true);
        submitButton.setDisable(true);
        restartButton.setVisible(true);
    }
}
