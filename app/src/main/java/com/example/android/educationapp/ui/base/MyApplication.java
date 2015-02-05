package com.example.android.educationapp.ui.base;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by nirav kalola on 2/1/2015.
 */
public class MyApplication extends Application{
    static final String TAG = "MyApp";
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "ZfgLJ3yiPbFLS3Fo2cKpINAksb5RxS6Cw1xqArjj", "EOGi1mA8eJfZKr5nLZHIyzYM44a6TShZGPKYCKVV");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

       /* ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.e("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });*/
        // Specify an Activity to handle all pushes by default.
      //  PushService.setDefaultPushCallback(this, LoginActivity.class);
    }
}
