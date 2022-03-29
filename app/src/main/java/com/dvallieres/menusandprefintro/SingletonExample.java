package com.dvallieres.menusandprefintro;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.security.Identity;
import java.util.ArrayList;

public class SingletonExample{

    String editValue;
    private static Context context;
    private static final SingletonExample ourInstance = new SingletonExample();

    ArrayList<SpecialObject> specObjects;
    static SharedPreferences prefs;

    public static SingletonExample getInstance(Context contextIn) {
        context = contextIn;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return ourInstance;
    }

    private SingletonExample() {
    }

    public SharedPreferences getPreferences(){
        return prefs;
    }

    public void setText(String editValue) {
        this.editValue = editValue;
    }
    public String getText() {
        return editValue;
    }

    public ArrayList<SpecialObject> getSpecialObjects(){
        return specObjects;
    }
}