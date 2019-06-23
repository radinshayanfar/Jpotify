package jpotify.model;

import helper.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NetworkPlaylist extends Playlist {
    private static final long serialVersionUID = 5262883635031145552L;
    private String host;
    private int port;

    public NetworkPlaylist(Playlist playlist, String host, int port) {
        super(playlist.getSongs());
        this.host = host;
        this.port = port;
    }

    @Override
    public Song next(RepeatRule rule) {
        Song ret = super.next(rule);
        return getSong(ret);
    }

    @Override
    public Song previous(RepeatRule rule) {
        Song ret = super.previous(rule);
        return getSong(ret);
    }

    private Song getSong(Song ret) {
        if (ret == null) return null;
        try {
            ret = new Song(new File(FileHelper.downloadSongToTemporaryDirectory(host, port, songs.indexOf(ret))));
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
