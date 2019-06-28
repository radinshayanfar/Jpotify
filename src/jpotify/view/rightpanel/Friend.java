package jpotify.view.rightpanel;

import jpotify.controller.MainController;
import jpotify.view.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Friend extends JPanel {

    private MainController controller;
    private JLabel artist = new JLabel();
    private JLabel currentSongTitle = new JLabel();
    private JLabel name = new JLabel();
    private JLabel state = new JLabel();
    private ArrayList<JLabel> labels = new ArrayList<>();
    private String host;
    private int port;

    public Friend(MainController mainController, String Name, String host, int port) {
        controller = mainController;
        this.setPreferredSize(new Dimension(FriendsBarView.WIDTH, FriendsBarView.FRIEND_HEIGHT));
        this.setMaximumSize(new Dimension(FriendsBarView.WIDTH, FriendsBarView.FRIEND_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(2,2));
        this.setBorder(BorderFactory.createMatteBorder(0, 0,1,0, new Color(157,219,185)));
        this.port = port;
        this.host = host;
        name.setText(Name);
        currentSongTitle.setText("");
        state.setText("online");

        ArrayList<JLabel> buttons = new ArrayList<>();
        buttons.add(name);
        buttons.add(state);
        buttons.add(currentSongTitle);
        for (JLabel l : buttons){
            this.add(l);
            l.setPreferredSize(new Dimension(100,40));
            l.setForeground(Color.lightGray);
            l.setBackground(Color.BLACK);
        }
        name.setBorder(BorderFactory.createEmptyBorder(0,8,0,0));
        currentSongTitle.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Music Note.png")));
            currentSongTitle.setIcon(new ImageIcon(icon.getImage().getScaledInstance(MainView.ICON, MainView.ICON, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setVisible(true);
    }

    public void setCurrentSongTitle(String nowPlaying){
        currentSongTitle.setText(nowPlaying);
        this.revalidate();
    }

    public void setState(String s){
        state.setText(s);
        this.revalidate();
    }

    public String getCurrentSongTitle() {
        return currentSongTitle.getText();
    }

    public String getName() {
        return name.getText();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
