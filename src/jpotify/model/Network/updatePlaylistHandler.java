package jpotify.model.Network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jpotify.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;

public class updatePlaylistHandler implements HttpHandler, ChangeableUser {

    private User user;

    public updatePlaylistHandler(User user) {
        this.user = user;
    }

    @Override
    public void changeUser(User user) {
        this.user = user;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String host = exchange.getRemoteAddress().getHostString();
        if (user.getAllowedIPs().contains(exchange.getRemoteAddress().getHostString())) {
            ObjectInputStream in = new ObjectInputStream(exchange.getRequestBody());
            int port = in.readInt();
            NetworkPlaylist playlist = null;
            try {
                playlist = new NetworkPlaylist(((Playlist) in.readObject()).getSongs(), host, port);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(playlist.getSongs());
            user.addSharedPlaylist(playlist);
            exchange.sendResponseHeaders(200, 0);
            exchange.close();
        }
        exchange.sendResponseHeaders(403, 0);
        exchange.close();
    }
}
