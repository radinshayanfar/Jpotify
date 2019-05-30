package jpotify.view.leftpanel;

import javax.swing.*;
import java.awt.*;

public class PlaylistBar extends JPanel {
    public PlaylistBar() {
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createTitledBorder("Playlists"));

        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setVisible(true);
    }
}
