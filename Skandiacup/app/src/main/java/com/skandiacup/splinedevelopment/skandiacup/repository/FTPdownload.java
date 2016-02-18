package com.skandiacup.splinedevelopment.skandiacup.repository;

import android.os.AsyncTask;

import com.skandiacup.splinedevelopment.skandiacup.utils.ExternalConfig;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class FTPdownload extends AsyncTask<String, Void, Object> {
    // this should come from config ?
    String server = "ftp.skandiacup.no";
    int portNumber = 21;
    String user = ExternalConfig.user;
    String password = ExternalConfig.password;
    String filename = "appConfig.json";

    private FTPcallback callback;

    public FTPdownload (FTPcallback callback, String filename){
        this.callback = callback;
        this.filename = filename;
    }

    protected Object doInBackground(String... params) {
        FTPClient ftp = null;
        boolean success = false;
        try {
            ftp = new FTPClient();
            ftp.connect(server, portNumber);
            ftp.login(user, password);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            ByteArrayOutputStream outputStream = null;
            try {
                outputStream = new ByteArrayOutputStream();
                success = ftp.retrieveFile(filename, outputStream);
                return success ? outputStream.toByteArray() : null;
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ftp != null) {
                try {
                    ftp.logout();
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        if (object != null) {
            this.callback.successCallback(object);
        } else {
            System.out.println("object in onPostExecute (FTPdownload) is null");
            this.callback.errorCallback();
        }
    }

    @Override
    protected void onCancelled() {
        this.callback.errorCallback();
    }
}
