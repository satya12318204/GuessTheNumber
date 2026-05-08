package com.numbergame;

/**
 * Launcher class to bypass JavaFX 11+ module system checks when building a fat JAR.
 * It does not extend Application, which prevents the Java runtime from looking
 * for JavaFX modules on the module path.
 */
public class Launcher {
    public static void main(String[] args) {
        // Delegate to the main method of the actual JavaFX application
        NumberGuessingGame.main(args);
    }
}
