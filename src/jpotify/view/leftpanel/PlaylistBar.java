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
    private JList<String> playlistList = new JList<>();

    public PlaylistBar() {
//        this.list = list;
        this.setBackground(Color.BLACK);
//        this.setBorder(BorderFactory.createTitledBorder("Playlists"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        playlistList.setListData(list.toArray(new String[list.size()]));
        playlistList.setPreferredSize(new Dimension(LeftPanelView.WIDTH - 30, playlistList.getMaximumSize().height));
        this.add(new JScrollPane(playlistList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        playlistList.setBackground(Color.BLACK);
        this.add(playlistList);

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Add.png")));
            createPlaylist.setIcon(new ImageIcon(icon.getImage().getScaledInstance(10,10, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createPlaylist.setText("Create playlist");
//        createPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
        createPlaylist.setPreferredSize(new Dimension(LeftPanelView.WIDTH , 30));
        createPlaylist.setBackground(Color.white);
        createPlaylist.setBorder(BorderFactory.createMatteBorder(10,20,10,20, Color.white));
        this.add(createPlaylist);

        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setVisible(true);
    }

    public void addListElements(List<String> list){
        playlistList.setListData(list.toArray(new String[list.size()]));
    }
}
