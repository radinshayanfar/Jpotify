package jpotify.view.toppanel;

import jpotify.controller.MainController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LogoBox extends JPanel {
    private MainController controller;

    public LogoBox(MainController mainController) {
        controller = mainController;
        this.setPreferredSize(new Dimension(200, 40));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.black);

        Border outerB = BorderFactory.createMatteBorder(0, 10, 5, 0, Color.black);
        Border whiteLineB = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0, 0, 5, 0, Color.black);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);

        //home
        JLabel home = new JLabel("Home                    ");
        home.setFont(new Font("Arial", Font.PLAIN, 20));
        home.setForeground(Color.white);
        home.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
        this.add(home);
    }
}
