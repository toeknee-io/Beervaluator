package com.loosefang.beervaluator;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by Tony on 1/4/2015.
 */
public class InitializeApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "orVUJCC3iDTch8rK9IIsir0VrayMg2BKZp4FFoEF", "4R7lMpdTYMefk7Aq6FvmI2ipB3awcQvcIV4G7ymB");

        ParseUser.enableAutomaticUser();
        ParseUser.getCurrentUser().saveInBackground();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

    }
}
