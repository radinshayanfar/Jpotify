package jpotify.view.leftpanel;

import javax.swing.*;
import java.awt.*;

public class LibraryBar extends JPanel {
    public LibraryBar() {

        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createTitledBorder("Library"));

        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setVisible(true);
    }
}
