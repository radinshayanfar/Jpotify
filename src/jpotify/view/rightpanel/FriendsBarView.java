package jpotify.view.rightpanel;

import jpotify.view.MainView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class FriendsBarView extends JPanel {
    static final int WIDTH = 200, FRIEND_HEIGHT = 80;
    private ArrayList<Friend> friends = new ArrayList<>();

    public FriendsBarView(ArrayList<Friend> friends) {
        this();
        setFriends(friends);
    }

    public FriendsBarView(){
        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);

        Border outerB = BorderFactory.createMatteBorder(15,15,5,15, Color.black);
        Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0,0,5,0, Color.black);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);
        //Friends Activity
        JLabel label = new JLabel("Friends Activity  ");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.white);
        label.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
        this.add(label);
        Friend f1 = new Friend("maryam","offline");
        this.add(f1);
        this.setVisible(true);
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
        for (Friend f: friends){
            this.add(f);
        }
    }
}
