package jpotify.view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image img;
    private int size;
    public static final int ARTWORK_MODE = 180, ICON_MODE = 80;

    public ImagePanel(String img, int size) {
        this(new ImageIcon(img).getImage(), size);
    }

    public ImagePanel(Image img, int size) {
//        this.img = (BufferedImage) img;
        this.img = img;
        this.size = size;
        Dimension sizeD = new Dimension(size, size);
        setPreferredSize(sizeD);
        setMinimumSize(sizeD);
        setMaximumSize(sizeD);
        setSize(sizeD);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0 , size ,size , null);
    }

}
