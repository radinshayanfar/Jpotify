package jpotify.view.centerpanel;

import jpotify.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SongsPanel extends JPanel {

    private MainController controller;
    private ArrayList<JSong> songs = new ArrayList<>();

    public SongsPanel(MainController mainController, ArrayList<JSong> songs) {
        controller = mainController;
        this.setSize(CenterPanelView.ELEMENTS, CenterPanelView.ELEMENTS);
        this.setBackground(new Color(14,14,14));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (JSong s :songs) {
            this.add(s);
        }
        setVisible(false);
    }



}