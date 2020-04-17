import java.net.InetAddress;

public class ClientData {
    private InetAddress address;
    private int port;

    public ClientData(int port, InetAddress address) {
        this.port = port;
        this.address = address;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
