package jpotify.view.bottompanel;

import jpotify.view.MainView;
import jpotify.view.bottompanel.songcontrolpanel.SongControlPanelView;
import jpotify.view.bottompanel.songcontrolpanel.TopPanel;

import javax.swing.*;
import java.awt.*;

public class BottomPanelView extends JPanel {
    static final int HEIGHT = 100;
    private SongInfoLabel songInfo = new SongInfoLabel();
    private VolumeControlPanelView volumeControlPanelView = new VolumeControlPanelView();
    private SongControlPanelView songControlPanelView = new SongControlPanelView();

    public BottomPanelView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        this.setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));

//        this.setBorder(BorderFactory.createEmptyBorder( 0, 20, 0 , 0));
        songInfo.setSongInfo("Moein","Tolou","TOLOU");
        this.add(songInfo, BorderLayout.WEST);
        this.add(volumeControlPanelView, BorderLayout.EAST);
        this.add(songControlPanelView, BorderLayout.CENTER);


        this.setVisible(true);
    }

}
