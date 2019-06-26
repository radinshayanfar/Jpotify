package jpotify.view.bottompanel;

import jpotify.controller.MainController;
import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class BottomPanelView extends JPanel {
    static final int HEIGHT = 100;
    private MainController controller;
    private SongInfoLabel songInfoLabel;
    private VolumeControlPanelView volumeControlPanelView;
    private ControlPanel controlPanel;

    public BottomPanelView(MainController mainController) {
        controller = mainController;
        this.setLayout(new BorderLayout());

        this.setBackground(new Color(34, 34, 34));
        this.setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));
        songInfoLabel = new SongInfoLabel(controller);
        this.add(songInfoLabel, BorderLayout.WEST);
        volumeControlPanelView = new VolumeControlPanelView(controller);
        this.add(volumeControlPanelView, BorderLayout.EAST);
        controlPanel = new ControlPanel(controller);
        this.add(controlPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public SongInfoLabel getSongInfoLabel() {
        return songInfoLabel;
    }
}
