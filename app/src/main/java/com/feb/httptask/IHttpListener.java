package com.feb.httptask;

import java.io.InputStream;

/**
 * @author lilichun
 * createDate: 2019-11-12
 */
public interface IHttpListener {
    void onSuccess(InputStream inputStream);

    void onFail();
}
