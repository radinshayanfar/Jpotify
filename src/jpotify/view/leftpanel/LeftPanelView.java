package jpotify.view.leftpanel;

import jpotify.view.MainView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LeftPanelView extends JPanel {

    public static final int WIDTH = 200, ELEMENTS_HEIGHT = 200;

//    private JPanel libraryBar = new LibraryBar();
//    private JPanel playlistBar = new PlaylistBar(Arrays.asList("Favourites", "Shared", "POP", "ROMANCE", "HIP-HOP"
//            , "SUMMER", "MOOD", "Ye chizi :|", "Ye chiz dige", "Zaheran khoobe!"));
//    private ArtworkPanel artwork = new ArtworkPanel();

    private LibraryBar libraryBar;
    private PlaylistBar playlistBar;
    private ArtworkPanel artworkPanel;

    public LeftPanelView() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Border outerB = BorderFactory.createMatteBorder(2,15,10,0, Color.black);
        Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0,0,5,0, Color.black);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);

        //home
        JLabel home = new JLabel("Home                ");
        home.setFont(new Font("Arial", Font.PLAIN, 20));
        home.setForeground(Color.white);
        home.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
        this.add(home);

        libraryBar = new LibraryBar();
        this.add(libraryBar);

        //playlists
        JLabel playlist = new JLabel("Playlists              ");
        playlist.setFont(new Font("Arial", Font.PLAIN, 20));
        playlist.setForeground(Color.white);
        playlist.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
        this.add(playlist);

//        playlistBar = new PlaylistBar();
//        playlistBar.addListElements(list);
//        this.add(playlistBar);

        artworkPanel = new ArtworkPanel();
        this.add(artworkPanel);

//        this.add(new JScrollPane(libraryBar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        this.setBackground(Color.black);
        this.setVisible(true);

    }
}
