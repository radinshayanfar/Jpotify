package jpotify.model.Network;

import com.sun.net.httpserver.HttpServer;
import jpotify.model.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    private HttpServer server;

    private getListHandler getSharedList;
    private getListHandler getRecentlyPlayedList;
    private getSongHandler getSong;

    public Server(User user, int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        getSharedList = new getListHandler(user, user::getSharedPlaylist);
        getRecentlyPlayedList = new getListHandler(user, user::getRecentlyPlayed);
        getSong = new getSongHandler(user);
        server.createContext("/getSharedList", getSharedList);
        server.createContext("/getRecentlyPlayedList", getRecentlyPlayedList);
        server.createContext("/getSong", getSong);
        server.setExecutor(Executors.newCachedThreadPool()); // creates a default executor
        server.start();
    }

    public void changeUser(User user) {
        getSharedList.changeUser(user, user::getSharedPlaylist);
        getRecentlyPlayedList.changeUser(user, user::getRecentlyPlayed);
        getSong.changeUser(user);
    }

    public void stopServer() {
        server.stop(0);
    }
}
