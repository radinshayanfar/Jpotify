package jpotify.view.leftpanel;

import jpotify.controller.MainController;
import jpotify.view.ImagePanel;
import jpotify.view.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

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

        playlistBar = new PlaylistBar(controller, controller.getPlayLists());
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

    public void changeArtworkPanel(byte [] image){
        Image img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        this.invalidate();
        this.remove(artworkPanel);
        artworkPanel = new ImagePanel(img, new Dimension(ImagePanel.ARTWORK_MODE, ImagePanel.ARTWORK_MODE));
        this.add(artworkPanel, BorderLayout.SOUTH);
        this.revalidate();
    }
    public void refreshPlaylistBar(){
//        this.invalidate();
        this.remove(playlistBar);
        playlistBar = new PlaylistBar(controller, controller.getPlayLists());
        this.add(playlistBar, BorderLayout.CENTER);
        this.revalidate();
    }
}
