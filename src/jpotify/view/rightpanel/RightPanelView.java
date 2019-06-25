package jpotify.view.rightpanel;

import jpotify.view.MainView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class RightPanelView extends JPanel {
    private FriendsBarView friendsBarView;
    static final int WIDTH = 200;
    private JScrollPane scrollPane;

    public RightPanelView() {
//
//        ArrayList<Friend> friends = new ArrayList<>();
//        Friend f1 = new Friend("maryam","offline");
//        Friend f2 = new Friend("Mohammaf", "online");
//        Friend f3 = new Friend("f","nazi","omid","online");
//        friends.add(f1);
//        friends.add(f2);
//        friends.add(f3);
//        FriendsBarView friendsBarView = new FriendsBarView(friends);
        this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        this.setLayout(new BorderLayout());
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
        this.add(label, BorderLayout.NORTH);

        friendsBarView = new FriendsBarView();
        scrollPane = new JScrollPane(friendsBarView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER);

        ArrayList<Friend> friends = new ArrayList<>();
        Friend f1 = new Friend("maryam","offline", "jh");
        Friend f2 = new Friend("Mohammaf", "online", "jjlk");
        Friend f3 = new Friend("f","nazi", "online");
        friends.add(f1);
        friends.add(f2);
        friends.add(f3);
        friendsBarView.setFriends(friends);


        setVisible(true);
    }
}