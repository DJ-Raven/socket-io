package data;

import com.corundumstudio.socketio.SocketIOClient;

public class DataClient {

    public SocketIOClient getClient() {
        return client;
    }

    public void setClient(SocketIOClient client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataClient(SocketIOClient client, String name) {
        this.client = client;
        this.name = name;
    }

    public DataClient() {
    }

    private SocketIOClient client;
    private String name;

    public Object[] toRowTable(int row) {
        return new Object[]{this, row, name};
    }
}
