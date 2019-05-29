package jpotify.view;

import jpotify.view.bottompanel.BottomPanelView;
import jpotify.view.leftpanel.LeftPanelView;
import jpotify.view.toppanel.TopPanelView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public static final int WIDTH = 1200, HEIGHT = 750;

    public MainView() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(new LeftPanelView(), BorderLayout.WEST);
        this.add(new BottomPanelView(), BorderLayout.SOUTH);
        this.add(new TopPanelView(), BorderLayout.NORTH);

        this.setSize(WIDTH, HEIGHT);
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));

        this.setVisible(true);

    }
}
