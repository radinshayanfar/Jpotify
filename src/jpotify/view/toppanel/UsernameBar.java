package jpotify.view.toppanel;

import jpotify.controller.MainController;

import javax.swing.*;
import java.awt.*;

public class UsernameBar extends JMenuBar {
    private MainController controller;

    public UsernameBar(MainController mainController) {
        controller = mainController;
        setPreferredSize(new Dimension(200,40));
        setBackground(Color.red);
        this.add(new JMenu());
        this.setVisible(true);
    }
}
