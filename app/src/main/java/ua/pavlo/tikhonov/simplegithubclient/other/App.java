package ua.pavlo.tikhonov.simplegithubclient.other;

import android.app.Application;
import android.content.Context;

import ua.pavlo.tikhonov.simplegithubclient.other.di.AppComponent;
import ua.pavlo.tikhonov.simplegithubclient.other.di.DaggerAppComponent;

public class App extends Application {

    private static Context mContext;

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
        mContext = this;
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }

    public static Context getContext(){
        return mContext;
    }

}
