package jpotify.view.bottompanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class BottomPanelView extends JPanel {
    static final int HEIGHT = 100;
    private SongInfoLabel songInfo = new SongInfoLabel();


    public BottomPanelView() {
        this.setLayout(new BorderLayout());

        this.setBackground(Color.cyan);

        this.setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));

//        this.setBorder(BorderFactory.createEmptyBorder( 0, 20, 0 , 0));
        songInfo.setSongInfo("Moein","Tolou","TOLOU");
        this.add(songInfo, BorderLayout.WEST);

        this.setVisible(true);
    }

}
