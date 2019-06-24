package jpotify.model;

import com.mpatric.mp3agic.*;
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
            } else {
                String name = address.getName();
                title = name.substring(0, name.lastIndexOf('.'));
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
    }

    private void processArtwork() {
        try {
            Mp3File mp3file = new Mp3File(address);
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                artwork = id3v2Tag.getAlbumImage();
                if (artwork == null) {
//                TODO: use default artwork
                }
            } else {
//                TODO: use default artwork
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
    }

    public void useDefaultArtwork() {
//        TODO: use default artwork
    }

    public byte[] getArtwork() {
        if (artwork == null) {
            processId3Tags();
            processArtwork();
        }
        return artwork;
    }

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
