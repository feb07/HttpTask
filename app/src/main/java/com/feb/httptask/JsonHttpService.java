package com.feb.httptask;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author lilichun
 * createDate: 2019-11-12
 */
public class JsonHttpService implements IHttpService {
    private IHttpListener httpListener;
    private String url;
    private byte[] requestData;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    @Override
    public void execute() {
        executePost();
    }

    @Override
    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    private void executePost() {
        HttpURLConnection connection = null;
        URL url;
        try {
            url = new URL(this.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setReadTimeout(20 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            OutputStream outputStream = connection.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            if (requestData != null) {
                bufferedOutputStream.write(requestData);
            }
            bufferedOutputStream.flush();
            outputStream.close();
            bufferedOutputStream.close();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if (httpListener != null) {
                    httpListener.onSuccess(connection.getInputStream());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (httpListener != null) {
                httpListener.onFail();
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
