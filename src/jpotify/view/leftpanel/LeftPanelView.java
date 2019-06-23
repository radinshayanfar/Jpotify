package jpotify.view.leftpanel;

import jpotify.view.MainView;
import jpotify.view.MyBorder;
import jpotify.view.toppanel.JpotifyTitleBox;

import javax.swing.*;
import java.awt.*;

public class LeftPanelView extends JPanel {

    public static final int WIDTH = 200, ELEMENTS_HEIGHT = 200;

//    private JPanel libraryBar = new LibraryBar();
//    private JPanel playlistBar = new PlaylistBar(Arrays.asList("Favourites", "Shared", "POP", "ROMANCE", "HIP-HOP"
//            , "SUMMER", "MOOD", "Ye chizi :|", "Ye chiz dige", "Zaheran khoobe!"));
//    private ArtworkPanel artwork = new ArtworkPanel();

    private MyBorder border1, border2, border3;
    private LibraryBar libraryBar;
    private PlaylistBar playlistBar;
    private ArtworkPanel artworkPanel;
    private JpotifyTitleBox titleBox;

    public LeftPanelView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //title Box
        titleBox = new JpotifyTitleBox();
        this.add(titleBox);


//        this.add(new JScrollPane(libraryBar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
//        this.add(libraryBar);
//        this.add(playlistBar);
//        this.add(artwork);

        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        this.setBackground(Color.black);
        this.setVisible(true);

    }
}
