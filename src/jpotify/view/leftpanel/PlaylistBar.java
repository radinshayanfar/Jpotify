package jpotify.view.leftpanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PlaylistBar extends JPanel {

    private static final int ELEMENTS_SIZE = 10;

    private JButton createPlaylist = new JButton();
    private List<String> list;
    private JList<String> playlistList = new JList<>();

    public PlaylistBar(List<String> list) {
        this.list = list;

        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createTitledBorder("Playlists"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Add.png")));
            createPlaylist.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createPlaylist.setText("Create playlist");
        createPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
        createPlaylist.setPreferredSize(new Dimension(LeftPanelView.WIDTH - 10, 30));
        createPlaylist.setBackground(Color.white);
        this.add(createPlaylist);

        playlistList.setListData(list.toArray(new String[list.size()]));
        playlistList.setPreferredSize(new Dimension(LeftPanelView.WIDTH - 30, playlistList.getMaximumSize().height));
        this.add(new JScrollPane(playlistList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
//        this.add(playlistList);

        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setVisible(true);
    }
}
