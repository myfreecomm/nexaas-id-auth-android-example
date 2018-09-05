package com.nexaas.pkceclienttest.manager;

import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;
import com.nexaas.pkceclienttest.RetrofitInstance;
import com.nexaas.pkceclienttest.interfaces.OauthTokenService;
import com.nexaas.pkceclienttest.interfaces.ResponseListener;
import com.nexaas.pkceclienttest.models.NexaasIdUser;
import com.nexaas.pkceclienttest.models.OauthToken;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandre on 23/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */

public class OauthManager {

    public static void getUserCredentials(OauthToken oauthToken, final ResponseListener<NexaasIdUser> listener) {
        OauthTokenService service = RetrofitInstance.getInstance().create(OauthTokenService.class);
        try {
            String body = LoganSquare.serialize(oauthToken);
            Log.v("Body request", body);
        } catch (IOException e) {
            Log.e("Logansquare exception", e.getLocalizedMessage());
        }
        Call<NexaasIdUser> getCredentials = service.oauthToken(oauthToken);

        getCredentials.enqueue(new Callback<NexaasIdUser>() {
            @Override
            public void onResponse(Call<NexaasIdUser> call, Response<NexaasIdUser> response) {
                Log.v("PW2","Status code: " + response.code());
                if (response.isSuccessful())
                    listener.onSuccess(response.body());

                else if (response.code() == 401)
                    listener.onFailure("Unauthorized");
                else
                    listener.onFailure(RetrofitInstance.getError(response.errorBody()));
            }

            @Override
            public void onFailure(Call<NexaasIdUser> call, Throwable t) {
                listener.onFailure(RetrofitInstance.treatFailureMessage(t));
            }
        });
    }
}
