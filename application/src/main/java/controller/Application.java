package controller;

import model.manager.DatabaseManager;

import java.sql.SQLException;

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
        try {
            DatabaseManager.connect();
        } catch (SQLException e) {
            System.out.println("[ERROR] Unable to connect to the database, because of the following exception:");
            e.printStackTrace();
            System.exit(1);
        }
        new LoginWindow();
    }
}
