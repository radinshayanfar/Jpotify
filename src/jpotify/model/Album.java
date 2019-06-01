package jpotify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Album implements Serializable, Comparable<Album> {
    private String name;
    private List<Song> songs = new ArrayList<>();
    private long lastPlayed = 1L;

    public Album(String name, Song song) {
        this.name = name;
        songs.add(song);
    }

    @Override
    public int compareTo(Album o) {
        long diff = lastPlayed - o.lastPlayed;
        return diff < 0 ? -1 : (diff == 0 ? 0 : 1);
    }

    public long getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(long lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void doSort() {
        Collections.sort(songs);
    }
}
