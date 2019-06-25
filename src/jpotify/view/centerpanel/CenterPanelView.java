package jpotify.view.centerpanel;

import jpotify.view.*;
import jpotify.view.Listeners.PanelChangeListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CenterPanelView extends JPanel implements PanelChangeListener {
    public static final int WIDTH = 700, ELEMENTS =600;
//    private TopPanelView topPanelView = new TopPanelView();
    private SongsPanel songsPanel = new SongsPanel();
    private AlbumsPanel albumsPanel = new AlbumsPanel();
    private PlaylistPanel playlistPanel = new PlaylistPanel();
    private JScrollPane scrollPane;

    public CenterPanelView() {
        setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        setLayout(new BorderLayout());
        this.setBackground(new Color(14,14, 14));

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
            System.out.println("song");
            this.invalidate();
            this.remove(albumsPanel);
            this.remove(playlistPanel);
            this.remove(songsPanel);
            songsPanel = new SongsPanel();
            scrollPane = new JScrollPane(songsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(scrollPane, BorderLayout.CENTER);
            this.setBackground(Color.YELLOW);
            this.revalidate();
        }
        else if (panelName.equals("albums")){
            System.out.println("album");
            this.invalidate();
            this.remove(albumsPanel);
            this.remove(playlistPanel);
            this.remove(songsPanel);
            albumsPanel = new AlbumsPanel();
            scrollPane = new JScrollPane(albumsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(scrollPane, BorderLayout.CENTER);
//            this.setBackground(Color.MAGENTA);
            this.revalidate();
        }
        else{
            System.out.println("playlist");
            this.invalidate();
            this.remove(albumsPanel);
            this.remove(playlistPanel);
            this.remove(songsPanel);
            //TODO throws playlist name at controller and takes songslist
            playlistPanel = new PlaylistPanel();
            scrollPane = new JScrollPane(playlistPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(scrollPane, BorderLayout.CENTER);
//            this.setBackground(Color.blue);
            this.revalidate();
        }
    }
}
