package jpotify.view.toppanel;

import jpotify.controller.MainController;

import javax.swing.*;
import java.awt.*;

public class UsernameBar extends JPanel {
    private MainController controller;

    public UsernameBar(MainController mainController) {
        controller = mainController;
        setPreferredSize(new Dimension(500,40));
        setBackground(new Color(14,14,14));
        this.setVisible(true);
    }
}
