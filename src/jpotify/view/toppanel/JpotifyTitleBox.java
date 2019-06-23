package jpotify.view.toppanel;

import jpotify.view.leftpanel.LeftPanelView;

import javax.swing.*;
import java.awt.*;

public class JpotifyTitleBox extends JPanel {
    public JpotifyTitleBox() {
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH , 40));
        this.setBackground(Color.GRAY);
        this.setVisible(true);
    }
}
