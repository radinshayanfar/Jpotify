package jpotify.view;

import jpotify.view.centerpanel.CenterPanelView;
import jpotify.view.leftpanel.ArtworkPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SongsPanel extends JPanel {

    private ArrayList<Jsong> songs = new ArrayList<>();

    public SongsPanel() {
        this.setSize(CenterPanelView.WIDTH, CenterPanelView.HEIGHT);
        this.setBackground(new Color(14,14,14));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

//        for (Jsong song : songs){
//            this.add(song);
//            song.setVisible(true);
//        }

        ArtworkPanel artworkPanel = new ArtworkPanel();
        Jsong song = new Jsong("test", artworkPanel);
        song.setVisible(true);
        this.add(song);

        setVisible(true);
    }

//    public void addSong(Jsong song){
//
//    }
}
