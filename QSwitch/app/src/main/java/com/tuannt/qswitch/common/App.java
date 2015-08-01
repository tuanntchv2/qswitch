package com.tuannt.qswitch.common;

import android.app.Application;
import com.activeandroid.ActiveAndroid;

/**
 * Copyright Tuannt
 * Created by TuanNT on 8/1/2015.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
