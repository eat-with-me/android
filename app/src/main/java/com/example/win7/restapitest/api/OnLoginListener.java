package com.example.win7.restapitest.api;

/**
 * Created by win7 on 22/04/2016.
 */
public interface OnLoginListener {

    void onSuccess();
    void onWrongCredentials();
    void onError();
}
