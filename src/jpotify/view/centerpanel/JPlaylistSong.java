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
    private JButton moveUp = new JButton();
    private JButton moveDown = new JButton();
    private final static int SONGHEIGHT = 60;
    private Dimension dimension = new Dimension(CenterPanelView.WIDTH, SONGHEIGHT);

    public JPlaylistSong(MainController mainController, int playListIndex, int index, String title, String artist, String album) {
        this.controller = mainController;
        this.playListIndex = playListIndex;

        this.index = new JLabel(Integer.toString(index +  1));
        this.title = new JLabel(title);
        this.artist = new JLabel(artist);
        this.album = new JLabel(album);

        this.setBackground(new Color(14,14,14));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
//        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);

        this.add(this.index);
        this.index.setForeground(Color.lightGray);
        this.index.setPreferredSize(new Dimension(20, 10));
        for (JLabel l: new JLabel[]{this.title,this.artist,this.album}) {
            l.setPreferredSize(new Dimension(100,SONGHEIGHT));
            l.setMaximumSize(new Dimension(100,SONGHEIGHT));
            l.setForeground(Color.lightGray);
//            l.setBorder(BorderFactory.createMatteBorder(0,1,0,0, Color.lightGray));
            this.add(l);
        }
        this.album.setPreferredSize(new Dimension(220,SONGHEIGHT));
        this.artist.setPreferredSize(new Dimension(220,SONGHEIGHT));
        mySetBorder(new Color(14,14,14));

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Remove.png")));
            delete.setIcon(new ImageIcon(icon.getImage().getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/up.png")));
            moveUp.setIcon(new ImageIcon(icon.getImage().getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/down.png")));
            moveDown.setIcon(new ImageIcon(icon.getImage().getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (JButton btn: new JButton[]{delete,moveUp,moveDown}) {
            btn.addMouseListener(this);
            btn.setMaximumSize(new Dimension(20, 20));
            btn.setBorder(null);
            btn.setBackground(new Color(14,14,14));
            this.add(btn);
        }

        this.addMouseListener(this);

        this.setVisible(true);
    }

    private void mySetBorder(Color c){
        Border outerB = BorderFactory.createMatteBorder(0,0,20,0, c);
        Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0,5,5,5, c);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);
        this.setBorder(BorderFactory.createCompoundBorder(complexB, outerB));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource().equals(this)){
             System.out.println(index.getText() + "playlist songs");
            controller.playSelectedSong(Integer.parseInt(index.getText())-1);
        }
        if (e.getSource().equals(delete))
            controller.deleteSongFromPlaylist(Integer.parseInt(index.getText())-1);
        if (e.getSource().equals(moveDown)) {
            controller.changePositionOfTheSongInPlaylist(false, Integer.parseInt(index.getText()) - 1);
        }
        if (e.getSource().equals(moveUp)) {
            controller.changePositionOfTheSongInPlaylist(true, Integer.parseInt(index.getText()) - 1);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
