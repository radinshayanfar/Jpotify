package jpotify.view.centerpanel;

import jpotify.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlbumsPanel extends JPanel {
    private MainController controller;
    private ArrayList<JAlbum> album = new ArrayList<>();

    public AlbumsPanel(MainController mainController, ArrayList<JAlbum> jAlbums) {

        controller = mainController;
        this.setSize(CenterPanelView.WIDTH, CenterPanelView.ELEMENTS);
        this.setMaximumSize(new Dimension(CenterPanelView.WIDTH, CenterPanelView.ELEMENTS));
        this.setBackground(new Color(14,14,14));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.album = jAlbums;
        for (JAlbum a: album) {
            this.add(a);
        }
        setVisible(false);
    }
}
