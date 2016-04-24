package controller;

import model.Session;
import resource.Dimensions;
import resource.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The base class for the windows, which manage the main {@link JFrame} and the {@link #session}.
 */
abstract class Window implements WindowListener, Session.SessionListener {


    /**
     * The session associated with this window.
     */
    protected Session session;

    private JFrame frame;

    /**
     * The main container in the main {@link JFrame}.
     */
    protected Container container;

    /**
     * Constructs a new window based on the given params.
     *
     * @param title   The title. Note, that prefix the title.
     * @param session The session.
     */
    protected Window(String title, Session session) {
        this.session = session;
        if (session != null) {
            session.addListener(this);
        }
        frame = new JFrame(Strings.TITLE_PREFIX + title);
        container = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(Dimensions.WINDOW_MINIMUM_SIZE);
    }

    /**
     * Display the frame in the given size.
     *
     * @param width  The width of the frame.
     * @param height The height of the frame.
     */
    protected void display(int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // Nothing to do.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        session.removeListener(this);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // Nothing to do.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // Nothing to do.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // Nothing to do.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // Nothing to do.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // Nothing to do.
    }

    @Override
    public void onEnd() {
        // TODO
    }
}
