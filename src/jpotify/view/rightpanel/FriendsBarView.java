package jpotify.view.rightpanel;

import jpotify.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FriendsBarView extends JPanel {
    static final int WIDTH = 200, FRIEND_HEIGHT = 60;
    private ArrayList<Friend> friends = new ArrayList<>();
    private JList friendsList;
    private FriendHandler fh = new FriendHandler();

     public FriendsBarView(){
         this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         this.setPreferredSize(new Dimension(WIDTH, MainView.HEIGHT));
         this.setBackground(Color.MAGENTA);

         this.setVisible(true);
     }

    public void setFriends(ArrayList<Friend> friends){
         this.invalidate();
         this.removeAll();
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
            System.out.println(f.getTitle());
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
    }
}
