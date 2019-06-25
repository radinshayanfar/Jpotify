package jpotify.view.rightpanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;

public class RightPanelView extends JPanel {
    private FriendsBarView friendsBarView;

    public RightPanelView() {

//        ArrayList<Friend> friends = new ArrayList<>();
//        Friend f1 = new Friend("maryam","offline");
//        Friend f2 = new Friend("Mohammaf", "online");
//        Friend f3 = new Friend("f","nazi","omid","online");
//        friends.add(f1);
//        friends.add(f2);
//        friends.add(f3);
////        friendsBarView = new FriendsBarView(friends);
//

        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);

//        Border outerB = BorderFactory.createMatteBorder(15,15,5,15, Color.black);
//        Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);
//        Border inerB = BorderFactory.createMatteBorder(0,0,5,0, Color.black);
//        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);
//        //Friends Activity
//        JLabel label = new JLabel("Friends Activity  ");
//        label.setFont(new Font("Arial", Font.PLAIN, 20));
//        label.setForeground(Color.white);
//        label.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
//        this.add(label);

        setVisible(true);
    }
}
