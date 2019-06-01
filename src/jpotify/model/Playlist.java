package jpotify.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private boolean changeable = true;
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public Playlist(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public boolean isChangeable() {
        return changeable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (changeable)
            this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }


}
