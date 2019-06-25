package jpotify.view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image img;
    private Dimension size;
    public static final int ARTWORK_MODE = 150, ICON_MODE = 80, JSONG = 150;

    public ImagePanel(String img, Dimension dimension) {
        this(new ImageIcon(img).getImage(), dimension);
    }

    public ImagePanel(Image img, Dimension dimension) {
//        this.img = (BufferedImage) img;
        this.img = img;
        this.size = dimension;
//        Dimension sizeD = new Dimension(size, size);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setSize(dimension);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0 , size.width ,size.height , null);
    }

}
