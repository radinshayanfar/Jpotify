package jpotify.view.centerpanel;

import jpotify.controller.MainController;
import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PlaylistPanel extends JPanel implements MouseListener {
    private MainController controller;
    private JPanel playlistList;
    private JPanel controlPanel;
    private JPanel infoPanel;
    private JButton edit, add, delete, playAll, moveUp, moveDown, deleteSong;
    private JLabel label;
    private boolean changable;

    public PlaylistPanel(MainController mainController, String name, ArrayList<JPlaylistSong> songs, boolean changable) {
        controller = mainController;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(CenterPanelView.ELEMENTS, CenterPanelView.ELEMENTS));
        this.setBackground(Color.cyan);
        this.setVisible(true);
        this.changable = changable;
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
        label = new JLabel(name);
        add = new JButton("add song");
        this.add(label);
        this.add(add);
        add.addMouseListener(this);
        if (changable){
            edit = new JButton("edit name");
            edit.addMouseListener(this);
            this.add(edit);
            delete = new JButton("delete");
            this.add(delete);
            delete.addMouseListener(this);
        }
        this.setVisible(true);
    }

    private void setControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(Color.MAGENTA);
        controlPanel.setMaximumSize(new Dimension(CenterPanelView.WIDTH, 50));
        playAll = new JButton("playAll");
        moveUp = new JButton("moveUp");
        moveDown = new JButton("moveDown");
        deleteSong = new JButton("deleteSong");
        for (JButton j : new JButton[]{playAll, moveUp, moveDown, deleteSong}) {
            controlPanel.add(j);
            j.addMouseListener(this);
        }
        controlPanel.setVisible(true);
    }

    private void setPlaylistList(ArrayList<JPlaylistSong> songs) {
        playlistList = new JPanel();
        playlistList.setLayout(new BoxLayout(playlistList, BoxLayout.Y_AXIS));
        playlistList.setMaximumSize(new Dimension(CenterPanelView.WIDTH, MainView.HEIGHT - 150));
        for (JPlaylistSong song : songs) {
            playlistList.add(song);
            song.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        edit, add, delete, playAll, moveUp, moveDown, deleteSong;
        if (e.getSource() == playAll)
            controller.playSelectedSong(0);
        if (e.getSource() == add) {
            new AddSongToPlayList(controller.getLibrarySongsNames());
        }
        if (e.getSource() == delete){
            controller.removePlaylist();
        }
        if (e.getSource()== edit){
            changePlaylistNamePrepration();
        }
    }

    private void changePlaylistNamePrepration() {
        JFrame frame = new JFrame();
        JLabel enterName = new JLabel("Enter Name:");
        JTextField newName = new JTextField();
        JButton ok = new JButton("ok");
        JButton cancel = new JButton("cancel");
        frame.setLayout(new GridLayout(2,2));
        frame.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));
        frame.add(enterName);
        frame.add(newName);
        frame.add(cancel);
        frame.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ret = newName.getText();
                if (ret != null)
                    if(ret.equals(label.getText())) {
                        controller.changePlaylistName(ret);
                        frame.dispose();
                    }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
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

    private class AddSongToPlayList extends JFrame implements ActionListener {

        private JButton cancelBtn = new JButton("cancel");
        private JButton okBtn = new JButton("okay");
        private JList songsList;
        private ArrayList<String> songs = new ArrayList<>();

        public AddSongToPlayList(ArrayList<String> mySongs) throws HeadlessException {
            this.songs = mySongs;
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLayout(new BorderLayout());
            this.setSize(300, 400);
            this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                    , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));


            songsList = new JList(mySongs.toArray());
            JScrollPane scrollPane = new JScrollPane(songsList);
            this.add(scrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 2));
            buttonPanel.setPreferredSize(new Dimension(300, 30));
            buttonPanel.add(cancelBtn);
            buttonPanel.add(okBtn);
            cancelBtn.addActionListener(this);
            okBtn.addActionListener(this);
            this.add(buttonPanel, BorderLayout.SOUTH);

            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(cancelBtn)) {
                this.setVisible(false);
                this.dispose();
            }
            if (e.getSource().equals(okBtn)) {
                ArrayList<Integer> indexes = new ArrayList<>();
                for (Object o : songsList.getSelectedValuesList()) {
                    String s = (String) o;
                    indexes.add(songs.indexOf(s));
                    controller.addSongToPlaylist(indexes);
                }
                if (songsList.getSelectedValuesList().size() != 0) {
                    this.dispose();
                }
            }
        }
    }
}
