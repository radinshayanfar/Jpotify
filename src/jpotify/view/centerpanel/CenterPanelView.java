package jpotify.view.centerpanel;

import jpotify.view.AlbumsPanel;
import jpotify.view.Listeners.PanelChangeListener;
import jpotify.view.MainView;
import jpotify.view.SongsPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CenterPanelView extends JPanel implements PanelChangeListener {
    public static final int WIDTH = 700;
//    private TopPanelView topPanelView = new TopPanelView();
    private SongsPanel songsPanel = new SongsPanel();
    private AlbumsPanel albumsPanel = new AlbumsPanel();

    public CenterPanelView() {
        setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        setLayout(new BorderLayout());
        this.setBackground(new Color(14,14, 14));

        //nice border
        Border border1 = BorderFactory.createMatteBorder(5,15,10,15, new Color(14,14,14));
        Border border2 = BorderFactory.createMatteBorder(1,0,0,0, Color.lightGray);
        this.setBorder(BorderFactory.createCompoundBorder(border1, border2));
        setVisible(true);

    }

    public void setSongsPanel(SongsPanel s){
        songsPanel = s;
    }
    public void setAlbumsPanel(AlbumsPanel a){
        albumsPanel = a;
    }

    public SongsPanel getSongsPanel() {
        return songsPanel;
    }

    public AlbumsPanel getAlbumsPanel() {
        return albumsPanel;
    }

    @Override
    public void DisplayPanel(String panelName) {
        if (panelName.equals("songs")){
            this.invalidate();
            this.remove(albumsPanel);
            this.remove(songsPanel);
            songsPanel = new SongsPanel();
            this.add(songsPanel);
            this.revalidate();
        }
        else if (panelName.equals("albums")){
            this.invalidate();
            this.remove(albumsPanel);
            this.remove(songsPanel);
            albumsPanel = new AlbumsPanel();
            this.add(albumsPanel);
            this.revalidate();
        }
    }
}
