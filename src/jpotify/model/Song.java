package jpotify.model;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;

public class Song implements Serializable, Comparable<Song> {
    private File address;
    private long lastPlayed = 1L;
//    TODO: Album
    private byte[] artwork;

    public Song(File address) throws FileNotFoundException {

        if (!address.exists() || !address.isFile()) throw new FileNotFoundException();
        this.address = address;

        processArtwork();

    }

    private void processArtwork() {
        try {
            Mp3File mp3file = new Mp3File(address);
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                artwork = id3v2Tag.getAlbumImage();
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
    }

    public void updateLastPlayed() {
        lastPlayed = Instant.now().toEpochMilli();
    }

    @Override
    public int compareTo(Song o) {
        long diff = lastPlayed - o.lastPlayed;
        return diff < 0 ? -1 : (diff == 0 ? 0 : 1);
    }
}
