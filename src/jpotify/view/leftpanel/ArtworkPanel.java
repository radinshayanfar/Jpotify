package jpotify.view.leftpanel;

import jpotify.view.ImagePanel;

import java.awt.*;

public class ArtworkPanel extends ImagePanel {

    public ArtworkPanel(String img) {
        super(img);
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setBackground(Color.yellow);
        this.setVisible(true);
    }
    public ArtworkPanel(Image img){
        super(img);
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setBackground(Color.yellow);
        this.setVisible(true);
    }

}