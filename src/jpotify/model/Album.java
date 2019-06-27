package jpotify.model;

import java.util.Collections;
import java.util.List;

public class Album extends SongList implements Comparable<Album> {
    private static final long serialVersionUID = 8033087536317087108L;

    public Album(Song song) {
        songs.add(song);
    }

    @Override
    public int compareTo(Album o) {
        Collections.sort(songs);
        long diff = getLastPlayed() - o.getLastPlayed();
        return diff < 0 ? 1 : (diff == 0 ? 0 : -1);
    }

    public long getLastPlayed() {
        doSort();
        return songs.get(0).getLastPlayed();
    }

    public String getName() {
        return songs.get(0).getAlbum();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getSongs() {
        doSort();
        return songs;
    }

    public String getTitles(){
        StringBuilder ret = new StringBuilder();
        for (int i = 0 ; i < 3 && i != songs.size() ; i++){
            ret.append(songs.get(i).getTitle() + ", ");
        }
        ret.delete(ret.length() - 2, ret.length());
        if (songs.size() > 3)
            ret.append(", ...");
        return ret.toString();
    }

    public byte[] getArtwork() {
        return songs.get(0).getArtwork();
    }

    private void doSort() {
        Collections.sort(songs);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    @Override
    public String toString() {
        return "Album{+"+ getName() +"}";
    }
}
