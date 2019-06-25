package jpotify.view.centerpanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlbumsPanel extends JPanel {
    private ArrayList<Jsong> albums = new ArrayList<>();

    public AlbumsPanel() {
        this.setSize(CenterPanelView.ELEMENTS, CenterPanelView.ELEMENTS);
        this.setMaximumSize(new Dimension(CenterPanelView.ELEMENTS, CenterPanelView.ELEMENTS));
        this.setBackground(new Color(14,14,14));
        this.setLayout(new FlowLayout());
//        refresh(new Jsong("My albums", new ImagePanel("./assets/sampleAlbum.png", ImagePanel.ICON_MODE)));
//        this.add(new Jsong("My albums", new ImagePanel("./assets/sampleAlbum.png", new Dimension(ImagePanel.JSONG, ImagePanel.JSONG))));
        this.add(new Jsong("My albums"," fd", "./assets/sampleAlbum.png"));
        this.add(new Jsong("My albums"," fd1", "./assets/sampleAlbum.png"));
        this.add(new Jsong("My albums"," fd21", "./assets/sampleAlbum.png"));
        this.add(new Jsong("My albums"," fd5", "./assets/sampleAlbum.png"));
        this.add(new Jsong("My albums"," fd5", "./assets/sampleAlbum.png"));
        this.add(new Jsong("My albums"," fd4", "./assets/sampleAlbum.png"));
        this.add(new Jsong("My albums"," fd6", "./assets/sampleAlbum.png"));

        setVisible(true);
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
