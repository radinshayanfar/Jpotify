package jpotify.model;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import helper.FileHelper;
import helper.TagReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class Song implements Serializable, Comparable<Song> {
    private static final long serialVersionUID = -8475149752149561117L;
    private File address;
    private long lastPlayed = 1L;
    private Album albumReference;
    private String title;
    private String album;
    private String artist;
    private transient byte[] artwork;

    public Song(File address) throws FileNotFoundException {

        if (!address.exists() || !address.isFile()) throw new FileNotFoundException();
        this.address = address;

        processId3Tags();
        processArtwork();

    }

    /**
     * Processes address and fill title, album and artists with song tags
     */
    private void processId3Tags() {
        try {
            Mp3File mp3file = new Mp3File(address);
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                title = id3v2Tag.getTitle();
                album = id3v2Tag.getAlbum();
                artist = id3v2Tag.getArtist();
            } /*else if (mp3file.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                title = id3v1Tag.getTitle();
                album = id3v1Tag.getAlbum();
                artist = id3v1Tag.getArtist();
            }*/ else if (TagReader.tagExists(address)) {
                TagReader tag = new TagReader(address);
                title = tag.getTitle();
                album = tag.getAlbum();
                artist = tag.getArtist();
            }
            if (title == null) {
                String name = address.getName();
                title = name.substring(0, name.lastIndexOf('.'));
            }
            if (artist == null) {
                artist = "";
            }
            if (album == null)
                album = "";
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes address and fill title, album and artists with song artwork
     */
    private void processArtwork() {
        try {
            Mp3File mp3file = new Mp3File(address);
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                artwork = id3v2Tag.getAlbumImage();

            }
            if (artwork == null) {
                artwork = FileHelper.loadSampleArtwork();
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads default artwork image for songs who doesn't have artwork or songs over network
     */
    public void useDefaultArtwork() {
        try {
            artwork = FileHelper.loadSampleArtwork();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getArtwork() {
        if (artwork == null) {
            processId3Tags();
            processArtwork();
        }
        return artwork;
    }

    /**
     * Updates song last played time with current time
     */
    public void updateLastPlayed() {
        lastPlayed = Instant.now().toEpochMilli();
    }

    @Override
    public int compareTo(Song o) {
        long diff = lastPlayed - o.lastPlayed;
        return diff < 0 ? 1 : (diff == 0 ? 0 : -1);
    }

    public File getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public long getLastPlayed() {
        return lastPlayed;
    }

    public Album getAlbumReference() {
        return albumReference;
    }

    public void setAlbumReference(Album albumReference) {
        this.albumReference = albumReference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return address.equals(song.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

//    TODO: change album if changed


    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                '}';
    }
}
