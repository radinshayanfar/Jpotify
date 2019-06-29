package jpotify.view.toppanel;

import jpotify.controller.MainController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SearchBar extends JPanel implements ActionListener {
    private MainController controller;
    private JTextField textField = new JTextField();
    private JLabel searchLabel = new JLabel();

    public SearchBar(MainController mainController) {
        controller = mainController;
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(700, 25));
        this.setMaximumSize(new Dimension(700, 25));

        this.add(textField, BorderLayout.CENTER);
        textField.addActionListener(this);
        textField.setPreferredSize(this.getPreferredSize());

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/search.png")));
            searchLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(searchLabel, BorderLayout.WEST);
        searchLabel.setMaximumSize(new Dimension(22,22));
        searchLabel.setBackground(Color.white);
        searchLabel.setBorder(BorderFactory.createMatteBorder(0,0,0,1, Color.BLACK));

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
