package com.example.android.educationapp.ui.base;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
//import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

public class ToDoListApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
  //  ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
      String appkey = "ZfgLJ3yiPbFLS3Fo2cKpINAksb5RxS6Cw1xqArjj";
      String clientkey = "EOGi1mA8eJfZKr5nLZHIyzYM44a6TShZGPKYCKVV";
    Parse.initialize(this,appkey, clientkey);


    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
