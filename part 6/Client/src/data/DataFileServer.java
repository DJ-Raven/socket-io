/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RAVEN
 */
public class DataFileServer {

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public File getOutPutPath() {
        return outPutPath;
    }

    public void setOutPutPath(File outPutPath) {
        this.outPutPath = outPutPath;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DataFileServer(int fileID, String fileName, String fileSize, File outPutPath, boolean status) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.outPutPath = outPutPath;
        this.status = status;
    }

    public DataFileServer(JSONObject json) throws JSONException {
        fileID = json.getInt("fileID");
        fileName = json.getString("fileName");
        fileSize = json.getString("fileSize");
    }

    private int fileID;
    private String fileName;
    private String fileSize;
    private File outPutPath;
    private boolean status;

    public Object[] toTableRow(int row) {
        return new Object[]{this, row, fileName, fileSize, "Next Update"};
    }
}
