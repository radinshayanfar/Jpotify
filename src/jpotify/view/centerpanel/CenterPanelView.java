package jpotify.view.centerpanel;

import jpotify.controller.MainController;
import jpotify.view.*;
import jpotify.view.Listeners.PanelChangeListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CenterPanelView extends JPanel implements PanelChangeListener {
    private MainController controller;
    public static final int WIDTH = 800, ELEMENTS =210;
//    private TopPanelView topPanelView = new TopPanelView();
    private SongsPanel songsPanel;
    private AlbumsPanel albumsPanel;
    private PlaylistPanel playlistPanel;
    private JScrollPane scrollPane;

    public CenterPanelView(MainController mainController) {
        controller = mainController;
        setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        setLayout(new BorderLayout());
        this.setBackground(new Color(14,14, 14));

        Border border1 = BorderFactory.createMatteBorder(5,15,10,15, new Color(14,14,14));
        Border border2 = BorderFactory.createMatteBorder(1,0,0,0, Color.lightGray);
        this.setBorder(BorderFactory.createCompoundBorder(border1, border2));

        setVisible(true);

        songsPanel = new SongsPanel(controller);
        albumsPanel = new AlbumsPanel(controller);
        playlistPanel = new PlaylistPanel(controller);
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
            this.removee();
            songsPanel = new SongsPanel(controller);
            songsPanel.setVisible(true);
            scrollPane = new JScrollPane(songsPanel);
            this.add(scrollPane, BorderLayout.CENTER);
//            this.setBackground(Color.YELLOW);
            this.revalidate();
        }
        else if (panelName.equals("albums")){
            System.out.println("album");
            this.removee();
            albumsPanel = new AlbumsPanel(controller);
            albumsPanel.setVisible(true);
            scrollPane = new JScrollPane(albumsPanel);
            this.add(scrollPane, BorderLayout.CENTER);
//            this.setBackground(Color.MAGENTA);
            this.revalidate();
        }
        else{
            System.out.println("playlist");
            this.removee();
            //TODO throws playlist name at controller and takes songslist
            playlistPanel = new PlaylistPanel(controller);
            playlistPanel.setVisible(true);
//            scrollPane = new JScrollPane(playlistPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(playlistPanel, BorderLayout.CENTER);
//            this.setBackground(Color.blue);
            this.revalidate();
        }
    }

    private void removee(){
        this.invalidate();
        this.remove(albumsPanel);
        this.remove(playlistPanel);
        this.remove(songsPanel);
    }
}
