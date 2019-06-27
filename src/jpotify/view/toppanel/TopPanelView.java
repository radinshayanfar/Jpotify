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
        setBackground(new Color(14,14, 14));
        setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));

        this.add(new LogoBox(controller), BorderLayout.WEST);

        JPanel searchContainer = new JPanel();
        searchContainer.setBackground(new Color(14,14,14));
        searchContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchContainer.add(new SearchBar(controller));
        this.add(searchContainer, BorderLayout.CENTER);

        JPanel topRight = new JPanel();
        topRight.setBackground(Color.black);
        topRight.setPreferredSize(new Dimension(200, HEIGHT));
        this.add(topRight, BorderLayout.EAST);

        setVisible(true);
    }

}
