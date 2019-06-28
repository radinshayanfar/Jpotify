package helper;

import jpotify.model.Users;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

final public class FileHelper {

    private FileHelper() {
    }

    public static ArrayList<String> ipFileToArrayList(String address) throws FileNotFoundException {
        ArrayList<String> ret = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(address))) {
            while (sc.hasNext()) {
                ret.add(sc.next());
            }
        }
        return ret;
    }

    public static void createDataDirectories() throws IOException {
        Files.createDirectories(Paths.get("data"));
        Files.createDirectories(Paths.get("data/tmp"));
    }

    public static void deleteTemporaryDirectory() {
        Arrays.stream(Objects.requireNonNull(new File("data/tmp").listFiles())).forEach(File::delete);
    }

    public static void saveUsers(Users users) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/users.jdf"));
        out.writeObject(users);
    }

    public static Users loadUsers() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/users.jdf"));
        return (Users) in.readObject();
    }

    public static String downloadSongToTemporaryDirectory(String host, int port, int index) throws IOException {
        URL url = new URL("http://" + host + ":" + port + "/getSong?i=" + index);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        String fileName = "data/tmp/" + StringHelper.randomString(10) + ".mp3";
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        return fileName;
    }

    public static byte[] loadSampleArtwork() throws IOException {
        return Files.readAllBytes(Paths.get("./assets/sample.png"));
    }
}
