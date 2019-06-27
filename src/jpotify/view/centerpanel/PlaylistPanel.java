package jpotify.view.centerpanel;

import jpotify.controller.MainController;
import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlaylistPanel extends JPanel {
    private MainController controller;
    private SongsPanel songsPanel;
    private JPlaylistSong songs;
    private JPanel playlistList;
    private JPanel controlPanel;
    private JPanel infoPanel;

    public PlaylistPanel(MainController mainController, String name, ArrayList<JPlaylistSong> songs) {
        controller = mainController;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(CenterPanelView.ELEMENTS, CenterPanelView.ELEMENTS));
        this.setBackground(Color.cyan);
        this.setVisible(true);
        setInfoPanel(name);
        setControlPanel();
        setPlaylistList(songs);
        this.add(infoPanel);
        this.add(controlPanel);
        this.add(new JScrollPane(playlistList));
    }

    private void setInfoPanel(String name) {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setMaximumSize(new Dimension(CenterPanelView.WIDTH, 50));
        JLabel label = new JLabel(name);
        JButton delete = new JButton("delete");
        JButton add = new JButton("add song");
        JButton edit = new JButton("edit name");
        this.add(label);
        this.add(edit);
        this.add(add);
        this.add(delete);
        this.setVisible(true);
    }

    private void setControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(Color.MAGENTA);
        controlPanel.setMaximumSize(new Dimension(CenterPanelView.WIDTH, 50));
        JButton playAll = new JButton("playAll");
        JButton moveUp = new JButton("moveUp");
        JButton moveDown = new JButton("moveDown");
        JButton deleteSong = new JButton("deleteSong");
        for (JButton j : new JButton[]{playAll, moveUp, moveDown, deleteSong}) {
            controlPanel.add(j);
        }
        controlPanel.setVisible(true);
    }

    private void setPlaylistList(ArrayList<JPlaylistSong> songs) {
        playlistList = new JPanel();
        playlistList.setLayout(new BoxLayout(playlistList,BoxLayout.Y_AXIS));
        playlistList.setMaximumSize(new Dimension(CenterPanelView.WIDTH, MainView.HEIGHT - 150));
        for (JPlaylistSong song : songs){
            playlistList.add(song);
            song.setVisible(true);
        }
    }
}
