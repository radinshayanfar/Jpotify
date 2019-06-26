package jpotify.view.centerpanel;

import jpotify.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SongsPanel extends JPanel {

    private MainController controller;
    private ArrayList<Jsong> songs = new ArrayList<>();

    public SongsPanel(MainController mainController) {
        controller = mainController;
        this.setSize(CenterPanelView.ELEMENTS, CenterPanelView.ELEMENTS);
        this.setBackground(new Color(14,14,14));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        songs.add(new Jsong("df","df"," fd", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("df","sdfa"," fd1", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("df","sdfa"," fd21", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("df","sdafsdf"," fd5", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("df"," fd"," fd5", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("df","sdfsf"," fd4", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("df","asfsdv"," fd6", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("df","dfsdf"," fd", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("df","Mms"," fd1", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("Mybums","df"," fd21", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("Ms","df"," fd5", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("df","Myums"," fd5", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("Myums","Myums"," fd4", "./assets/sampleAlbum.png"));
//        songs.add(new Jsong("Myums","Myums"," fd6", "./assets/sampleAlbum.png"));
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

        for (Jsong s: this.songs)
            this.add(s);

//        for(int i = 0 ; i < this.songs.size() ; i++){
//            JPanel panel = new JPanel();
//            if(i%4 == 0){
//                panel.setLayout(new GridLayout(1,4));
//                panel.setPreferredSize(new Dimension(760,200));
//                panel.setMaximumSize(new Dimension(760,200));
//                for (int j = i; j < i+4 && j != this.songs.size() ; j++){
//                    panel.add(songs.get(j));
//                }
//            }
//            this.add(panel);
//        }
        revalidate();
    }

}