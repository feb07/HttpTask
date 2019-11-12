package com.feb.httptask;

/**
 * @author lilichun
 * createDate: 2019-11-12
 */
public interface IDataListener<T> {

    void onBizRequestSuccess(T requestInfo);

    void onBizRequestFail();
}
