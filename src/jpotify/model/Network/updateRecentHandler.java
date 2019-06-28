package jpotify.model.Network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jpotify.controller.MainController;
import jpotify.model.RecentlyPlayedPlaylist;
import jpotify.model.RemoteClient;
import jpotify.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;

public class updateRecentHandler implements HttpHandler, ChangeableUser {

    private User user;
    private MainController controller;

    public updateRecentHandler(User user, MainController controller) {
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
        if (user.getAllowedIPs().contains(exchange.getRemoteAddress().getHostString())) {
            ObjectInputStream in = new ObjectInputStream(exchange.getRequestBody());
            int port = in.readInt();
            String userName = null;
            RecentlyPlayedPlaylist playlist = null;
            try {
                userName = (String) in.readObject();
                playlist = (RecentlyPlayedPlaylist) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            user.addOthersRecentlyPlayed(new RemoteClient(host, port, userName), playlist);
            exchange.sendResponseHeaders(200, 0);
            exchange.close();
            controller.refreshFriendsBar();
        }
        exchange.sendResponseHeaders(403, 0);
        exchange.close();
    }
}
