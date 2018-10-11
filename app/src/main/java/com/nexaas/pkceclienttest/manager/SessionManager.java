package com.nexaas.pkceclienttest.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;
import com.nexaas.pkceclienttest.models.NexaasIdUser;
import com.nexaas.pkceclienttest.models.UserProfile;

import java.io.IOException;

/**
 * Created by alexandre on 24/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */
public class SessionManager {

    private static final String NEXAAS_ACCOUNT_ACCESS = "nexaas_access";
    private static final String USER_PROFILE = "user_profile";
    private static final String NEXAAS_ID_PREFS = "nexaas_id_prefs";

    public static void storeUserTokens(Context context, NexaasIdUser nexaasId) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(NEXAAS_ID_PREFS, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();

            String parse = LoganSquare.serialize(nexaasId);

            editor.putString(NEXAAS_ACCOUNT_ACCESS, parse).apply();
        } catch (IOException e) {
            Log.e("Parse preferences error", e.getLocalizedMessage());
        }
    }

    public static NexaasIdUser getNexaasAccessAuthorization(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(NEXAAS_ID_PREFS, Context.MODE_PRIVATE);

        String nexaasIdJson = prefs.getString(NEXAAS_ACCOUNT_ACCESS, null);

        if (nexaasIdJson != null)
            try {
                return LoganSquare.parse(nexaasIdJson, NexaasIdUser.class);
            } catch (IOException e) {
                return null;
            }

        return null;
    }

    public static void storeUserProfile(Context context, UserProfile profile) {
        if (profile != null) {
            try {
                SharedPreferences prefs = context.getSharedPreferences(NEXAAS_ID_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                String parse = LoganSquare.serialize(profile);

                if (parse != null)
                    editor.putString(USER_PROFILE, parse).apply();
            } catch (IOException e) {
                Log.e("Parse profile error", e.getLocalizedMessage());
            }
        }
    }

    public static UserProfile getUserProfile(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(NEXAAS_ID_PREFS, Context.MODE_PRIVATE);

        try {
            String profileJson = prefs.getString(USER_PROFILE, null);

            if (profileJson != null)
                return LoganSquare.parse(profileJson, UserProfile.class);
        } catch (IOException e) {
            return null;
        }

        return null;
    }
}