package jpotify.view.leftpanel;

import jpotify.view.ImagePanel;
import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class LeftPanelView extends JPanel {

    public static final int WIDTH = 200, ELEMENTS_HEIGHT = 200;

    private LibraryBar libraryBar;
    private PlaylistBar playlistBar;
    private ImagePanel artworkPanel;

    public LeftPanelView() {

//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());

        libraryBar = new LibraryBar();
        this.add(libraryBar, BorderLayout.NORTH);

        playlistBar = new PlaylistBar();
        this.add(playlistBar, BorderLayout.CENTER);
//        playlistBar.addListElements(Arrays.asList("Favourites", "Shared", "POP", "ROMANCE", "HIP-HOP"
//                , "SUMMER", "MOOD", "Ye chizi :|", "Ye chiz dige", "Zaheran khoobe!"));
//        this.add(playlistBar);

//        this.add(new JScrollPane(libraryBar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        this.add(new ImagePanel("./assets/test_song_artwork.jpg", ImagePanel.ARTWORK_MODE), BorderLayout.SOUTH);
//        this.add(new ArtworkPanel());

        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public LibraryBar getLibraryBar() {
        return libraryBar;
    }

    public PlaylistBar getPlaylistBar() {
        return playlistBar;
    }

    public ImagePanel getArtworkPanel() {
        return artworkPanel;
    }

    public void changeArtworkPanel(String address){
        this.invalidate();
        this.remove(artworkPanel);
        this.add(new ImagePanel(address, ImagePanel.ARTWORK_MODE), BorderLayout.SOUTH);
        this.revalidate();
    }
}
