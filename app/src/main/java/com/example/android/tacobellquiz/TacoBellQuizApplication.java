package com.example.android.tacobellquiz;

import android.app.Application;
import android.content.Context;

/**
 *  This class was added so that the resources can be made accessible from the quiz answer object
 *  to provide a static constant that will allow access to the string resources
 */
public class TacoBellQuizApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}