package jpotify.model;

public class RemoteClient {
    private String host;
    private int port;

    public RemoteClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
