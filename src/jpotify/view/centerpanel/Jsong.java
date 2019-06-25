package jpotify.view.centerpanel;

import jpotify.view.ImagePanel;
import jpotify.view.Listeners.NewSongIsPlayed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Jsong extends JPanel implements MouseListener {

    public static final int WIDTH = 190, HEIGHT = 190,BORDER= 10;
    public static final Dimension DIMENSION = new Dimension(ImagePanel.JSONG,ImagePanel.JSONG);
    private ImagePanel artwork;
    private JPanel infoPanel = new JPanel();
    private JLabel artist = new JLabel();
    private JLabel title = new JLabel();
    private NewSongIsPlayed songIsPlayed;

    public Jsong(String ttl , String rtst, String rtwrk) {
        this(ttl, rtst, new ImageIcon(rtwrk).getImage());
    }

    public Jsong(String ttl, String rtst, Image img){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.red);
        artwork = new ImagePanel(img, DIMENSION);
        artwork.setBorder(BorderFactory.createMatteBorder(BORDER,BORDER,BORDER,BORDER,Color.white));
        this.add(artwork, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.SOUTH);
        infoPanel.setBackground(new Color(34,34,34));
        infoPanel.setLayout(new GridLayout(2,1));
        //TODO add icons and add songs
        infoPanel.add(title);
        infoPanel.add(artist);
        title.setText(ttl);
        artist.setText(rtst);
        this.addMouseListener(this);
        this.setVisible(true);
    }

    public void setSongIsPlayed(NewSongIsPlayed sg){
        songIsPlayed = sg;
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
