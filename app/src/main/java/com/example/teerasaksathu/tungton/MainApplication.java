package com.example.teerasaksathu.tungton;

        import android.app.Application;

/**
 * Created by teerasaksathu on 19/3/2018 AD.
 */

        import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

public class MainApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
