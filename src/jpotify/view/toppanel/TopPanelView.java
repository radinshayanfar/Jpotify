package jpotify.view.toppanel;

import jpotify.controller.MainController;
import jpotify.view.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class TopPanelView extends JPanel implements MouseListener {

    private JButton addUser;
    private MainController controller;
    static final int HEIGHT = 40;

    public TopPanelView(MainController mainController) {
        this.setLayout(new BorderLayout());
        controller = mainController;
        setBackground(new Color(14,14, 14));
        setPreferredSize(new Dimension(MainView.WIDTH, HEIGHT));

        this.add(new LogoBox(controller), BorderLayout.WEST);

        JPanel searchContainer = new JPanel();
        searchContainer.setBackground(new Color(14,14,14));
        searchContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchContainer.add(new SearchBar(controller));
        searchContainer.setBorder(BorderFactory.createEmptyBorder(4, 0,0,0));
        this.add(searchContainer, BorderLayout.CENTER);

        JPanel topRight = new JPanel();
        topRight.setBackground(Color.black);
        topRight.setPreferredSize(new Dimension(200, HEIGHT));
        this.add(topRight, BorderLayout.EAST);

        addUser =  new JButton("Connect a Friend");
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/icon.png")));
            addUser.setIcon(new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addUser.setPreferredSize(topRight.getPreferredSize());
        addUser.setBackground(Color.BLACK);
        addUser.setForeground(Color.white);
        addUser.setBorder(null);
        topRight.add(addUser);
        addUser.addMouseListener(this);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new CreateNewUserFrame(controller);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        addUser.setForeground(new Color(149,0,22));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        addUser.setForeground(Color.white);
    }
}
