package jpotify.view.centerpanel;

import javax.swing.*;
import java.awt.*;

public class PlaylistPanel extends JPanel {
    private SongsPanel songsPanel = new SongsPanel();

    public PlaylistPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(CenterPanelView.WIDTH, CenterPanelView.HEIGHT));
        this.setBackground(new Color(14,14,14));
        this.setVisible(true);
    }
}
