package jpotify.model.Network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jpotify.model.SongList;
import jpotify.model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.function.Supplier;

public class getListHandler implements HttpHandler {
    private User user;
    private Supplier<SongList> playlistSupplier;

    public getListHandler(User user, Supplier<SongList> playlistSupplier) {
        this.user = user;
        this.playlistSupplier = playlistSupplier;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (user.getIPs().contains(exchange.getRemoteAddress().getHostString())) {
            try {
                // ok, we are ready to send the response.
                exchange.sendResponseHeaders(200, 0);
                ObjectOutputStream out = new ObjectOutputStream(exchange.getResponseBody());
                out.writeObject(user.getName());
                out.writeObject(playlistSupplier.get());
                out.close();
            } catch (Exception e) {
                exchange.sendResponseHeaders(400, 0);
                exchange.close();
                e.printStackTrace();
            }
        }
        exchange.sendResponseHeaders(403, 0);
        exchange.close();
    }
}
