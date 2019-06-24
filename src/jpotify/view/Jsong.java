package jpotify.view;

import jpotify.view.leftpanel.ArtworkPanel;

import javax.swing.*;
import java.awt.*;

public class Jsong extends JPanel {

    public Jsong(String title , ArtworkPanel artwork) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(Color.BLACK);
        artwork.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));
        this.add(artwork, BorderLayout.CENTER);
        JLabel label = new JLabel(title);
        this.add(label, BorderLayout.SOUTH);
        this.setVisible(false);
    }

}