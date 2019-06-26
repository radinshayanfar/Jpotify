package jpotify.view.toppanel;

import jpotify.controller.MainController;
import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class TopPanelView extends JPanel {

    private MainController controller;
    static final int HEIGHT = 40;

    public TopPanelView(MainController mainController) {
        this.setLayout(new BorderLayout());
        controller = mainController;

//        this.add(new LogoBox(), BorderLayout.WEST);
//        this.add(new UsernameBar(), BorderLayout.CENTER);
        setBackground(new Color(14,14, 14));
        setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));

        JPanel topRight = new JPanel();
        topRight.setBackground(Color.black);
        topRight.setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));
//        this.add(topRight, BorderLayout.EAST);


        setVisible(true);
    }

}
