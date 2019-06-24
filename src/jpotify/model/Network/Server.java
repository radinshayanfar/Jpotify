package jpotify.model.Network;

import com.sun.net.httpserver.HttpServer;
import jpotify.model.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    private HttpServer server;

    private connectHandler connectHandler;
    private updateRecentHandler updateRecentHandler;
    private updatePlaylistHandler updatePlaylistHandler;
    private getSongHandler getSongHandler;

    public Server(User user, int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        connectHandler = new connectHandler(user);
        updateRecentHandler = new updateRecentHandler(user);
        updatePlaylistHandler = new updatePlaylistHandler(user);
        getSongHandler = new getSongHandler(user);
        server.createContext("/connect", connectHandler);
        server.createContext("/updateRecent", updateRecentHandler);
        server.createContext("/updatePlaylist", updatePlaylistHandler);
        server.createContext("/getSong", getSongHandler);
        server.setExecutor(Executors.newCachedThreadPool()); // creates a default executor
        server.start();
    }

    public void changeUser(User user) {
        for (ChangeableUser handler :
                new ChangeableUser[]{connectHandler, updateRecentHandler, updatePlaylistHandler, getSongHandler}) {
            handler.changeUser(user);
        }
    }

    public void stopServer() {
        server.stop(0);
    }
}
