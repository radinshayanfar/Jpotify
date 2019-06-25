package jpotify;

import jpotify.view.MainView;
import jpotify.view.leftpanel.CreateNewPlaylist;
import jpotify.view.rightpanel.PlaylistList;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.getLeftPanelView().getLibraryBar().setPanelChangeListener(mainView.getCenterPanelView());
        mainView.getLeftPanelView().getPlaylistBar().setPanelChangeListener(mainView.getCenterPanelView());

        ArrayList<String> s = new ArrayList<>();
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");
        s.add("dsf");
        s.add("dsfsdf");
        s.add("sdfjksdklfj");
        s.add("sdf");
        s.add("sdfdsf");

//        ArrayList<Jsong> thisi = new ArrayList<>();
//        thisi.add(new Jsong("My albums"," fd", "./assets/sampleAlbum.png"));
//        thisi.add(new Jsong("My albums"," fd1", "./assets/sampleAlbum.png"));
//        thisi.add(new Jsong("My albums"," fd21", "./assets/sampleAlbum.png"));
//        thisi.add(new Jsong("My albums"," fd5", "./assets/sampleAlbum.png"));
//        thisi.add(new Jsong("My albums"," fd5", "./assets/sampleAlbum.png"));
//        thisi.add(new Jsong("My albums"," fd4", "./assets/sampleAlbum.png"));
//        thisi.add(new Jsong("My albums"," fd6", "./assets/sampleAlbum.png"));

        CreateNewPlaylist p = new CreateNewPlaylist(s);
        PlaylistList pl = new PlaylistList("play", s);
        pl.setVisible(true);

        p.setVisible(true);

    }

}
