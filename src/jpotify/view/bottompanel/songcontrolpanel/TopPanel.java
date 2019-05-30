package jpotify.view.bottompanel.songcontrolpanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TopPanel extends JPanel {
    private static final int ELEMENTS_SIZE = 20;

    private JButton previousSong = new JButton();
    private JButton pauseSong = new JButton();
    private JButton nextSong = new JButton();

    public TopPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

//        Previous song button
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Music Controls/Play.png")));
            previousSong.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        previousSong.setPreferredSize(new Dimension(ELEMENTS_SIZE + 10, ELEMENTS_SIZE + 10));
        previousSong.setBackground(Color.white);
        this.add(previousSong);

        //        Play/Pause song button
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Music Controls/Play.png")));
            pauseSong.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pauseSong.setPreferredSize(new Dimension(ELEMENTS_SIZE + 10, ELEMENTS_SIZE + 10));
        pauseSong.setBackground(Color.white);
        this.add(pauseSong);

        //        Next song button
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Music Controls/Play.png")));
            nextSong.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nextSong.setPreferredSize(new Dimension(ELEMENTS_SIZE + 10, ELEMENTS_SIZE + 10));
        nextSong.setBackground(Color.white);
        this.add(nextSong);

    }
}
