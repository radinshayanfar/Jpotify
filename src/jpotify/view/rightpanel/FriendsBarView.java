package jpotify.view.rightpanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class FriendsBarView extends JPanel {
    static final int WIDTH = 200;

    public FriendsBarView() {
        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));

        this.setBackground(Color.BLACK);

        this.setVisible(true);
    }
}
