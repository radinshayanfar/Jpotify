package jpotify.view.leftpanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LibraryBar extends JPanel {

    private static final int ELEMENTS_SIZE = 10;

    private JButton addSong = new JButton();
    private JButton songs = new JButton();
    private JButton albums = new JButton();
    private JFileChooser fileChooser = new JFileChooser();

    public LibraryBar() {

        //overall sets
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Add.png")));
            addSong.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addSong.setText("Add Song");
        addSong.setBackground(Color.WHITE);
        addSong.setForeground(Color.lightGray);
        addSong.setBorder(BorderFactory.createMatteBorder(5, 20, 0, 0, Color.BLACK));
        addSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                fileChooser.showOpenDialog();
            }
        });
        this.add(addSong);

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Music Note.png")));
            songs.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        songs.setText("My music");
        songs.setBackground(Color.BLACK);
        songs.setForeground(Color.lightGray);
        this.add(songs);
        songs.setBorder(BorderFactory.createMatteBorder(5, 30, 0, 0, Color.BLACK));

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Album.png")));
            albums.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        albums.setText("Albums");
        albums.setBackground(Color.BLACK);
        albums.setForeground(Color.lightGray);
        this.add(albums);
        albums.setBorder(BorderFactory.createMatteBorder(5, 30, 60, 0, Color.BLACK));

        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setVisible(true);
    }
}
