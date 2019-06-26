package jpotify.view;

import jpotify.controller.MainController;
import jpotify.view.bottompanel.BottomPanelView;
import jpotify.view.centerpanel.CenterPanelView;
import jpotify.view.leftpanel.LeftPanelView;
import jpotify.view.rightpanel.RightPanelView;
import jpotify.view.toppanel.TopPanelView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public static final int WIDTH = 1400, HEIGHT = 750, ICON = 15;
    private MainController controller;
    private LeftPanelView leftPanelView;
    private BottomPanelView bottomPanelView;
    private CenterPanelView centerPanelView;
    private TopPanelView topPanelView;
    private RightPanelView rightPanelView;

    public MainView(MainController mainController) throws HeadlessException {
        controller = mainController;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));
        rightPanelView = new RightPanelView(mainController);
        this.add(rightPanelView, BorderLayout.EAST);
        topPanelView = new TopPanelView(mainController);
        this.add(topPanelView, BorderLayout.NORTH);
        leftPanelView = new LeftPanelView(mainController);
        this.add(leftPanelView, BorderLayout.WEST);
        bottomPanelView = new BottomPanelView(mainController);
        this.add(bottomPanelView, BorderLayout.SOUTH);
        centerPanelView = new CenterPanelView(mainController);
        this.add(centerPanelView, BorderLayout.CENTER);
        this.getLeftPanelView().getLibraryBar().setPanelChangeListener(this.getCenterPanelView());
        this.getLeftPanelView().getPlaylistBar().setPanelChangeListener(this.getCenterPanelView());
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

    public void changeArtwork(byte[] artwork) {
        leftPanelView.changeArtworkPanel(artwork);
    }
}
