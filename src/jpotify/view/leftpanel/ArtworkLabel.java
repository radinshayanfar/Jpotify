package jpotify.view.leftpanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ArtworkLabel extends JLabel {
    public ArtworkLabel() {
        int sideSize = LeftPanelView.ELEMENTS_HEIGHT - 30;
        this.setPreferredSize(new Dimension(sideSize, sideSize));

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/test_song_artwork.jpg")));
            this.setIcon(new ImageIcon(icon.getImage().getScaledInstance(sideSize, sideSize, Image.SCALE_AREA_AVERAGING)));
//            this.setHorizontalAlignment(SwingConstants.LEFT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
