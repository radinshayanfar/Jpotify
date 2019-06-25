package jpotify.view.centerpanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SongsPanel extends JPanel {

    private ArrayList<Jsong> songs = new ArrayList<>();

    public SongsPanel() {
        this.setSize(CenterPanelView.ELEMENTS, CenterPanelView.ELEMENTS);
        this.setBackground(new Color(14,14,14));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        songs.add(new Jsong("df"," fd", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("sdfa"," fd1", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("sdfa"," fd21", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("sdafsdf"," fd5", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("sadfsdf"," fd5", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("sdfsf"," fd4", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("asfsdv"," fd6", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("dfsdf"," fd", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("Mms"," fd1", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("Mybums"," fd21", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("Ms"," fd5", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("Myums"," fd5", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("Myums"," fd4", "./assets/sampleAlbum.png"));
        songs.add(new Jsong("Myums"," fd6", "./assets/sampleAlbum.png"));
        refresh(songs);

        setVisible(false);
    }

    public void refresh(Jsong song){
        songs.add(song);
        refresh(songs);
    }

    public void refresh(ArrayList<Jsong> songs){
        invalidate();
        removeAll();
        for (Jsong s : songs)
            if(!this.songs.contains(s))
                this.songs.add(s);
        for(int i = 0 ; i < this.songs.size() ; i++){
            JPanel panel = new JPanel();
            if(i%4 == 0){
                panel.setLayout(new GridLayout(1,4));
                panel.setPreferredSize(new Dimension(760,200));
                panel.setMaximumSize(new Dimension(760,200));
                for (int j = i; j < i+4 && j != this.songs.size() ; j++){
                    panel.add(songs.get(j));
                }
            }
            this.add(panel);
        }
        revalidate();
    }

}