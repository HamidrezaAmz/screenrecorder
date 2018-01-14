package com.orpheusdroid.screenrecorder;

import android.app.Application;

import com.orpheusdroid.screenrecorder.Utils.PublicFunction;

/**
 * Created by Reza Amozadeh on 1/7/2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PublicFunction.setTypeface(getApplicationContext());
    }
}
