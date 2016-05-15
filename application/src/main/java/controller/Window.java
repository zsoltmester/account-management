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
     * @param hasMenu Indicates that the window has manu or not.
     */
    protected Window(String title, Session session, boolean hasMenu) {
        this.session = session;
        if (session != null) {
            session.revoke();
            session.addListener(this);
        }

        frame = new JFrame(Strings.TITLE_PREFIX + title);
        container = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(Dimensions.WINDOW_MINIMUM_SIZE);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(this);
        Application.addWindow(this);

        if (hasMenu) {
            initializeMenu();
        }
    }

    private void initializeMenu() {
        JMenu menu = new JMenu(Strings.MENU_GROUP_NAME);

        JMenuItem loginMenuItem = new JMenuItem(Strings.MENU_LOGIN);
        loginMenuItem.addActionListener(event -> new LoginWindow());
        menu.add(loginMenuItem);

        JMenuItem searchMenuItem = new JMenuItem(Strings.MENU_SEARCH);
        searchMenuItem.addActionListener(event -> new CustomerSearchWindow(session));
        menu.add(searchMenuItem);

        menu.addSeparator();

        JMenuItem refreshMenuItem = new JMenuItem(Strings.MENU_REFRESH);
        refreshMenuItem.addActionListener(event -> refresh());
        menu.add(refreshMenuItem);

        menu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem(Strings.MENU_EXIT);
        exitMenuItem.addActionListener(event -> close());
        menu.add(exitMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }

    /**
     * Adds the given component properly, if the current layout of the {@link #container} is {@link BoxLayout}.
     *
     * @param component The component to add.
     * @param size      The size of the component.
     */
    protected void addComponentToBoxLayout(JComponent component, Dimension size) {
        component.setMaximumSize(size);
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(component);
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

    /**
     * Repaints the window.
     */
    protected void repaint() {
        container.validate();
        container.repaint();
    }

    /**
     * Closes the window.
     */
    protected void close() {
        frame.dispose();
    }

    /**
     * Refreshes the window.
     */
    protected abstract void refresh();

    @Override
    public void windowOpened(WindowEvent e) {
        // Nothing to do.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // Nothing to do.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        if (session != null) {
            session.removeListener(this);
        }
        Application.removeWindow(this);
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
        JOptionPane.showMessageDialog(container, Strings.SESSION_END_TITLE, Strings.SESSION_END_MESSAGE,
                JOptionPane.ERROR_MESSAGE);
        close();
    }
}
