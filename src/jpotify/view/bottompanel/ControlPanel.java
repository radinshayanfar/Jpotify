package jpotify.view.bottompanel;


import jpotify.view.centerpanel.CenterPanelView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ControlPanel extends JPanel implements ActionListener {

    private Boolean isPlaying = true;
    private Boolean isShuffled = false;
    private static final int REPEAT_ONE_SONG = 2, REPEAT=1, NO_REPEAT=0;
    private int repeatState;
    private ActionListener a = this;
    private static final int ICON = 40;
    private JButton play = new JButton();
    private JButton next = new JButton();
    private JButton previous = new JButton();
    private JButton repeat = new JButton();
    private JButton shuffle = new JButton();
    private JSlider controlBar = new JSlider(JSlider.HORIZONTAL, 100, 0);
    private ButtonPanel btnPanel = new ButtonPanel();

    public ControlPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(34,34,34));
        this.setPreferredSize(new Dimension(CenterPanelView.WIDTH, BottomPanelView.HEIGHT));
        this.add(controlBar, BorderLayout.SOUTH);
        this.add(btnPanel, BorderLayout.NORTH);
        repeatState = 0;
        controlBar.setBackground(new Color(34,34,34));
        controlBar.setPreferredSize(new Dimension(CenterPanelView.WIDTH - 100 , 20));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(play)){
            //TODO
            if (isPlaying){
                isPlaying = false;
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/pause.png")));
                    play.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15 , ICON + 15, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            else {
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/play.png")));
                    play.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15 , ICON + 15, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isPlaying = true;
                return;
            }
        }

        if(actionEvent.getSource().equals(next)){
            //TODO
        }
        if (actionEvent.getSource().equals(previous)){
            //TODO
        }
        if(actionEvent.getSource().equals(repeat)){
            switch (repeatState){
                case REPEAT:
                    try {
                        ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/like.png")));
                        repeat.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON  , ICON , Image.SCALE_AREA_AVERAGING)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case REPEAT_ONE_SONG:
                    try {
                        ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/play.png")));
                        repeat.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON  , ICON , Image.SCALE_AREA_AVERAGING)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case NO_REPEAT:
                    try {
                        ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/play.png")));
                        repeat.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON , ICON , Image.SCALE_AREA_AVERAGING)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    repeatState++;
                    repeatState = repeatState%3;
                    System.out.println(repeatState);
                    break;
         }
        }

        if (actionEvent.getSource().equals(shuffle)){
            if (!isShuffled){
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/like.png")));
                    shuffle.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON  , ICON , Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isShuffled = true;
                return;
            }
            else if (isShuffled){
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/shuffle.png")));
                    shuffle.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON  , ICON , Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isShuffled = false;
                return;
            }
        }
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
                repeat.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
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
            buttons.add(repeat);

            for (JButton btn : buttons){
                btn.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                btn.setBackground(new Color(34,34,34));
                btn.addActionListener(a);
                this.add(btn);
            }
            shuffle.addActionListener(a);
        }
    }
}
