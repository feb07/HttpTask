package com.feb.httptask;


import com.google.gson.Gson;

import java.nio.charset.Charset;

/**
 * @author lilichun
 * createDate: 2019-11-12
 */
public class HttpTask implements Runnable {

    private IHttpListener httpListener;

    private IHttpService httpService;

    public <T> HttpTask(T requestData, String url, IHttpService httpService, IHttpListener httpListener) {
        this.httpListener = httpListener;
        this.httpService = httpService;
        httpService.setHttpListener(httpListener);
        httpService.setUrl(url);
        if (requestData != null) {
            httpService.setRequestData(new Gson().toJson(requestData).getBytes( Charset.forName("UTF-8")));
        }
    }

    @Override
    public void run() {
        httpService.execute();
    }
}
