package controller;

import model.manager.DatabaseManager;

/**
 * The base class of the application. This contains the {@link #main(String[])} method.
 */
public class Application {

    /**
     * Connects to the database and starts the {@link LoginWindow}.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        if (DatabaseManager.connect()) {
            new LoginWindow();
        }
    }
}
