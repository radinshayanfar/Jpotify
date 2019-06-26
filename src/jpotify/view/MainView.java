package jpotify.view;

import jpotify.controller.MainController;
import jpotify.view.bottompanel.BottomPanelView;
import jpotify.view.centerpanel.CenterPanelView;
import jpotify.view.centerpanel.JPlaylistSong;
import jpotify.view.centerpanel.PlaylistPanel;
import jpotify.view.leftpanel.LeftPanelView;
import jpotify.view.rightpanel.RightPanelView;
import jpotify.view.toppanel.TopPanelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainView extends JFrame implements WindowListener {

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
        this.addWindowListener(this);
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

        JFrame pframe = new JFrame();
        pframe.setVisible(true);
        pframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pframe.setLayout(new BorderLayout());
        pframe.setSize(WIDTH, HEIGHT);
        pframe.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));
        PlaylistPanel pdf = new PlaylistPanel(controller);
        pframe.add(pdf, BorderLayout.CENTER);
        pdf.setLayout(new BoxLayout(pdf, BoxLayout.Y_AXIS));
        pdf.setVisible(true);
        pdf.add(new JPlaylistSong(controller, 0, 1, "dsf","Sdf","dfss"));

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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        controller.saveState();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
