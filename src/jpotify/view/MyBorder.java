package jpotify.view;

import javax.swing.*;
import java.awt.*;

public class MyBorder extends JPanel {

        public static final int VERTICAL_BORDER = 0, HORIZONTAL_BORDER = 1;

        public MyBorder(int size, int alignment , Color c) {
            if(alignment == 1)
                this.setSize(new Dimension(size, 1));
            else
                this.setSize(new Dimension(1, size));
            this.setBackground(c);
        }
}
