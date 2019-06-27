package jpotify.view.bottompanel;

import jpotify.controller.MainController;
import jpotify.view.leftpanel.LeftPanelView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SongInfoLabel extends JPanel {

    private MainController controller;
    private JLabel title = new JLabel();
    private JLabel artist = new JLabel();
    private JLabel album = new JLabel();

    public SongInfoLabel(MainController mainController) {
        this(mainController,"","","");
    }

    public SongInfoLabel(MainController mainController, String title, String artist, String album){
        controller = mainController;
        Border border = BorderFactory.createEmptyBorder(5,0,5,0);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));
        this.setBackground(new Color(34,34,34));
        this.setForeground(Color.white);
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, BottomPanelView.HEIGHT));
        this.setSongInfo(title, artist, album);
        this.title.setForeground(Color.lightGray);
        this.artist.setForeground(Color.lightGray);
        this.album.setForeground(Color.lightGray);
        this.title.setBorder(border);
        this.album.setBorder(border);
        this.artist.setBorder(border);
        add(this.title);
        add(this.artist);
        add(this.album);

    }

    public void setSongInfo(String title, String artist, String album) {
        this.album.setText("Album: ".concat(album));
        this.title.setText("Title: ".concat(title));
        this.artist.setText("Artist: ".concat(artist));
        revalidate();
    }
}
