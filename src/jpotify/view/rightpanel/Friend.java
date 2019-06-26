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
    private JLabel title = new JLabel();
    private JLabel name = new JLabel();
    private JLabel state = new JLabel();
    private ArrayList<JLabel> labels = new ArrayList<>();

    public Friend(MainController mainController, String Name, String State, String Song) {
        controller = mainController;
        this.setPreferredSize(new Dimension(FriendsBarView.WIDTH, FriendsBarView.FRIEND_HEIGHT));
        this.setMaximumSize(new Dimension(FriendsBarView.WIDTH, FriendsBarView.FRIEND_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(2,2));
        this.setBorder(BorderFactory.createMatteBorder(0, 0,1,0, new Color(157,219,185)));

        name.setText(Name);
        title.setText(Song);
        state.setText(State);

        ArrayList<JLabel> buttons = new ArrayList<>();
        buttons.add(name);
        buttons.add(state);
        buttons.add(title);
        for (JLabel l : buttons){
            this.add(l);
            l.setPreferredSize(new Dimension(100,40));
            l.setForeground(Color.lightGray);
            l.setBackground(Color.BLACK);
        }
        name.setBorder(BorderFactory.createEmptyBorder(0,8,0,0));
        title.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Music Note.png")));
            title.setIcon(new ImageIcon(icon.getImage().getScaledInstance(MainView.ICON, MainView.ICON, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setVisible(true);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getName() {
        return name.getText();
    }
}
