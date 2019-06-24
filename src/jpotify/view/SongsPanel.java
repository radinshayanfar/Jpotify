package jpotify.view;

import jpotify.view.centerpanel.CenterPanelView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SongsPanel extends JPanel {

    private ArrayList<Jsong> songs = new ArrayList<>();

    public SongsPanel() {
        this.setSize(CenterPanelView.WIDTH, CenterPanelView.HEIGHT);
        this.setBackground(new Color(14,14,14));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        refresh(new Jsong("My songs", new ImagePanel("./assets/sample.png", ImagePanel.ICON_MODE)));
        setVisible(true);
    }

    public void refresh(Jsong song){
        songs.add(song);
        for (Jsong s : songs)
            this.add(s);
        revalidate();
    }
    public void refresh(Jsong[] songs){
        for (Jsong s : songs)
            refresh(s);
    }
}
