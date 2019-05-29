package jpotify.view.toppanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class TopPanelView extends JPanel {
    static final int HEIGHT = 50;

    public TopPanelView() {
        this.setLayout(new BorderLayout());

        this.setBackground(Color.cyan);

        this.setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));

        this.setVisible(true);
    }

}
