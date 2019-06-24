package jpotify.view.rightpanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Friend extends JPanel {

    private JLabel artist = new JLabel();
    private JLabel title = new JLabel();
    private JLabel name = new JLabel();
    private JLabel state = new JLabel();
    private ArrayList<JLabel> labels = new ArrayList<>();

    public Friend(String Name, String Artist , String Title , String State) {
        labels.add(name);
        labels.add(title);
        labels.add(artist);
        labels.add(state);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(FriendsBarView.WIDTH - 20, FriendsBarView.FRIEND_HEIGHT));
        this.setBorder(BorderFactory.createMatteBorder(0,0, 1,0, Color.cyan));

        for (JLabel l : labels){
            this.add(l);
            this.setBackground(Color.BLACK);
            this.setForeground(Color.lightGray);
        }
        name.setFont(new Font("Arial", Font.BOLD, 12));
        state.setBorder(BorderFactory.createEmptyBorder(0,100, 0,0));
    }

    public Friend(String name, String state){
        this(name, "","", state);
    }
}
