package com.udacity.popularmovies.core;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.Objects;

public class NetworkUtils {

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return Objects.requireNonNull(cm).getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
