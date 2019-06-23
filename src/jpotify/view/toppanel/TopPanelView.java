package jpotify.view.toppanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class TopPanelView extends JPanel {

    static final int HEIGHT = 40;

    public TopPanelView() {
        this.setLayout(new BorderLayout());

        this.add(new LogoBox(), BorderLayout.WEST);
        this.add(new UsernameBar(), BorderLayout.CENTER);
        setBackground(new Color(14,14, 14));
        setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));
        setVisible(true);
    }

}
