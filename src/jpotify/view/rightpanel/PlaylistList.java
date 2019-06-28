package jpotify.view.rightpanel;

import jpotify.controller.MainController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlaylistList extends JFrame implements ActionListener {

    private MainController controller;
    private JLabel name = new JLabel();
    private JButton btn = new JButton("Download All");
    private JButton downloadBtn = new JButton("Download Single");
    private ListHandler listHandler = new ListHandler();
    private JList songsList;
    private String host;
    private int port;

    public PlaylistList(MainController mainController, String playlistName, ArrayList<String> playlistSong, String host, int port) throws HeadlessException {
        controller = mainController;

        this.port = port;
        this.host = host;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(300, 400);
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));

        name.setText(playlistName);
        this.add(name, BorderLayout.NORTH);

        songsList = new JList(playlistSong.toArray());
        songsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(songsList);
        this.add(scrollPane, BorderLayout.CENTER);
        songsList.addListSelectionListener(listHandler);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.setPreferredSize(new Dimension(300,30));
        buttonPanel.add(btn);
        buttonPanel.add(downloadBtn);
        btn.addActionListener(this);
        downloadBtn.addActionListener(this);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(false);
    }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btn)){
                //TODO
                JOptionPane.showMessageDialog(null, "Now you can find these songs in the download file!", "Download Complete", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("download all");
            }
            if (e.getSource().equals(downloadBtn)){
                System.out.println(songsList.getSelectedValue());
                String str = (String) songsList.getSelectedValue();
                str = str.concat("Is now saved on your device");
                JOptionPane.showMessageDialog(null, str, "Download Complete", JOptionPane.INFORMATION_MESSAGE);
                //TODO download the song
            }
            this.setVisible(false);
        }

    private class ListHandler implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            System.out.println(songsList.getSelectedIndex() + "play");
            songsList.getSelectedIndex();
            controller.playSelectedSongFromNetwork(host, port, songsList.getSelectedIndex() );
        }
    }
}
