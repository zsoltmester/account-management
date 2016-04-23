package controller;

import model.Session;

import javax.swing.*;

abstract class Window {

    protected Session session;

    protected JFrame frame;

    protected Window(Session session) {
        this.session = session;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
