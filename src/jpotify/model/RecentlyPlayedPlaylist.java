package jpotify.model;

import java.util.List;

public class RecentlyPlayedPlaylist extends SongList {
    private static final long serialVersionUID = -4412819728830654673L;

    public void setCurrentSong(Song currentSong) {
        songs.add(0, currentSong);
        this.current = currentSong;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void removeCurrentSong() {
        this.current = null;
    }

    public Song getCurrentSong() {
        return this.current;
    }


}
