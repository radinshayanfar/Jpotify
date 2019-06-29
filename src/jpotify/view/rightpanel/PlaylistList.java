package jpotify.view.rightpanel;

import jpotify.controller.MainController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlaylistList extends JPanel implements ActionListener {

    private MainController controller;
    private JLabel name = new JLabel();
    private JButton downloadBtn = new JButton("Download Single");
    private ListHandler listHandler = new ListHandler();
    private JList songsList;
    private String host;
    private int port;

    public PlaylistList(MainController mainController, String playlistName, ArrayList<String> playlistSong, String host, int port) throws HeadlessException {
        controller = mainController;
        this.port = port;
        this.host = host;
//        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        name.setText(playlistName);
        this.add(name, BorderLayout.NORTH);
        this.setBackground(new Color(14,14,14));

        songsList = new JList(playlistSong.toArray());
        songsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(songsList);
        this.add(scrollPane, BorderLayout.CENTER);
        songsList.addListSelectionListener(listHandler);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));
        buttonPanel.setPreferredSize(new Dimension(300,30));
        buttonPanel.add(downloadBtn);
        downloadBtn.addActionListener(this);
        downloadBtn.setBackground(new Color(149,0,22));
        downloadBtn.setBorderPainted(false);
        downloadBtn.setForeground(Color.WHITE);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(false);
    }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(downloadBtn)){
                if((Integer) songsList.getSelectedValue()== -1)
                    return;
                System.out.println(songsList.getSelectedValue());
//                String str = (String) songsList.getSelectedValue();
                String str = controller.downloadSong(host, port, songsList.getSelectedIndex());
                JOptionPane.showMessageDialog(null, str, "Download Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    private class ListHandler implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            songsList.getSelectedIndex();
            controller.playSelectedSongFromNetwork(host, port, songsList.getSelectedIndex());
        }
    }
}
