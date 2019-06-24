package jpotify.view.bottompanel;


import jpotify.view.centerpanel.CenterPanelView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ControlPanel extends JPanel {
    private static final int ICON = 40;
    private JButton play = new JButton();
    private JButton next = new JButton();
    private JButton previous = new JButton();
    private JButton like = new JButton();
    private JButton shuffle = new JButton();
    private JSlider controlBar = new JSlider(JSlider.HORIZONTAL, 100, 0);
    private ButtonPanel btnPanel = new ButtonPanel();

    public ControlPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(34,34,34));
        this.setPreferredSize(new Dimension(CenterPanelView.WIDTH, BottomPanelView.HEIGHT));
        this.add(controlBar, BorderLayout.SOUTH);
        this.add(btnPanel, BorderLayout.NORTH);
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(34,34,34));
        controlBar.setBackground(new Color(34,34,34));
        controlBar.setPreferredSize(new Dimension(CenterPanelView.WIDTH - 100 , 20));
        this.setVisible(true);
    }

    private class ButtonPanel extends JPanel{

        public ButtonPanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(new Color(34,34,34));
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/play.png")));
                play.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15 , ICON + 15, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/shuffle.png")));
                shuffle.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON , ICON, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/like.png")));
                like.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/forwards.png")));
                next.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/backward.png")));
                previous.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<JButton> buttons = new ArrayList();
            buttons.add(shuffle);
            buttons.add(previous);
            buttons.add(play);
            buttons.add(next);
            buttons.add(like);

            for (JButton btn : buttons){
                btn.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                btn.setBackground(new Color(34,34,34));
                this.add(btn);
            }
        }
    }
}
