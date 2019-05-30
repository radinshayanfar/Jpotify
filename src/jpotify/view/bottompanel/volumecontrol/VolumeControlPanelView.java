package jpotify.view.bottompanel.volumecontrol;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VolumeControlPanelView extends JPanel {
    private static final int ELEMENTS_SIZE = 20;
    static final int WIDTH = 200;

    private JButton mute = new JButton();
    private JLabel volumeDown = new JLabel();
    private JLabel volumeUp= new JLabel();
    private JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 100, 0);

    public VolumeControlPanelView() {

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
//        Mute Button
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Volume Controls/Mute.png")));
            mute.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mute.setPreferredSize(new Dimension(ELEMENTS_SIZE + 10, ELEMENTS_SIZE + 10));
        mute.setBackground(Color.white);
        this.add(mute);

        //        Volume Down Label
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Volume Controls/VolumeDown.png")));
            volumeDown.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        volumeDown.setPreferredSize(new Dimension(ELEMENTS_SIZE + 5, ELEMENTS_SIZE + 10));
        this.add(volumeDown);

//        Volume Slider
        volumeSlider.setPreferredSize(new Dimension(95, ELEMENTS_SIZE + 10));
        volumeSlider.setBackground(Color.white);
        this.add(volumeSlider);

        //        Volume Up Label
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Volume Controls/VolumeUp.png")));
            volumeUp.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        volumeUp.setPreferredSize(new Dimension(ELEMENTS_SIZE + 5, ELEMENTS_SIZE + 10));
        this.add(volumeUp);

        this.setBackground(Color.white);

    }
}
