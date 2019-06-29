package jpotify.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class ButtonPanel extends JPanel {

    public JLabel label = new JLabel();
    private ImagePanel imagePanel;

    public ButtonPanel(String label, Dimension dimension, MouseListener mouseListener, ImagePanel imagePanel){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(imagePanel != null)
            this.add(imagePanel, BorderLayout.WEST);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.label.setText(label);
        this.label.setForeground(Color.lightGray);
        this.add(this.label, BorderLayout.CENTER);
        this.label.setBorder(new EmptyBorder(0,20,0,0));
        this.setBackground(Color.BLACK);
        this.addMouseListener(mouseListener);
        this.setVisible(true);
    }
}