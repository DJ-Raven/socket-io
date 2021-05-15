package data;

import com.corundumstudio.socketio.SocketIOClient;
import java.io.IOException;
import java.util.HashMap;

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
    //  Key integer is fileID
    //  Use hash to store multy transfer
    private final HashMap<Integer, DataWriter> list = new HashMap<>();

    public void addWrite(DataWriter data, int fileID) {
        list.put(fileID, data);
    }

    public void writeFile(byte[] data, int fileID) throws IOException {
        list.get(fileID).writeFile(data);
    }

    public void closeWriter(int fileID) throws IOException {
        list.get(fileID).close();
    }

    public Object[] toRowTable(int row) {
        return new Object[]{this, row, name};
    }
}
