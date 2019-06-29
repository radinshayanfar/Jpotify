package jpotify.view.leftpanel;

import jpotify.controller.MainController;
import jpotify.view.ButtonPanel;
import jpotify.view.ImagePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class LibraryBar extends JPanel implements MouseListener {

    private MainController controller;
    private static final int ELEMENTS_SIZE = 15;
    private Dimension dimension = new Dimension(18,18);
    private ButtonPanel addSong, songs, albums;
    private JFileChooser fileChooser = new JFileChooser();

    public LibraryBar(MainController mainController) {
        controller = mainController;

        //overall sets
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.black);
        this.setSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT + 100));
        this.setMinimumSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT + 100));

        this.setBorder(BorderFactory.createEmptyBorder(0, 15, 0,0));
        Dimension panelDimention = new Dimension(200, 40);
        addSong = new ButtonPanel("Add Song to Library", panelDimention, this, new ImagePanel("./assets/Add.png",dimension));
        this.add(addSong);
        songs = new ButtonPanel("My music", panelDimention, this, new ImagePanel("./assets/Music Note.png",dimension));
//        songs.setBorder(BorderFactory.createEmptyBorder(0, 0,0,0));
        this.add(songs);
        albums = new ButtonPanel("Albums", panelDimention, this, new ImagePanel("./assets/sampleAlbum.png", dimension));
        this.add(albums);
//        albums.setBorder(BorderFactory.createEmptyBorder(0, 0,0,0));

        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, 100));
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(addSong)) {
            System.out.println("works correctly");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Mp3 File", "mp3");
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setMultiSelectionEnabled(true);
            int f = fileChooser.showOpenDialog(null);
            if (f == JFileChooser.CANCEL_OPTION) {
            } else if (f == JFileChooser.APPROVE_OPTION) {
                File[] files = fileChooser.getSelectedFiles();
                controller.addSongToLibrary(files);
            }
            controller.changeCenterPanel(MainController.MYSONG, 0);
        }
        if (e.getSource().equals(songs)) {
            controller.changeCenterPanel(MainController.MYSONG, 0);
        }
        if (e.getSource().equals(albums)) {
            controller.changeCenterPanel(MainController.ALBUMS, 0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ButtonPanel b = (ButtonPanel) e.getSource();
        b.setBackground(new Color(14,14,14));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ButtonPanel b = (ButtonPanel) e.getSource();
        b.setBackground(Color.BLACK);
    }

}
