package com.example.propertyjember;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    private static SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Pakti";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Make variable public to access from outside class
    public static final String KEY_kd_pengguna = "kd_pengguna";
    public static final String KEY_NIK_pengguna = "NIK_pengguna";
    public static final String KEY_nama_pengguna = "nama_pengguna";
    public static final String KEY_alamat_pengguna = "alamat_pengguna";
    public static final String KEY_no_pengguna = "no_pengguna";
    public static final String KEY_email = "email";
    public static final String KEY_foto_ktp = "foto_ktp";
    public static final String KEY_foto_diri_dan_ktp_ = "foto_diri_dan_ktp";
    public static final String KEY_foto_profil = "foto_profil";
    public static final String KEY_username = "username";
    public static final String KEY_password = "password";
    public static final String KEY_role = "role";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createSession(String NIK_pengguna, String nama_pengguna,
                              String alamat_pengguna, String no_pengguna, String email,
                              String foto_ktp, String foto_diri_dan_ktp, String foto_profil,
                              String username, String password, String role){
//        public void createSession(String kd_pengguna, String NIK_pengguna, String nama_pengguna,
//                String alamat_pengguna, String no_pengguna, String email,
//                String foto_ktp, String foto_diri_dan_ktp, String foto_profil,
//                String username, String password, String role){

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing kd_pengguna in pref
        editor.putString(KEY_nama_pengguna, nama_pengguna);

        // Storing data profil pengguna in pref
        editor.putString(KEY_NIK_pengguna, NIK_pengguna);
        editor.putString(KEY_alamat_pengguna, alamat_pengguna);
        editor.putString(KEY_no_pengguna, no_pengguna);
        editor.putString(KEY_email, email);
        editor.putString(KEY_foto_ktp, foto_ktp);
        editor.putString(KEY_foto_diri_dan_ktp_, foto_diri_dan_ktp);
        editor.putString(KEY_foto_profil, foto_profil);
        editor.putString(KEY_username, username);
        editor.putString(KEY_password, password);
        editor.putString(KEY_role, role);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);
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
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // index pasien
        user.put(KEY_kd_pengguna, pref.getString(KEY_kd_pengguna, null));

        // data profil pengguna
        user.put(KEY_NIK_pengguna, pref.getString(KEY_NIK_pengguna, null));
        user.put(KEY_nama_pengguna, pref.getString(KEY_nama_pengguna, null));
        user.put(KEY_alamat_pengguna, pref.getString(KEY_alamat_pengguna, null));
        user.put(KEY_no_pengguna, pref.getString(KEY_no_pengguna, null));
        user.put(KEY_email, pref.getString(KEY_email, null));
        user.put(KEY_foto_ktp, pref.getString(KEY_foto_ktp, null));
        user.put(KEY_foto_diri_dan_ktp_, pref.getString(KEY_foto_diri_dan_ktp_, null));
        user.put(KEY_foto_profil, pref.getString(KEY_foto_profil, null));
        user.put(KEY_username, pref.getString(KEY_username, null));
        user.put(KEY_password, pref.getString(KEY_password, null));
        user.put(KEY_role, pref.getString(KEY_role, null));


        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, Login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
