package jpotify.view.leftpanel;

import jpotify.controller.MainController;
import jpotify.view.ImagePanel;
import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class LeftPanelView extends JPanel {

    public static final int WIDTH = 200, ELEMENTS_HEIGHT = 200;
    private MainController controller;

    private LibraryBar libraryBar;
    private PlaylistBar playlistBar;
    private ImagePanel artworkPanel;

    public LeftPanelView(MainController mainController) {
        controller = mainController;

//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());

        libraryBar = new LibraryBar(controller);
        this.add(libraryBar, BorderLayout.NORTH);

        playlistBar = new PlaylistBar(controller);
        this.add(playlistBar, BorderLayout.CENTER);
        artworkPanel = new ImagePanel("./assets/sample.png", new Dimension(ImagePanel.ARTWORK_MODE, ImagePanel.ARTWORK_MODE));
        this.add( artworkPanel, BorderLayout.SOUTH);
//
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
        this.add(new ImagePanel(address, new Dimension(ImagePanel.ARTWORK_MODE, ImagePanel.ARTWORK_MODE)), BorderLayout.SOUTH);
//        artworkPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        this.revalidate();
    }
}
