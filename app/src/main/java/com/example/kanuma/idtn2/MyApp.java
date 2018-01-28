package com.example.kanuma.idtn2;

import android.app.Application;

/**
 * Created by showgata12 on 23/1/18.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
    }
}
