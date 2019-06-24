package jpotify.view.leftpanel;

import jpotify.view.Listeners.PanelChangeListener;
import jpotify.view.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LibraryBar extends JPanel {

    private static final int ELEMENTS_SIZE = 15;
    private PanelChangeListener panelChangeListener;
    private JButton addSong = new JButton();
    private JButton songs = new JButton();
    private JButton albums = new JButton();
    private JFileChooser fileChooser = new JFileChooser();
    private ButtonListener buttonListener = new ButtonListener();

    public LibraryBar() {

        //overall sets
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT + 50));

        Border outerB = BorderFactory.createMatteBorder(0,15,5,0, Color.black);
        Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0,0,5,0, Color.black);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);

        //home
        JLabel home = new JLabel("Home                ");
        home.setFont(new Font("Arial", Font.PLAIN, 20));
        home.setForeground(Color.white);
        home.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
        this.add(home);

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Add.png")));
            addSong.setIcon(new ImageIcon(icon.getImage().getScaledInstance(MainView.ICON, MainView.ICON, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addSong.setText("Add Song");
        addSong.setBackground(Color.BLACK);
        addSong.setForeground(Color.lightGray);
        addSong.setBorder(BorderFactory.createMatteBorder(5, 20, 0, 0, Color.BLACK));
        addSong.addActionListener(buttonListener);
        this.add(addSong);

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Music Note.png")));
            songs.setIcon(new ImageIcon(icon.getImage().getScaledInstance(MainView.ICON, MainView.ICON, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        songs.setText("My music");
        songs.setBackground(Color.BLACK);
        songs.setForeground(Color.lightGray);
        this.add(songs);
        songs.addActionListener(buttonListener);
        songs.setBorder(BorderFactory.createMatteBorder(5, 30, 0, 0, Color.BLACK));

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Album.png")));
            albums.setIcon(new ImageIcon(icon.getImage().getScaledInstance(MainView.ICON, MainView.ICON, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        albums.setText("Albums");
        albums.setBackground(Color.BLACK);
        albums.setForeground(Color.lightGray);
        this.add(albums);
        albums.addActionListener(buttonListener);
        albums.setBorder(BorderFactory.createMatteBorder(5, 30, 0, 0, Color.BLACK));

        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH,100));
        this.setVisible(true);
    }

    public void setPanelChangeListener(PanelChangeListener pl){
        this.panelChangeListener = pl;
    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(addSong)){
                System.out.println("works correctly");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Mp3 File", "mp3");
                fileChooser.setFileFilter(filter);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileChooser.setMultiSelectionEnabled(true);
                int f = fileChooser.showOpenDialog(null);
                if (f == JFileChooser.CANCEL_OPTION){
                }
                else if (f == JFileChooser.APPROVE_OPTION){
                    File [] file = fileChooser.getSelectedFiles();
                }
            }
            if (e.getSource().equals(songs)){
                panelChangeListener.DisplayPanel("songs");
            }
            if (e.getSource().equals(albums)){
                panelChangeListener.DisplayPanel("albums");
            }
        }
    }
}
