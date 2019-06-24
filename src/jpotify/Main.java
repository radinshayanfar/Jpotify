package jpotify;

import jpotify.view.MainView;

public class Main {

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.getLeftPanelView().getLibraryBar().setPanelChangeListener(mainView.getCenterPanelView());
    }

}
