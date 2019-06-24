package jpotify.view.bottompanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class BottomPanelView extends JPanel {
    static final int HEIGHT = 100;
//    private SongInfoLabel songInfo = new SongInfoLabel();
//    private VolumeControlPanelView volumeControlPanelView = new VolumeControlPanelView();
//    private SongControlPanelView songControlPanelView = new SongControlPanelView();

    public BottomPanelView() {
        this.setLayout(new BorderLayout());

        //white border on top
//        JPanel border = new JPanel();
//        border.setPreferredSize(new Dimension(this.getWidth(), 1));
//        border.setBackground(Color.white);
//        this.add(border, BorderLayout.NORTH);

        this.setBackground(new Color(34, 34, 34));
        this.setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));



//        this.add(songInfo, BorderLayout.WEST);
//        this.add(volumeControlPanelView, BorderLayout.EAST);
//        this.add(songControlPanelView, BorderLayout.CENTER);


        this.setVisible(true);
    }

}
