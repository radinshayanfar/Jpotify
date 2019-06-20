package jpotify.model.Network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jpotify.model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class getSharedListHandler implements HttpHandler {
    private User user;

    public getSharedListHandler(User user) {
        this.user = user;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (user.getIPs().contains(exchange.getRemoteAddress().getHostString())) {
            try {
                // ok, we are ready to send the response.
                exchange.sendResponseHeaders(200, 0);
                ObjectOutputStream out = new ObjectOutputStream(exchange.getResponseBody());
                out.writeObject(user.getSharedPlaylist());
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
