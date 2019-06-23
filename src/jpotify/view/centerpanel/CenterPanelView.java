package jpotify.view.centerpanel;

import jpotify.view.MainView;
import jpotify.view.MyBorder;
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
        MyBorder border1 = new MyBorder(this.getHeight(), MyBorder.VERTICAL_BORDER , Color.WHITE);
        MyBorder border2 = new MyBorder(this.getHeight(), MyBorder.VERTICAL_BORDER , Color.WHITE);
        this.add(border1, BorderLayout.WEST);
        this.add(border2, BorderLayout.EAST);

        setVisible(true);
    }
}
