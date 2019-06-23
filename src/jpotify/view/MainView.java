package jpotify.view;

import jpotify.view.bottompanel.BottomPanelView;
import jpotify.view.centerpanel.CenterPanelView;
import jpotify.view.leftpanel.LeftPanelView;
import jpotify.view.rightpanel.FriendsBarView;
import jpotify.view.toppanel.TopPanelView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public static final int WIDTH = 1200, HEIGHT = 750;
    private LeftPanelView leftPanelView =  new LeftPanelView();
    private FriendsBarView friendsBarView = new FriendsBarView();
    private BottomPanelView bottomPanelView = new BottomPanelView();
    private CenterPanelView centerPanelView = new CenterPanelView();
    private TopPanelView topPanelView = new TopPanelView();

    public MainView() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));
        this.add(topPanelView, BorderLayout.NORTH);
        this.add(leftPanelView, BorderLayout.WEST);
        this.add(friendsBarView, BorderLayout.EAST);
        this.add(bottomPanelView, BorderLayout.SOUTH);
        this.add(centerPanelView, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
