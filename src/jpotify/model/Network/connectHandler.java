package jpotify.model.Network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jpotify.model.NetworkPlaylist;
import jpotify.model.Playlist;
import jpotify.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class connectHandler implements HttpHandler, ChangeableUser {

    private User user;

    public connectHandler(User user) {
        this.user = user;
    }

    @Override
    public void changeUser(User user) {
        this.user = user;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String host = exchange.getRemoteAddress().getHostString();
        if (user.getHosts().contains(exchange.getRemoteAddress().getHostString())) {
            ObjectInputStream in = new ObjectInputStream(exchange.getRequestBody());
            int port = in.readInt();
            NetworkPlaylist playlist = null;
            try {
                playlist = new NetworkPlaylist(((Playlist) in.readObject()).getSongs(), host, port);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            user.addSharedPlaylist(playlist);
            in.close();
            ObjectOutputStream out = new ObjectOutputStream(exchange.getResponseBody());
            out.writeObject(user.getSharedPlaylist());
            out.writeObject(user.getRecentlyPlayed());
            out.flush();
            out.close();
        }
        exchange.sendResponseHeaders(403, 0);
        exchange.close();
    }
}
