package helper;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class TagReader {
    private static final String HEADER = "TAG";

    private String title;
    private String artist;
    private String album;

    private File songFile;

    public TagReader(File songFile) throws IOException {
        this.songFile = songFile;

        readTags();
    }

    /**
     * Reads ID3v1 tags from song file
     * @throws IOException if file not found
     */
    private void readTags() throws IOException {
        if (tagExists(songFile)) {
            RandomAccessFile raf = new RandomAccessFile(songFile, "r");
            raf.seek(raf.length() - 125);
            byte[] buffer = new byte[125];
            if (raf.read(buffer, 0, 125) != 125) {
                raf.close();
                throw new IOException("Tag absent EX00");
            }
            raf.close();
            String tag = new String(buffer, 0, 125, StandardCharsets.ISO_8859_1);
            title = tag.substring(0, 30).trim();
            artist = tag.substring(30, 60).trim();
            album = tag.substring(60, 90).trim();
        }
    }

    /**
     * Check if ID3v1 tag is present or not
     *
     * @return true if tag is present
     */
    public static boolean tagExists(File file) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            if (raf.length() < 129) {
                return false;
            } else {
                long seekPos = raf.length() - 128;
                raf.seek(seekPos);

                byte[] buffer = new byte[3];

                if (raf.read(buffer, 0, 3) != 3) {
                    throw new IOException("Read beyond end of file");
                }

                String testTag = new String(buffer, 0, 3, StandardCharsets.ISO_8859_1);
                return testTag.equals(HEADER);
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }
}
