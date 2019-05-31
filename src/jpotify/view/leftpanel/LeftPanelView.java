package jpotify.view.leftpanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LeftPanelView extends JPanel {

    public static final int WIDTH = 200, ELEMENTS_HEIGHT = 200;

    private JPanel libraryBar = new LibraryBar();
    private JPanel playlistBar = new PlaylistBar(Arrays.asList("POP", "ROMANCE", "HIP-HOP", "SUMMER", "MOOD"));
    private JLabel artwork = new ArtworkLabel();

    public LeftPanelView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        this.add(new JScrollPane(libraryBar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        this.add(libraryBar);
        this.add(playlistBar);
        this.add(artwork);

        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));

        this.setBackground(Color.black);
        this.setVisible(true);

    }
}
