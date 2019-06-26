package jpotify.view.bottompanel;


import jpotify.controller.MainController;
import jpotify.view.centerpanel.CenterPanelView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ControlPanel extends JPanel implements ActionListener, MouseListener {

    private MainController controller;
    private static Boolean isPlaying = false;
    private Boolean notShuffled = true;
    private static final int REPEAT_ONE_SONG = 2, REPEAT=1, NO_REPEAT=0;
    private int repeatState;
    private ActionListener a = this;
    private static final int ICON = 40;
    private JButton play = new JButton();
    private JButton next = new JButton();
    private JButton previous = new JButton();
    private JButton repeat = new JButton();
    private JButton shuffle = new JButton();
    private JSlider controlBar = new JSlider(JSlider.HORIZONTAL, 0,100, 0);
    private ButtonPanel btnPanel = new ButtonPanel();

    public JSlider getControlBar() {
        return controlBar;
    }

    public ControlPanel(MainController mainController) {
        controller = mainController;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(34,34,34));
        this.setPreferredSize(new Dimension(CenterPanelView.WIDTH, BottomPanelView.HEIGHT));
        this.add(btnPanel, BorderLayout.NORTH);
        repeatState = 0;
        controlBar.setBackground(new Color(34,34,34));
        controlBar.setPreferredSize(new Dimension(CenterPanelView.WIDTH - 500 , 20));
        this.setVisible(true);
        controlBar.addMouseListener(this);
        this.add(controlBar, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(play)){
            //TODO
            if (isPlaying){
                System.out.println("1");
                isPlaying = false;
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/play.png")));
                    play.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15 , ICON + 15, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller.pause(true);
                return;
            }
            else {
                System.out.println("2");
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/pause.png")));
                    play.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15 , ICON + 15, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isPlaying = true;
                controller.pause(false);
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

        if(actionEvent.getSource().equals(shuffle)){
            //TODO
            if (notShuffled){
                notShuffled = false;
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/random.png")));
                    shuffle.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15 , ICON + 15, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            else {
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/shuffle.png")));
                    shuffle.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15 , ICON + 15, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                notShuffled = true;
                return;
            }
        }
    }

    public void changeButton(int mode) {
        switch (mode){
            case MainController.PLAY_BUTTON:
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/pause.png")));
                    play.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15 , ICON + 15, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isPlaying = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        controller.pausePlayerForSeek();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JSlider s = (JSlider) e.getSource();
        controller.sliderChanged(s.getValue());
        controller.resumePlayerForSeek();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
