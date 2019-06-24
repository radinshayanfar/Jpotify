package jpotify.view;

import jpotify.view.centerpanel.CenterPanelView;
import jpotify.view.leftpanel.ArtworkPanel;

import javax.swing.*;
import java.awt.*;

public class AlbumsPanel extends JPanel {
    public AlbumsPanel() {
        this.setSize(CenterPanelView.WIDTH, CenterPanelView.HEIGHT);
        this.setBackground(new Color(14,14,14));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

//        for (Jsong song : songs){
//            this.add(song);
//            song.setVisible(true);
//        }

        ArtworkPanel artworkPanel = new ArtworkPanel();
        Jsong song = new Jsong("test", artworkPanel);
        song.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red));
        song.setVisible(true);
        this.add(song);

        setVisible(true);
    }
}
