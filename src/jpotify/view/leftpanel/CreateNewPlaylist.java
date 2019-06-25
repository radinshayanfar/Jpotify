package jpotify.view.leftpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateNewPlaylist extends JFrame implements ActionListener {

    private JTextField name = new JTextField();
    private JButton cancelBtn = new JButton("cancel");
    private JButton okBtn = new JButton("okay");
    private JList songsList;

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

        songsList = new JList(mySongs.toArray());
        JScrollPane scrollPane = new JScrollPane(songsList);
//        songsList.setSelectionMode();
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
        }
        if (e.getSource().equals(okBtn)){
            System.out.println(songsList.getSelectedValuesList());
            System.out.println(name.getText());
            //TODO
            if(songsList.getSelectedValuesList().size() != 0)
                this.setVisible(false);
        }
    }
}
