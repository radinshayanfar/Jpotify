package jpotify.view.centerpanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlbumsPanel extends JPanel {
    private ArrayList<Jsong> albums = new ArrayList<>();

    public AlbumsPanel() {
        this.setSize(CenterPanelView.WIDTH, CenterPanelView.HEIGHT);
        this.setBackground(new Color(14,14,14));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
//        refresh(new Jsong("My albums", new ImagePanel("./assets/sampleAlbum.png", ImagePanel.ICON_MODE)));
//        this.add(new Jsong("My albums", new ImagePanel("./assets/sampleAlbum.png", new Dimension(ImagePanel.JSONG, ImagePanel.JSONG))));
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
