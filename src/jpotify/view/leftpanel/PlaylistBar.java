package jpotify.view.leftpanel;

import jpotify.controller.MainController;
import jpotify.view.ButtonPanel;
import jpotify.view.ImagePanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class PlaylistBar extends JPanel implements MouseListener {

    private MainController controller;
    private static final int ELEMENTS_SIZE = 15;
    private JScrollPane scrollPane = new JScrollPane();
    private JComboBox playLists;
    private HashMap<Integer, String> items;
    private ButtonPanel addPlaylist;
    private JPanel panel;

    public PlaylistBar(MainController mainController, HashMap<Integer, String>items) {
        controller = mainController;
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        //playlist label
        Border outerB = BorderFactory.createMatteBorder(5, 15, 5, 5, Color.black);
        Border whiteLineB = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0, 0, 5, 0, Color.black);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB,inerB);
        JLabel playlistLabel = new JLabel("Playlists       ");
//        playlistLabel.setMaximumSize(new Dimension(180, 50));
        playlistLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        playlistLabel.setForeground(Color.white);
        playlistLabel.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
        this.add(playlistLabel, BorderLayout.NORTH);

        addPlaylist = new ButtonPanel("Create a New Playlist", new Dimension(this.getWidth(), 40), this, new ImagePanel("./assets/addd.png", new Dimension(18,18)));
        addPlaylist.setBorder(BorderFactory.createEmptyBorder(8,10,0,0));
        this.add(addPlaylist, BorderLayout.SOUTH);


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.items = items;
        for (int i = 0 ; i < items.size() ; i++){
            panel.add(new PlaylistItem(i, items.get(i),this));
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        panel.setBackground(Color.black);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().getView().setBackground(Color.BLACK);
        scrollPane.setBorder(null);
//        scrollPane.getVerticalScrollBar().View.setMaximumSize(new Dimension(3,50));
        this.setVisible(true);
    }

    public void refreshList(int index, String newItem) {
        PlaylistItem p = new PlaylistItem(index, newItem, this);
        panel.add(p);
//        p.focusGained(requestFocus());
        //TODO you can create a list of the items, set them all black and the selected one blue
        //TODO get all the inputs and fields of a focusEvent and make them happen yourself :)
        panel.revalidate();
        this.revalidate();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(addPlaylist)){
            controller.createNewPlaylistFrame();
        }
        else{
            PlaylistItem p = (PlaylistItem)e.getSource();
            controller.changeCenterPanel(MainController.PLAYLIST, p.index);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(addPlaylist)){
            addPlaylist.setBackground(new Color(149,0,22));
            addPlaylist.label.setForeground(Color.white);
        }
        else {
            PlaylistItem p = (PlaylistItem) e.getSource();
            p.setBackground(new Color(14,14,14));
            p.mySetBorder(new Color(14,14,14));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(addPlaylist)){
            addPlaylist.setBackground(Color.black);
            addPlaylist.label.setForeground(Color.lightGray);
        }
        else{
            PlaylistItem p = (PlaylistItem) e.getSource();
            p.setBackground(Color.black);
            p.mySetBorder(Color.BLACK);
        }
    }

    private class PlaylistItem extends JPanel implements FocusListener{
        private int index;
        private JLabel playlistLabel = new JLabel();

        public PlaylistItem(int index, String playlistName, MouseListener mouseListener) {
            this.index = index;
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.setBackground(Color.BLACK);
            this.setMaximumSize(new Dimension(190,30 ));
            playlistLabel.setText(playlistName);
            this.add(playlistLabel);
            playlistLabel.setForeground(Color.lightGray);
            this.mySetBorder(Color.BLACK);
            this.addMouseListener(mouseListener);
            this.addFocusListener(this);
            this.setVisible(true);
        }

        @Override
        public void focusGained(FocusEvent e) {
            System.out.println("GAINED");
        }

        @Override
        public void focusLost(FocusEvent e) {
            System.out.println("LOST");
            System.out.println(e.toString());
            System.out.println(" "+e.getSource()+" "+e.getID()+" "+e.isTemporary()+" "+e.getOppositeComponent()+" "+e.getCause());
            this.setBackground(Color.black);
        }

        public void mySetBorder(Color c){
            Border outerB = BorderFactory.createMatteBorder(0,15,5,5, Color.BLACK);
            Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.white);
            Border inerB = BorderFactory.createMatteBorder(0,10,5,0, c);
            Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);
            this.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
        }

    }
}
