package com.naomi.nasatoday.activity;

import android.app.Application;
import android.content.res.Configuration;

import com.google.firebase.FirebaseApp;

public class MyCustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
                FirebaseApp.initializeApp(this);

    }

}
