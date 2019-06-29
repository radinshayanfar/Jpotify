package jpotify.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist extends SongList {
    private static final long serialVersionUID = 4264242358501632849L;
    private boolean changeable = true;
    private String name;

    public Playlist(boolean changeable) {
        this.changeable = changeable;
    }

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

    public Playlist(List<Song> songs) {
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

    public void addSong(Song song) {
        if (song != null && !songs.contains(song)) {
            songs.add(song);
        }
    }

    /**
     * Changes playlist songs order. moves selected song up in list
     * @param index index of song to be moved up in playlist
     */
    public void moveUp(int index) {
        if (index > 0) {
            Collections.swap(songs, index, index - 1);
        }
    }

    /**
     * Changes playlist songs order. moves selected song down in list
     * @param index index of song to be moved down in playlist
     */
    public void moveDown(int index) {
        if (index < songs.size() - 1)
            Collections.swap(songs, index, index + 1);
    }

    /**
     * Returns shuffled version of playlist
     * @return shuffled playlist object
     */
    public Playlist getShuffled() {
        Playlist ret = new Playlist(new ArrayList<>(songs));
        Collections.shuffle(ret.songs);
        ret.current = this.current;
        return ret;
    }

    public void removeSong(int index) {
        songs.remove(index);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }
}
