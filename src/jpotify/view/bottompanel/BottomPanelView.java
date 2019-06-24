package jpotify.view.bottompanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class BottomPanelView extends JPanel {
    static final int HEIGHT = 100;
    private SongInfoLabel songInfoLabel = new SongInfoLabel();
    private VolumeControlPanelView volumeControlPanelView = new VolumeControlPanelView();
    private ControlPanel controlPanel = new ControlPanel();

    public BottomPanelView() {
        this.setLayout(new BorderLayout());

        this.setBackground(new Color(34, 34, 34));
        this.setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));
        this.add(songInfoLabel, BorderLayout.WEST);
        this.add(volumeControlPanelView, BorderLayout.EAST);
        this.add(controlPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public SongInfoLabel getSongInfoLabel() {
        return songInfoLabel;
    }
}
