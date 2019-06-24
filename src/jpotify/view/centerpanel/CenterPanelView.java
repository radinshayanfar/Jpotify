package jpotify.view.centerpanel;

import jpotify.view.*;
import jpotify.view.Listeners.PanelChangeListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CenterPanelView extends JPanel implements PanelChangeListener {
    public static final int WIDTH = 700;
//    private TopPanelView topPanelView = new TopPanelView();
    private SongsPanel songsPanel = new SongsPanel();
    private AlbumsPanel albumsPanel = new AlbumsPanel();
    private PlaylistPanel playlistPanel = new PlaylistPanel();

    public CenterPanelView() {
        setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        setLayout(new BorderLayout());
        this.setBackground(new Color(14,14, 14));

        Border border1 = BorderFactory.createMatteBorder(5,15,10,15, new Color(14,14,14));
        Border border2 = BorderFactory.createMatteBorder(1,0,0,0, Color.lightGray);
        this.setBorder(BorderFactory.createCompoundBorder(border1, border2));

        this.add(new Jsong("My albums", new ImagePanel("./assets/sampleAlbum.png", ImagePanel.ICON_MODE)));


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
            this.remove(playlistPanel);
            this.remove(songsPanel);
            songsPanel = new SongsPanel();
            this.add(songsPanel);
            this.setBackground(Color.YELLOW);
            this.revalidate();
        }
        else if (panelName.equals("albums")){
            this.invalidate();
            this.remove(albumsPanel);
            this.remove(playlistPanel);
            this.remove(songsPanel);
            albumsPanel = new AlbumsPanel();
            this.add(albumsPanel);
            this.setBackground(Color.MAGENTA);
            this.revalidate();
        }
        else{
            this.invalidate();
            this.remove(albumsPanel);
            this.remove(playlistPanel);
            this.remove(songsPanel);
            //TODO throws playlist name at controller and t
            playlistPanel = new PlaylistPanel();
            this.add(playlistPanel);
            this.setBackground(Color.blue);
            this.revalidate();
        }
    }
}
