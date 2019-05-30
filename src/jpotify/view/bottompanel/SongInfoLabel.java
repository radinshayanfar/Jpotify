package jpotify.view.bottompanel;

import jpotify.view.leftpanel.LeftPanelView;

import javax.swing.*;
import java.awt.*;

public class SongInfoLabel extends JLabel {

    public SongInfoLabel() {
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, BottomPanelView.HEIGHT));
        this.setSongInfo("Moein", "Tolou", "TOLOU");

    }

    public void setSongInfo(String title, String artist, String album) {
        this.setText("<html>" + title + "<br/>" + artist + "<br/>" + album + "</html>");
    }
}
