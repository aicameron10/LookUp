package com.bol.lookup.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.bol.lookup.activities.MainActivity;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "MyPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";


    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // User name (make variable public to access from outside)
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_AUTH = "auth";

    public static final String KEY_USER = "user";

    public static final String KEY_TOKEN = "token";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String name, String email, String auth, String userDetailsId, String AuthToken) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_PASSWORD, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // Storing email in pref
        editor.putString(KEY_AUTH, auth);

        editor.putString(KEY_USER, userDetailsId);

        editor.putString(KEY_TOKEN, AuthToken);


        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        user.put(KEY_AUTH, pref.getString(KEY_AUTH, null));

        user.put(KEY_USER, pref.getString(KEY_USER, null));

        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.apply();

        //((HomeActivity) _context).displayView(0);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}