package jpotify.view.rightpanel;

import jpotify.controller.MainController;
import jpotify.view.MainView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class RightPanelView extends JPanel {
    private MainController controller;
    private FriendsBarView friendsBarView;
    static final int WIDTH = 200;
    private JScrollPane scrollPane;

    public RightPanelView(MainController mainController) {
        controller = mainController;

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

        friendsBarView = new FriendsBarView(controller);
        scrollPane = new JScrollPane(friendsBarView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBorder(null);
//        scrollPane.setPreferredSize(new Di);

        ArrayList<Friend> friends = new ArrayList<>();
        Friend f1 = new Friend(controller,"maryam","offline", "jh");
        Friend f2 = new Friend(controller,"Mohammaf", "online", "jjlk");
        Friend f3 = new Friend(controller,"f","nazi", "online");
        friends.add(f1);
        friends.add(f2);
        friends.add(f3);
        friendsBarView.setFriends(friends);

        setVisible(true);
    }
}
