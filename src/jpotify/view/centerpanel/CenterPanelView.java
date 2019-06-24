package jpotify.view.centerpanel;

import jpotify.view.AlbumsPanel;
import jpotify.view.MainView;
import jpotify.view.SongsPanel;
import jpotify.view.toppanel.TopPanelView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CenterPanelView extends JPanel {
    public static final int WIDTH = 700;
    private TopPanelView topPanelView = new TopPanelView();
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

        this.add(songsPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setSongsPanel(SongsPanel s){
        songsPanel = s;
    }
    public void setAlbumsPanel(AlbumsPanel a){
        albumsPanel = a;
    }
}
