package jpotify.view.bottompanel;

import jpotify.controller.MainController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VolumeControlPanelView extends JPanel implements ActionListener, ChangeListener {
    private static final int ELEMENTS_SIZE = 20;
    static final int WIDTH = 200;

    private MainController controller;
    private JButton mute = new JButton();
    private JLabel volumeDown = new JLabel();
    private JLabel volumeUp = new JLabel();
    private JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, -20, 4, 0);

    public VolumeControlPanelView(MainController mainController) {
        controller = mainController;
        this.setBackground(new Color(34, 34, 34));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
//        Mute Button
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/mute.png")));
            mute.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mute.setPreferredSize(new Dimension(ELEMENTS_SIZE + 10, ELEMENTS_SIZE + 10));
        mute.setBackground(new Color(34, 34, 34));
        this.add(mute);
        mute.addActionListener(this);

        //        Volume Down Label
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Remove.png")));
            volumeDown.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        volumeDown.setPreferredSize(new Dimension(ELEMENTS_SIZE, ELEMENTS_SIZE + 5));
        volumeDown.setBackground(new Color(34, 34, 34));
        this.add(volumeDown);

//        Volume Slider
        volumeSlider.setPreferredSize(new Dimension(95, ELEMENTS_SIZE + 10));
        volumeSlider.setBackground(new Color(34, 34, 34));
        this.add(volumeSlider);
        volumeSlider.addChangeListener(this);

        //        Volume Up Label
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/addd.png")));
            volumeUp.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        volumeUp.setPreferredSize(new Dimension(ELEMENTS_SIZE, ELEMENTS_SIZE + 5));
        this.add(volumeUp);
        this.setBackground(new Color(34, 34, 34));
    }

    public JSlider getVolumeSlider() {
        return volumeSlider;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.muteVolume();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (((JSlider) e.getSource()).getValueIsAdjusting())
            controller.changeVolume(volumeSlider.getValue());
    }
}
