package jpotify.view.bottompanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class BottomPanelView extends JPanel {
    static final int HEIGHT = 100;

    public BottomPanelView() {
        this.setLayout(new BorderLayout());

        this.setBackground(Color.cyan);

        this.setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));

        this.setVisible(true);
    }
}
