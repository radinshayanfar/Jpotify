package jpotify.view.toppanel;

import jpotify.controller.MainController;

import javax.swing.*;
import java.awt.*;

public class LogoBox extends JPanel {
    private MainController controller;

    public LogoBox(MainController mainController) {
        controller = mainController;
        this.setPreferredSize(new Dimension(200, 40));
        setBackground(Color.black);
    }
}
