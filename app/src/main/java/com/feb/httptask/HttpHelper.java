package com.feb.httptask;

/**
 * @author lilichun
 * createDate: 2019-11-12
 */
public class HttpHelper {

    public static <R, S> void setHttpRequest(String url, R requestData, Class<S> response, IDataListener<S> dataListener) {
        IHttpListener httpListener = new JsonHttpListener<>(response, dataListener);
        IHttpService httpService = new JsonHttpService();
        HttpTask httpTask = new HttpTask(requestData, url, httpService, httpListener);
        ThreadPoolManager.getInstance().execute(httpTask);
    }
}
