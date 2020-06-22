package com.wbl.app.appifywp;

import android.app.Application;

import com.onesignal.OneSignal;

import java.util.HashMap;

public class MainApplication extends Application {
    private HashMap<String, String> menuKeyValue;

    public HashMap<String, String> getMenuKeyValue() {
        return menuKeyValue;
    }

    public void setMenuKeyValue(HashMap<String, String> menuKeyValue) {
        this.menuKeyValue = menuKeyValue;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
