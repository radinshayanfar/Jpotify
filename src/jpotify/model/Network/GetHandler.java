package jpotify.model.Network;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helper.StringHelper;
import jpotify.model.User;

import java.io.*;
import java.util.Map;

public class GetHandler implements HttpHandler {

    private User user;

    public GetHandler(User user) {
        this.user = user;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> params = StringHelper.queryToMap(exchange.getRequestURI().getQuery());
        if (params.containsKey("i") && user.getIPs().contains(exchange.getRemoteAddress().getHostString())) {
            int index = Integer.valueOf(params.get("i"));
            Headers h = exchange.getResponseHeaders();
            h.add("Content-Type", "audio/mpeg");

            try {
                File file = user.getSharedPlaylist().getSongs().get(index).getAddress();
                byte[] byteArray = new byte[(int) file.length()];
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                bis.read(byteArray, 0, byteArray.length);

                // ok, we are ready to send the response.
                exchange.sendResponseHeaders(200, file.length());
                OutputStream os = exchange.getResponseBody();
                os.write(byteArray, 0, byteArray.length);
                os.close();
            } catch (Exception e) {
                exchange.sendResponseHeaders(400, 0);
                exchange.close();
                e.printStackTrace();
            }
        }
        exchange.sendResponseHeaders(400, 0);
        exchange.close();
    }
}
