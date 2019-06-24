package jpotify.view.leftpanel;

import jpotify.view.Listeners.PanelChangeListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class PlaylistBar extends JPanel {

    private static final int ELEMENTS_SIZE = 15;
    private JScrollPane scrollPane = new JScrollPane();
    private JComboBox playLists;
    private Vector<Object> items = new Vector<>();
    private JButton addPlaylist = new JButton();
    private PlayListHandler playListHandler;
    private PanelChangeListener panelChangeListener;
//    private NewPlaylistHandler nplh = new NewPlaylistHandler();

    public PlaylistBar(){
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        Border outerB = BorderFactory.createMatteBorder(50,15,5,0, Color.BLACK);
        Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0,0,5,0, Color.BLACK);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);
        //playlist label
        JLabel playlistLabel = new JLabel("Playlists         ");
        playlistLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        playlistLabel.setForeground(Color.white);
        playlistLabel.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
        this.add(playlistLabel, BorderLayout.NORTH);

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Add.png")));
            addPlaylist.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addPlaylist.setText("Create a New Playlist");
        addPlaylist.setBackground(Color.BLACK);
        addPlaylist.setForeground(Color.lightGray);
        addPlaylist.setBorder(BorderFactory.createMatteBorder(5, 0, 10, 0, Color.BLACK));
        this.add(addPlaylist, BorderLayout.SOUTH);

        refreshList("Favorites");
        refreshList("Shared");
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        this.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());
        panel.add(playLists , BorderLayout.NORTH);

        playLists.addActionListener(new PlayListHandler());

        this.setVisible(true);
    }

    public void refreshList(Object newItem){
        items.add(newItem);
        playLists = new JComboBox(items);
        playLists.setForeground(Color.white);
        playLists.setBackground(Color.black);
        playLists.setPreferredSize(new Dimension(LeftPanelView.WIDTH, 30));
        this.revalidate();
    }

    public void refreshList(Object [] newItem){
        for ( Object o : newItem )
            refreshList(o);
    }

    public void setPanelChangeListener(PanelChangeListener panelChangeListener) {
        this.panelChangeListener = panelChangeListener;
    }

    private class PlayListHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelChangeListener.DisplayPanel((String)playLists.getSelectedItem());
        }
    }

}
