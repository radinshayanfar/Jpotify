package jpotify.view.rightpanel;

import jpotify.controller.MainController;
import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FriendsBarView extends JPanel {

    private MainController controller;
    static final int WIDTH = 200, FRIEND_HEIGHT = 60;
    private ArrayList<Friend> friends = new ArrayList<>();
    private JList friendsList;
    private FriendHandler fh = new FriendHandler();

     public FriendsBarView(MainController mainController){
         controller = mainController;
         this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT-500));
         this.setBackground(Color.BLACK);
         this.setVisible(true);
     }

    public void setFriends(ArrayList<Friend> friends){
         this.invalidate();
         this.removeAll();
         if(friends == null) return;
         for (Friend f : friends){
             this.add(f);
             f.addMouseListener(fh);
         }
         this.revalidate();
    }

    private class FriendHandler implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            Friend f = (Friend) e.getSource();
            System.out.println(f.getName());
            System.out.println(f.getCurrentSongTitle());
            //TODO Show Friends PlayList
            controller.showFriendPlaylist(f.getName(), f.getHost(), f.getPort());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            Friend f = (Friend) e.getSource();
            f.setBackground(new Color(34,34,34));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            Friend f = (Friend) e.getSource();
            f.setBackground(Color.BLACK);
        }
    }
}
