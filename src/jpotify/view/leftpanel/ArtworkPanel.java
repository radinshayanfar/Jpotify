package jpotify.view.leftpanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArtworkPanel extends JPanel {

    private BufferedImage image;

    ArtworkPanel() {
        int sideSize = LeftPanelView.ELEMENTS_HEIGHT;
        this.setPreferredSize(new Dimension(sideSize, sideSize));
        try {
            image = toBufferedImage(ImageIO.read(new File("./assets/test_song_artwork.jpg")).getScaledInstance(sideSize, sideSize, Image.SCALE_AREA_AVERAGING));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

    public void change(byte[] newArtwork) {
        image = toBufferedImage(new ImageIcon(newArtwork).getImage().getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING));
        this.repaint();
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    private static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

}