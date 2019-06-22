package jpotify.view.centerpanel;

import jpotify.view.MainView;
import jpotify.view.toppanel.TopPanelView;

import javax.swing.*;
import java.awt.*;

public class CenterPanelView extends JPanel {
    public static final int WIDTH = 700;
    private TopPanelView topPanelView = new TopPanelView();
    private JPanel center = new JPanel(); //mainFrame

    public CenterPanelView() {
        setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        //search bar and username
        this.add(topPanelView, BorderLayout.NORTH);

        //center (main part)
        center.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        center.setBackground(Color.BLACK);
        this.add(center, BorderLayout.CENTER);

        //borders
        JPanel border1 = new JPanel();
        JPanel border2 = new JPanel();
        border1.setPreferredSize(new Dimension(1, this.getHeight()));
        border1.setBackground(Color.white);
        this.add(border1, BorderLayout.WEST);
        border2.setPreferredSize(new Dimension(1, this.getHeight()));
        border2.setBackground(Color.white);
        this.add(border2, BorderLayout.EAST);

        setVisible(true);
    }
}
