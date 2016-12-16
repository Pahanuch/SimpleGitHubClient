package ua.pavlo.tikhonov.simplegithubclient.model.api.basic;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import ua.pavlo.tikhonov.simplegithubclient.other.Const;

/**
 * Created by Tikho on 16.12.2016.
 */

public class Auth {

    private static SharedPreferences sPref;

    public static boolean isAuth(Activity context) {
        sPref = context.getPreferences(Context.MODE_PRIVATE);
        String savedToken = sPref.getString(Const.SAVED_TOKEN, "");
        AccessToken.setAccessToken(savedToken);
        if (savedToken.isEmpty()) return false;
        return true;
    }

    public static void clearToken(Activity context) {
        sPref = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(Const.SAVED_TOKEN, null);
        ed.commit();
    }

    public static void saveToken(Activity context, String token) {
        sPref = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(Const.SAVED_TOKEN, token);
        ed.commit();
    }
}
