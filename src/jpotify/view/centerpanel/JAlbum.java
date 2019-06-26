package jpotify.view.centerpanel;

import jpotify.controller.MainController;
import jpotify.view.ImagePanel;
import jpotify.view.Listeners.NewSongIsPlayed;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JAlbum extends JPanel implements MouseListener {

    private MainController controller;
    public static final int WIDTH = CenterPanelView.WIDTH , HEIGHT = 190 ,BORDER= 1;
    public static final Dimension DIMENSION = new Dimension(ImagePanel.JSONG ,ImagePanel.JSONG);
    private ImagePanel artwork;
    private JPanel infoPanel = new JPanel();
//    private JLabel artist = new JLabel();
    private JLabel titles = new JLabel();
    private JLabel albumName = new JLabel();
    private NewSongIsPlayed songIsPlayed;
//    private JButton delete = new JButton();
    private int index;

    public JAlbum(MainController mainController, String name, ArrayList<JSong> songs, byte [] img, int index) throws IOException {
        controller = mainController;
        this.index = index;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH - 100,HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH - 100, HEIGHT));
        this.setBackground(new Color(14,14,14));
        Image image = ImageIO.read(new ByteArrayInputStream(img));
        artwork = new ImagePanel(image, DIMENSION);
        artwork.setBorder(BorderFactory.createMatteBorder(BORDER,BORDER,BORDER,BORDER,Color.cyan));
        this.add(artwork, BorderLayout.WEST);
        this.add(infoPanel, BorderLayout.CENTER);
//        this.add(delete, BorderLayout.EAST);
//
//        try {
//            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Remove.png")));
//            delete.setIcon(new ImageIcon(icon.getImage().getScaledInstance(10, 10, Image.SCALE_AREA_AVERAGING)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        delete.addMouseListener(this);
//        delete.setBorder(null);
//        delete.setBackground(new Color(14,14,14));

        mySetBorder(new Color(14,14,14));

        infoPanel.setBackground(new Color(14,14,14));
        infoPanel.setLayout(new GridLayout(2,1));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        //TODO add icons and add songs
        infoPanel.add(albumName);
        infoPanel.add(titles);
//        infoPanel.add(album);
        albumName.setText(name);
        albumName.setForeground(Color.lightGray);
//        artist.setText(rtst);
//        artist.setForeground(Color.lightGray);
        String srt = "";
        for (int i = 0 ; i < 3 && i != songs.size() ; i++){
            JSong song = songs.get(i);
            srt.concat(song.getTitle().getText());
        }
        titles.setText(srt);
        titles.setForeground(Color.lightGray);
        this.add(infoPanel, BorderLayout.CENTER);

        this.addMouseListener(this);

        this.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(this)){
            System.out.println("album");
//            controller.playSelectedSong(index);
//            controller.controlButtonHandler(MainController.PLAY_BUTTON);
            controller.changeCenterPanel(MainController.MYSONG);
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
        if(e.getSource().equals(delete))
            delete.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red));
        else {
            this.setBackground(Color.BLACK);
            mySetBorder(Color.black);
            delete.setBackground(Color.BLACK);
            infoPanel.setBackground(Color.BLACK);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(delete))
            delete.setBorder(null);
        else {
            this.setBackground(new Color(14, 14, 14));
            mySetBorder(new Color(14,14,14));
            delete.setBackground(new Color(14,14,14));
            infoPanel.setBackground(new Color(14,14,14));
        }
    }

    private void mySetBorder(Color c){
        Border outerB = BorderFactory.createMatteBorder(0,15,5,0, c);
        Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.cyan);
        Border inerB = BorderFactory.createMatteBorder(0,0,5,0, c);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);
        this.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
    }
}