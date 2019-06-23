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

        this.setLayout(new BorderLayout());

        Border border1 = BorderFactory.createMatteBorder(2,15,4,15, Color.BLACK);
        Border border2 = BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);

        //home
        JLabel home = new JLabel("Home");
        home.setFont(new Font("Arial", Font.PLAIN, 24));
        home.setForeground(Color.white);
        home.setBorder(BorderFactory.createCompoundBorder(border1, border2));
        this.add(home, BorderLayout.NORTH);


//        this.add(new JScrollPane(libraryBar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        this.setBackground(Color.black);
        this.setVisible(true);

    }
}
