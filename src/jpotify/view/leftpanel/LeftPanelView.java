package jpotify.view.leftpanel;

import jpotify.view.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LeftPanelView extends JPanel {

    public static final int WIDTH = 200, ELEMENTS_HEIGHT = 200;

    private JPanel libraryBar = new LibraryBar();
    private JPanel playlistBar = new PlaylistBar();
    private JLabel artwork = new ArtworkLabel();

    public LeftPanelView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(libraryBar);
        this.add(playlistBar);
        this.add(artwork);

        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));

        this.setBackground(Color.black);
        this.setVisible(true);

    }
}
