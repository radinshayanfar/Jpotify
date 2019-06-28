package jpotify.model.Network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jpotify.controller.MainController;
import jpotify.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class connectHandler implements HttpHandler, ChangeableUser {

    private User user;
    private MainController controller;

    public connectHandler(User user, MainController controller) {
        this.user = user;
        this.controller = controller;
    }

    @Override
    public void changeUser(User user) {
        this.user = user;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String host = exchange.getRemoteAddress().getHostString();
        System.out.println("someone is trying to connect" + host);
        if (user.getAllowedIPs().contains(exchange.getRemoteAddress().getHostString())) {
            ObjectInputStream in = new ObjectInputStream(exchange.getRequestBody());
            int port = in.readInt();
            String userName = null;
            NetworkPlaylist playlist = null;
            RecentlyPlayedPlaylist recentlyPlayedPlaylist = null;
            try {
                userName = (String) in.readObject();
                playlist = new NetworkPlaylist(((Playlist) in.readObject()).getSongs(), host, port);
                recentlyPlayedPlaylist = (RecentlyPlayedPlaylist) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            user.addRemoteClient(host, port);
            RemoteClient connectedClient = new RemoteClient(host, port, userName);
            user.addSharedPlaylist(connectedClient, playlist);
            user.addOthersRecentlyPlayed(connectedClient, recentlyPlayedPlaylist);
            in.close();
            exchange.sendResponseHeaders(200, 0);
            ObjectOutputStream out = new ObjectOutputStream(exchange.getResponseBody());
            out.writeObject(user.getName());
            out.writeObject(user.getSharedPlaylist());
            out.writeObject(user.getRecentlyPlayed());
            out.flush();
            out.close();
            controller.refreshFriendsBar();
        }
        exchange.sendResponseHeaders(403, 0);
        exchange.close();
    }
}
