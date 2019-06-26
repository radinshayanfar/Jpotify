package jpotify.view.centerpanel;

import jpotify.controller.MainController;

import javax.swing.*;
import java.awt.*;

public class PlaylistPanel extends JPanel {
    private MainController controller;
    private SongsPanel songsPanel;

    public PlaylistPanel(MainController mainController) {
        controller = mainController;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(CenterPanelView.ELEMENTS, CenterPanelView.ELEMENTS));
        this.setBackground(Color.cyan);
        this.setVisible(false);
//        songsPanel = new SongsPanel(controller);
    }
}
