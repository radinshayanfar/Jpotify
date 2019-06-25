package jpotify;

import jpotify.view.MainView;
import jpotify.view.rightpanel.PlaylistList;
import jpotify.view.leftpanel.CreateNewPlaylist;

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
        CreateNewPlaylist p = new CreateNewPlaylist(s);
        PlaylistList pl = new PlaylistList("play", s);
        pl.setVisible(true);

        p.setVisible(true);

    }

}
