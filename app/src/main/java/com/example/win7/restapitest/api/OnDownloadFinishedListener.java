package com.example.win7.restapitest.api;

import java.util.List;

/**
 * Created by win7 on 03/04/2016.
 */
public interface OnDownloadFinishedListener <E> {

    void onSuccess(E arg);
    void onError();

}
