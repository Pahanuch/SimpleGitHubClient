package ua.pavlo.tikhonov.simplegithubclient.model.api.basic;

import android.util.Base64;

/**
 * Created by Tikho on 16.12.2016.
 */

public class AccessToken {

    private static String AUTH_64 = "empty";

    public static void setAccessToken(String username, String password){
        String credentials = username + ":" + password;
        AUTH_64 =
                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }

    public static void setAccessToken(String accessToken){
        AUTH_64 = accessToken;
    }

    public static String getAccessToken(){
        return AUTH_64;
    }

}
