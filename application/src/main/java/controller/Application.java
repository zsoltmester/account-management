package controller;

import model.manager.DatabaseManager;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The base class of the application. This contains the {@link #main(String[])} method.
 */
public class Application {

    private static Set<Window> windows;

    /**
     * Connects to the database and starts the {@link LoginWindow}.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        windows = new CopyOnWriteArraySet<>();
        if (DatabaseManager.connect()) {
            new LoginWindow();
        }
    }

    /**
     * Adds a {@link Window} to the window cache.
     *
     * @param window The window to add.
     */
    public static void addWindow(Window window) {
        windows.add(window);
    }

    /**
     * Remove a {@link Window} from the window cache.
     *
     * @param window The window to remove.
     */
    public static void removeWindow(Window window) {
        windows.remove(window);
    }

    /**
     * Refresh the given type of windows.
     *
     * @param windowType The window type to refresh.
     */
    public static void refreshWindow(Class windowType) {
        for (Window window : windows) {
            if (window.getClass().equals(windowType)) {
                window.refresh();
            }
        }
    }
}
