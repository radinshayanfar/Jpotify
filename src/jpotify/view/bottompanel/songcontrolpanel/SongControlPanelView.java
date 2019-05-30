package jpotify.view.bottompanel.songcontrolpanel;

import javax.swing.*;
import java.awt.*;

public class SongControlPanelView extends JPanel {

    private TopPanel topPanel = new TopPanel();
    private BottomPanel bottomPanel = new BottomPanel();

    public SongControlPanelView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.white);

        bottomPanel.setPreferredSize(new Dimension(this.getWidth(), 50));

        this.add(topPanel);
        this.add(bottomPanel);
    }
}
