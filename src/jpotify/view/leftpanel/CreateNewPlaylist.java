package jpotify.view.leftpanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateNewPlaylist extends JFrame {

    private JTextField name = new JTextField();
    private JButton cancelBtn = new JButton("cancel");
    private JButton okBtn = new JButton("okay");

    public CreateNewPlaylist(ArrayList mySongs) throws HeadlessException {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(300, 400);
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(2,2));
        JLabel nameLabel = new JLabel("Choose Name:");
//        JLabel sondLabel = new JLabel("Choose Songs");
        this.add(upPanel, BorderLayout.NORTH);
        upPanel.add(nameLabel);
        upPanel.add(name);
//        upPanel.add(sondLabel);

        JList songsList = new JList(mySongs.toArray());
        JScrollPane scrollPane = new JScrollPane(songsList);
//        songsList.setSelectionMode();
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.setPreferredSize(new Dimension(300,30));
        buttonPanel.add(cancelBtn);
        buttonPanel.add(okBtn);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(false);
    }

}
