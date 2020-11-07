package com.example.jwongp.integrador2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
//TODO: COMPLETAR TOOLS
public class InternetConnection {
    public static boolean checkConnection(@NonNull Context context) {
        return ((ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
