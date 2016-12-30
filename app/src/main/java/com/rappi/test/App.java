package com.rappi.test;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Mendez Fernandez on 29/12/2016.
 */

public class App extends Application {

    private static App mInstance;


    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        mInstance = this;
    }

    public static boolean checkInternetConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager) mInstance.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = connectivityManager.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        return i.isAvailable();
    }

}