package jpotify.view.centerpanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlbumsPanel extends JPanel {
    private ArrayList<Jsong> albums = new ArrayList<>();

    public AlbumsPanel() {

        this.setSize(CenterPanelView.WIDTH, CenterPanelView.ELEMENTS);
        this.setMaximumSize(new Dimension(CenterPanelView.WIDTH, CenterPanelView.ELEMENTS));
        this.setBackground(new Color(14,14,14));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        refresh(new Jsong("My albums", new ImagePanel("./assets/sampleAlbum.png", ImagePanel.ICON_MODE)));
//        this.add(new Jsong("My albums", new ImagePanel("./assets/sampleAlbum.png", new Dimension(ImagePanel.JSONG, ImagePanel.JSONG))));

        add(new Jsong("My albums"," fd"," fd", "./assets/sampleAlbum.png"));
        add(new Jsong("My albums"," fd"," fd1", "./assets/sampleAlbum.png"));
        add(new Jsong("My albums"," fd21"," fd", "./assets/sampleAlbum.png"));
        add(new Jsong("My albums"," fd5"," fd", "./assets/sampleAlbum.png"));
        add(new Jsong("My albums"," fd5"," fd", "./assets/sampleAlbum.png"));
        add(new Jsong("My albums"," fd4"," fd", "./assets/sampleAlbum.png"));
        add(new Jsong("My albums"," fd6"," fd", "./assets/sampleAlbum.png"));


        setVisible(false);
    }

    public void refresh(Jsong song){
        albums.add(song);
        for (Jsong s : albums)
            this.add(s);
        revalidate();
    }
    public void refresh(Jsong[] songs){
        for (Jsong s : songs)
            refresh(s);
    }
}
