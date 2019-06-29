package jpotify.view.leftpanel;

import jpotify.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateNewPlaylist extends JFrame implements ActionListener {

    private MainController controller;
    private JTextField name = new JTextField();
    private JButton cancelBtn = new JButton("cancel");
    private JButton okBtn = new JButton("okay");
    private JList songsList;
    private ArrayList<String> songs  = new ArrayList<>();

    public CreateNewPlaylist(MainController mainController, ArrayList<String> mySongs) throws HeadlessException {
        controller = mainController;
        songs = mySongs;
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(300, 400);
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(2,2));
        JLabel nameLabel = new JLabel("Choose Name:");
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setForeground(new Color(149,0,22));
//        JLabel sondLabel = new JLabel("Choose Songs");
        this.add(upPanel, BorderLayout.NORTH);
        upPanel.add(nameLabel);
        upPanel.add(name);
//        upPanel.add(sondLabel);

        songsList = new JList(mySongs.toArray());
        songsList.setBackground(new Color(14,14,14));
        songsList.setForeground(Color.white);
        JScrollPane scrollPane = new JScrollPane(songsList);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.setPreferredSize(new Dimension(300,30));
        buttonPanel.add(cancelBtn);
        buttonPanel.add(okBtn);
        cancelBtn.addActionListener(this);
        okBtn.addActionListener(this);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(cancelBtn)){
            this.setVisible(false);
            this.dispose();
        }
        if (e.getSource().equals(okBtn)){
            if (!name.getText().equals("")) {
                ArrayList<Integer> indexes = new ArrayList<>();
                for (Object o : songsList.getSelectedValuesList()) {
                    String s = (String) o;
                    indexes.add(songs.indexOf(s));
                }
                controller.createNewPlaylist(name.getText(), indexes);
                if (songsList.getSelectedValuesList().size() != 0)
                    this.setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "Please Enter a Name","ERROR!",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
