package jpotify.view.bottompanel;


import jpotify.controller.MainController;
import jpotify.model.RepeatRule;
import jpotify.view.centerpanel.CenterPanelView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


public class ControlPanel extends JPanel implements ActionListener, MouseListener {

    private MainController controller;
    private Boolean playing = false;
    private Boolean shuffled = false;
    private static final int REPEAT_ONE_SONG = 2, REPEAT = 1, NO_REPEAT = 0;
    private int repeatState = 0;
    private ActionListener a = this;
    private static final int ICON = 40;
    private JButton play = new JButton();
    private JButton next = new JButton();
    private JButton previous = new JButton();
    private JButton repeat = new JButton();
    private JButton shuffle = new JButton();
    private JPanel sliderPanel;
    private JSlider controlBar = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    private ButtonPanel btnPanel = new ButtonPanel();
    private JLabel timeSpent = new JLabel("00:00");
    private JLabel timeLeft = new JLabel("00:00");

    public ControlPanel(MainController mainController) {
        controller = mainController;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(34, 34, 34));
        this.setPreferredSize(new Dimension(CenterPanelView.WIDTH, BottomPanelView.HEIGHT));
        this.add(btnPanel, BorderLayout.NORTH);
        repeatState = 0;
        sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.setPreferredSize(new Dimension(CenterPanelView.WIDTH , 30));
        sliderPanel.setBackground(new Color(34,34,34));
        sliderPanel.add(timeSpent, BorderLayout.WEST);
        sliderPanel.add(timeLeft, BorderLayout.EAST);
        timeLeft.setForeground(Color.lightGray);
        timeSpent.setForeground(Color.lightGray);
        controlBar.setBackground(new Color(34, 34, 34));
        controlBar.setPreferredSize(sliderPanel.getPreferredSize());
        controlBar.addMouseListener(this);
        sliderPanel.add(controlBar, BorderLayout.CENTER);
        sliderPanel.setVisible(true);
        this.setVisible(true);
        this.add(sliderPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == play) {
            if (playing) {
                changePlayButton(true);
                controller.pause(true);
                return;
            } else if (controller.pause(false)) {
                changePlayButton(false);
                return;
            }
        }

        if (actionEvent.getSource() == next) {
            controller.nextPressed();
        }
        if (actionEvent.getSource() == previous) {
            controller.previousPressed();
        }
        if (actionEvent.getSource() == repeat) {
            repeatState = ++repeatState % 3;
            switch (repeatState) {
                case REPEAT:
                    try {
                        ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/1.png")));
                        repeat.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case REPEAT_ONE_SONG:
                    try {
                        ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/2.png")));
                        repeat.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case NO_REPEAT:
                    try {
                        ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/0.png")));
                        repeat.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            controller.setRepeat(repeatState == 0 ? RepeatRule.OFF : (repeatState == 1 ? RepeatRule.REPEAT : RepeatRule.REPEAT_ONE));
        }

        if (actionEvent.getSource() == shuffle) {
            if (shuffled) {
                System.out.println("turn shuffle off");
                controller.turnShuffleOff();
                shuffled = false;
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/shuffle.png")));
                    shuffle.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (controller.turnShuffleOn()) {
                System.out.println("turn shuffle on");
                shuffled = true;
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/random.png")));
                    shuffle.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void changePlayButton(boolean pause) {
        if (pause) {
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/play.png")));
                play.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15, ICON + 15, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            playing = false;
        } else {
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/pause.png")));
                play.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15, ICON + 15, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            playing = true;
        }
    }

    public JPanel getSliderPanel() {
        return sliderPanel;
    }

    public JLabel getTimeSpent() {
        return timeSpent;
    }

    public JLabel getTimeLeft() {
        return timeLeft;
    }

    public JSlider getControlBar() {
        return controlBar;
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
        if (!controller.songSliderChanged(s.getValue())) s.setValue(0);
        controller.resumePlayerForSeek();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private class ButtonPanel extends JPanel {

        public ButtonPanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(new Color(34, 34, 34));
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/play.png")));
                play.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON + 15, ICON + 15, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/shuffle.png")));
                shuffle.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ICON, ICON, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icons/0.png")));
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

            for (JButton btn : new JButton[]{shuffle, previous, play, next, repeat}) {
                btn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                btn.setBackground(new Color(34, 34, 34));
                btn.setForeground(new Color(34, 34, 34));
                btn.addActionListener(a);
                btn.setBorderPainted(false);
                this.add(btn);
            }
        }
    }

    public Boolean isPlaying() {
        return playing;
    }
}
