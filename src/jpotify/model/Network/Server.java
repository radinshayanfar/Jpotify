package jpotify.model.Network;

import com.sun.net.httpserver.HttpServer;
import jpotify.model.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    private User user;
    private HttpServer server;

    public Server(User user) throws IOException {
        this.user = user;
        server = HttpServer.create(new InetSocketAddress(3245), 0);
        server.createContext("/getSharedList", new getListHandler(user, user::getSharedPlaylist));
        server.createContext("/getRecentlyPlayedList", new getListHandler(user, user::getRecentlyPlayed));
        server.createContext("/getSong", new getSongHandler(user));
        server.setExecutor(Executors.newCachedThreadPool()); // creates a default executor
        server.start();
    }

    public void stopServer() {
        server.stop(0);
    }
}
