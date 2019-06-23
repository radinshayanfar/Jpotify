package jpotify.view.bottompanel;

import jpotify.view.MainView;
import jpotify.view.MyBorder;

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
        MyBorder border = new MyBorder(this.getWidth(), MyBorder.HORIZONTAL_BORDER , Color.WHITE);
        this.add(border, BorderLayout.NORTH);

        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));
//        this.add(songInfo, BorderLayout.WEST);
//        this.add(volumeControlPanelView, BorderLayout.EAST);
//        this.add(songControlPanelView, BorderLayout.CENTER);


        this.setVisible(true);
    }

}
