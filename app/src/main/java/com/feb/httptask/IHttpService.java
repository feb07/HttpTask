package com.feb.httptask;

/**
 * @author lilichun
 * createDate: 2019-11-12
 */
public interface IHttpService {
    void setUrl(String url);

    void setRequestData(byte[] requestData);

    void execute();

    void setHttpListener(IHttpListener httpListener);
}
