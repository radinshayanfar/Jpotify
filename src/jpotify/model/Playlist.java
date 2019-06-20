package jpotify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Playlist implements Serializable {
    private static final long serialVersionUID = 4264242358501632849L;
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

    public Playlist(String name, boolean changeable) {
        this(name);
        this.changeable = changeable;
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
