package jpotify.view.leftpanel;

import jpotify.controller.MainController;
import jpotify.view.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PlaylistBar extends JPanel implements ActionListener {

    private MainController controller;
    private static final int ELEMENTS_SIZE = 15;
    private JScrollPane scrollPane = new JScrollPane();
    private JComboBox playLists;
    private HashMap<Integer, String> items;
    private JButton addPlaylist = new JButton();
    private JPanel panel;

    public PlaylistBar(MainController mainController, HashMap<Integer, String>items) {
        controller = mainController;
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        Border outerB = BorderFactory.createMatteBorder(50, 15, 5, 0, Color.BLACK);
        Border whiteLineB = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0, 0, 5, 0, Color.BLACK);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);
        //playlist label
        JLabel playlistLabel = new JLabel("Playlists         ");
        playlistLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        playlistLabel.setForeground(Color.white);
        playlistLabel.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
        this.add(playlistLabel, BorderLayout.NORTH);

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Add.png")));
            addPlaylist.setIcon(new ImageIcon(icon.getImage().getScaledInstance(MainView.ICON + 30, MainView.ICON + 30, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addPlaylist.setText("Create a New Playlist");
        addPlaylist.setBackground(Color.BLACK);
        addPlaylist.setForeground(Color.lightGray);
        addPlaylist.setBorder(BorderFactory.createMatteBorder(5, 0, 10, 0, Color.BLACK));
        this.add(addPlaylist, BorderLayout.SOUTH);
        addPlaylist.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.items = items;
        for (int i = 0 ; i < items.size() ; i++){
            panel.add(new PlaylistItem(i, items.get(i)));
        }
        JScrollPane scrollBar = new JScrollPane(panel);
        panel.setBackground(Color.black);
        this.add(scrollBar, BorderLayout.CENTER);


        this.setVisible(true);
    }

    public void refreshList(int index, String newItem) {
        PlaylistItem p = new PlaylistItem(index, newItem);
        panel.add(p);
//        p.focusGained(requestFocus());
        //TODO you can create a list of the items, set them all black and the selected one blue
        //TODO get all the inputs and fields of a focusEvent and make them happen yourself :)
        panel.revalidate();
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.createNewPlaylistFrame();
    }

    private class PlaylistItem extends JPanel implements MouseListener, FocusListener{
        private int index;
        private JLabel playlistLabel = new JLabel();

        public PlaylistItem(int index, String playlistName) {
            this.index = index;
            this.setBackground(Color.BLACK);
            this.setMaximumSize(new Dimension(190,30 ));
            playlistLabel.setText(playlistName);
            this.add(playlistLabel);
            playlistLabel.setForeground(Color.lightGray);
            this.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
            this.addMouseListener(this);
            this.addFocusListener(this);
            this.setVisible(true);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            controller.changeCenterPanel(MainController.PLAYLIST, this.index);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void focusGained(FocusEvent e) {
            this.setBackground(Color.blue);
        }

        @Override
        public void focusLost(FocusEvent e) {
            e.toString();
            System.out.println(e.toString());
            System.out.println(" "+e.getSource()+" "+e.getID()+" "+e.isTemporary()+" "+e.getOppositeComponent()+" "+e.getCause());
            this.setBackground(Color.black);
        }
    }
//    public void createNewPlaylist(String text, ArrayList<Integer> indexes) {
//        ArrayList<Song> songs = new ArrayList<>();
//        for (Integer i: indexes) {
//            songs.add(user.getLibrarySongs().get(i));
//        }
//        int lastIndex = user.newPlaylist(text, songs);
//        mainView.getLeftPanelView().getPlaylistBar().refreshList(lastIndex, text);
//        changeCenterPanel(PLAYLIST, lastIndex);
//////        for (Playlist p :
//////                user.getPlaylists()) {
//////            System.out.println(p.getName() + ": " + p.getSongs());
//////        }
//    }

}
