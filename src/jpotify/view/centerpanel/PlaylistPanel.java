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
    public static final int PANELWIDTH = 400;
    private MainController controller;
    private JPanel playlistList, controlPanel, infoPanel;
    private JButton edit, add, delete, playAll;
    private JLabel label;
    private Dimension buttonDimention = new Dimension(100, 21);
    private boolean changable;

    public PlaylistPanel(MainController mainController, String name, ArrayList<JPlaylistSong> songs, boolean changable) {
        controller = mainController;
        this.changable = changable;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(CenterPanelView.WIDTH, CenterPanelView.WIDTH));
        this.setBackground(new Color(14, 14, 14));
        this.setVisible(true);
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
        setInfoPanel(name);
        setPlaylistList(songs);
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(playlistList, BorderLayout.CENTER);
//        this.add(controlPanel);
        this.add(new JScrollPane(playlistList));
    }

    private void setInfoPanel(String name) {
        infoPanel = new JPanel();
        infoPanel.setBackground(new Color(14, 14, 14));
        infoPanel.setLayout(new GridLayout(2, 1));
        infoPanel.setPreferredSize(new Dimension(PANELWIDTH, 60));
        infoPanel.setMaximumSize(new Dimension(CenterPanelView.WIDTH, 60));

        label = new JLabel(name);
        label.setForeground(new Color(149, 0, 22));
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(label);
        setControlPanel();
        infoPanel.add(controlPanel);

        this.setVisible(true);
    }

    private void setControlPanel() {

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.BLACK);
        controlPanel.setVisible(true);
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setPreferredSize(new Dimension(PANELWIDTH, 30));
        controlPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(149, 0, 22)));
        controlPanel.setMaximumSize(new Dimension(CenterPanelView.WIDTH, 30));

        playAll = new JButton("Play All");
        add = new JButton("Add New Song");
        edit = new JButton("Edit Playlist Name");
        delete = new JButton("delete Playlist");

        JButton[] buttons;
        if (changable)
            buttons = new JButton[]{playAll, add, edit, delete};
        else
            buttons = new JButton[]{playAll, add};

        for (JButton button : buttons) {
            button.setBackground(Color.BLACK);
            button.setForeground(Color.LIGHT_GRAY);
            button.setBorderPainted(false);
            button.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(149, 0, 22)));
            button.setPreferredSize(buttonDimention);
            controlPanel.add(button);
            button.addMouseListener(this);
        }

        infoPanel.add(controlPanel);
        controlPanel.setVisible(true);
    }

    private void setPlaylistList(ArrayList<JPlaylistSong> songs) {
        playlistList = new JPanel();
        playlistList.setLayout(new BoxLayout(playlistList, BoxLayout.Y_AXIS));
        playlistList.setPreferredSize(new Dimension(PANELWIDTH, MainView.HEIGHT));
        playlistList.setMaximumSize(new Dimension(CenterPanelView.WIDTH, MainView.HEIGHT));
        playlistList.setBackground(new Color(14, 14, 14));
        playlistList.setBorder(null);
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
        if (e.getSource() == delete) {
            controller.removePlaylist();
        }
        if (e.getSource() == edit) {
            System.out.println("edit");
            changePlaylistNamePreparation();
        }
    }

    private void changePlaylistNamePreparation() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel enterName = new JLabel("Enter Name:");
        JTextField newName = new JTextField();
        JButton ok = new JButton("ok");
        JButton cancel = new JButton("cancel");
        frame.setLayout(new GridLayout(2, 2));
        frame.setSize(new Dimension(200, 100));
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
                if (!ret.equals("")) {
                    if (!ret.equals(label.getText())) {
                        controller.changePlaylistName(ret);
                        frame.dispose();
                    } else
                        JOptionPane.showMessageDialog(null, "Please Enter a New Name", "Warning", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please Fill the Name Field", "Warning", JOptionPane.ERROR_MESSAGE);
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
