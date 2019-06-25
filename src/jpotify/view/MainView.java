package jpotify.view;

import jpotify.view.bottompanel.BottomPanelView;
import jpotify.view.centerpanel.CenterPanelView;
import jpotify.view.leftpanel.LeftPanelView;
import jpotify.view.rightpanel.RightPanelView;
import jpotify.view.toppanel.TopPanelView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public static final int WIDTH = 1200, HEIGHT = 750, ICON = 15;

    private LeftPanelView leftPanelView =  new LeftPanelView();
    private BottomPanelView bottomPanelView = new BottomPanelView();
    private CenterPanelView centerPanelView = new CenterPanelView();
    private TopPanelView topPanelView = new TopPanelView();
    private RightPanelView rightPanelView = new RightPanelView();

    public MainView() throws HeadlessException { ;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));
        this.add(rightPanelView, BorderLayout.EAST);
        this.add(topPanelView, BorderLayout.NORTH);
        this.add(leftPanelView, BorderLayout.WEST);
        this.add(bottomPanelView, BorderLayout.SOUTH);
        this.add(centerPanelView, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public BottomPanelView getBottomPanelView() {
        return bottomPanelView;
    }

    public LeftPanelView getLeftPanelView() {
        return leftPanelView;
    }

    public RightPanelView getRightPanelView() {
        return rightPanelView;
    }

    public CenterPanelView getCenterPanelView() {
        return centerPanelView;
    }

    public TopPanelView getTopPanelView() {
        return topPanelView;
    }
}
