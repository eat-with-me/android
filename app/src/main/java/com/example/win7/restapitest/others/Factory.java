package com.example.win7.restapitest.others;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.ApiConnectionImp;

/**
 * Created by win7 on 01/04/2016.
 */
public class Factory {

    private static ApiConnection apiConnection;

    public static ApiConnection getApiConnection(){
        if (apiConnection == null){
            apiConnection = new ApiConnectionImp();
        }
        return apiConnection;

    }
}
