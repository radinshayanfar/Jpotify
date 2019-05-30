package jpotify.view.bottompanel.songcontrolpanel;

import jpotify.view.bottompanel.BottomPanelView;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {

    private JLabel leftLabel = new JLabel();
    private JLabel rightLabel = new JLabel();
    private JSlider songSlider = new JSlider(JSlider.HORIZONTAL);

    public BottomPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.white);

        leftLabel.setText("2:34");
        rightLabel.setText("5:35");
        songSlider.setPreferredSize(new Dimension(350, 50));
        songSlider.setBackground(Color.white);
        this.add(leftLabel);
        this.add(songSlider);
        this.add(rightLabel);
    }
}
