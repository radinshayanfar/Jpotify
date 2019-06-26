package jpotify.view.centerpanel;

import jpotify.controller.MainController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class JPlaylistSong extends JPanel implements MouseListener {

    private int playListIndex;
    private MainController controller;
    private JLabel album,artist, title, index;
    private JButton delete = new JButton();
    private final static int HEIGHT = 40;
    private Dimension dimension = new Dimension(CenterPanelView.WIDTH - 100, HEIGHT);

    public JPlaylistSong(MainController mainController, int playListIndex, int index, String title, String artist, String album) {
        this.controller = mainController;
        this.playListIndex = playListIndex;

        this.index = new JLabel(Integer.toString(index +  1));
        this.title = new JLabel(title);
        this.artist = new JLabel(artist);
        this.album = new JLabel(album);

        this.setBackground(new Color(14,14,14));
        this.setLayout(new FlowLayout());
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);

        this.add(this.index);
        this.index.setForeground(Color.lightGray);
        this.index.setPreferredSize(new Dimension(20, HEIGHT));
        for (JLabel l: new JLabel[]{this.title,this.album,this.artist}) {
            l.setPreferredSize(new Dimension(200,HEIGHT));
            l.setForeground(Color.lightGray);
            this.add(l);
        }
        mySetBorder(new Color(14,14,14));

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Remove.png")));
            delete.setIcon(new ImageIcon(icon.getImage().getScaledInstance(10, 10, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        delete.addMouseListener(this);
        delete.setMaximumSize(new Dimension(20, HEIGHT));
        delete.setBorder(null);
        delete.setBackground(new Color(14,14,14));

        this.setVisible(true);
    }

    private void mySetBorder(Color c){
        Border outerB = BorderFactory.createMatteBorder(0,0,5,0, c);
        Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0,5,5,5, c);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);
        this.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
