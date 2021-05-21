package data;

import io.socket.client.Ack;
import io.socket.client.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import javax.swing.JTable;
import org.json.JSONException;
import org.json.JSONObject;
import swing.PanelStatus;

public class DataReader {

    public PanelStatus getStatus() {
        return status;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public RandomAccessFile getAccFile() {
        return accFile;
    }

    public void setAccFile(RandomAccessFile accFile) {
        this.accFile = accFile;
    }

    public DataReader(File file, JTable table) throws IOException {
        //  r is mode file read only
        accFile = new RandomAccessFile(file, "r");
        this.file = file;
        this.fileSize = accFile.length();
        this.fileName = file.getName();
        this.status = new PanelStatus();
        this.status.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!status.isPause() && pause) {
                    pause = false;
                    //  Request continue file length from server by file id
                    client.emit("r_f_l", fileID, new Ack() {
                        @Override
                        public void call(Object... os) {
                            if (os.length > 0) {
                                long length = Long.valueOf(os[0].toString());
                                try {
                                    accFile.seek(length);
                                    sendingFile(client);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    //  Send file

                }
            }
        });
        this.table = table;
    }

    private int fileID;
    private File file;
    private long fileSize;
    private String fileName;
    private RandomAccessFile accFile;
    private PanelStatus status;
    private JTable table;
    private Socket client;

    public synchronized byte[] readFile() throws IOException {
        long filePointer = accFile.getFilePointer();
        if (filePointer != fileSize) {
            int max = 2000;
            //  2000 is max send file per package
            //  we spite it to send large file
            long length = filePointer + max >= fileSize ? fileSize - filePointer : max;
            byte[] data = new byte[(int) length];
            accFile.read(data);
            return data;
        } else {
            return null;
        }
    }

    public void close() throws IOException {
        accFile.close();
    }

    public String getFileSizeConverted() {
        double bytes = fileSize;
        String[] fileSizeUnits = {"bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        String sizeToReturn;
        DecimalFormat df = new DecimalFormat("0.#");
        int index;
        for (index = 0; index < fileSizeUnits.length; index++) {
            if (bytes < 1024) {
                break;
            }
            bytes = bytes / 1024;
        }
        sizeToReturn = df.format(bytes) + " " + fileSizeUnits[index];
        return sizeToReturn;
    }

    public double getPercentage() throws IOException {
        double percentage;
        long filePointer = accFile.getFilePointer();
        percentage = filePointer * 100 / fileSize;
        return percentage;
    }

    public Object[] toRowTable(int no) {
        return new Object[]{this, no, fileName, getFileSizeConverted(), "Next update"};
    }

    public void startSend(Socket socket) throws JSONException {
        this.client = socket;
        JSONObject data = new JSONObject();
        data.put("fileName", fileName);
        data.put("fileSize", fileSize);
        socket.emit("send_file", data, new Ack() {
            @Override
            public void call(Object... os) {
                //   this call back function
                //   Index 0 Boolean, Index 1 FileID
                if (os.length > 0) {
                    boolean action = (boolean) os[0];
                    if (action) {
                        //  fileID generate by server then return back with this function
                        fileID = (int) os[1];
                        //  starting send file
                        try {
                            sendingFile(socket);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private boolean pause = false;

    private void sendingFile(Socket socket) throws IOException, JSONException {
        JSONObject data = new JSONObject();
        data.put("fileID", fileID);
        byte[] bytes = readFile();
        if (bytes != null) {
            data.put("data", bytes);
            data.put("finish", false);
        } else {
            data.put("finish", true);
            close();    //  to close file
            status.done();
        }
        socket.emit("sending", data, new Ack() {
            @Override
            public void call(Object... os) {
                //  Call back function to sending more file
                //  This function meaning the server has receive file we has sending
                //  So we need send more file until we finish
                //  We response Boolean true or false
                if (os.length > 0) {
                    boolean act = (boolean) os[0];
                    if (act) {
                        try {
                            //  This function will recursive until act = false
                            if (!status.isPause()) {
                                showStatus((int) getPercentage());
                                sendingFile(socket);
                            } else {
                                pause = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void showStatus(int values) {
        status.showStatus(values);
        table.repaint();
    }
}
