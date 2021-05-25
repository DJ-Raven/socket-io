package data;

public class DataRequestFile {

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public DataRequestFile(int fileID, long length) {
        this.fileID = fileID;
        this.length = length;
    }

    public DataRequestFile() {
    }

    private int fileID;
    private long length;
}
