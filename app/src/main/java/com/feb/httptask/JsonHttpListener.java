package com.feb.httptask;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @author lilichun
 * createDate: 2019-11-12
 */
public class JsonHttpListener<T> implements IHttpListener {
    private Class<T> tClass;
    private IDataListener<T> dataListener;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonHttpListener(Class<T> tClass, IDataListener<T> dataListener) {
        this.tClass = tClass;
        this.dataListener = dataListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        final T response = new Gson().fromJson(getContent(inputStream), tClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (dataListener != null) {
                    dataListener.onBizRequestSuccess(response);
                }
            }
        });
    }

    @Override
    public void onFail() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (dataListener != null) {
                    dataListener.onBizRequestFail();
                }
            }
        });
    }

    private String getContent(InputStream inputStream) {
        String content;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        content = stringBuilder.toString();
        return content;


    }
}
