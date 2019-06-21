package jpotify.model;

import java.util.List;

public class RecentlyPlayedPlaylist extends Playlist {

    private static final long serialVersionUID = -4412819728830654673L;
    private Song currentSong = null;

    public RecentlyPlayedPlaylist(boolean changeable) {
        super(changeable);
    }

    public void setCurrentSong(Song currentSong) {
        addSong(currentSong);
        this.currentSong = currentSong;
    }

    @Override
    public List<Song> getSongs() {
        doSort();
        return super.getSongs();
    }

    public void removeCurrentSong() {
        currentSong = null;
    }

    public Song getCurrentSong() {
        return currentSong;
    }
}
