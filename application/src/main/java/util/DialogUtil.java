package util;

import resource.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * Utility functions for dialogs.
 */
public class DialogUtil {

    /**
     * Shows an error dialog.
     *
     * @param container The container of the dialog.
     */
    public static void showErrorDialog(Component container) {
        JOptionPane.showMessageDialog(container, Strings.ERROR_DIALOG_MESSAGE,
                Strings.ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a confirm dialog and returns the user answer.
     *
     * @param container The container of the dialog.
     * @return The user decision.
     */
    public static boolean showConfirmDialog(Component container) {
        int result = JOptionPane.showConfirmDialog(container, Strings.CONFIRM_DIALOG_MESSAGE,
                Strings.CONFIRM_DIALOG_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return !(result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION);
    }
}
