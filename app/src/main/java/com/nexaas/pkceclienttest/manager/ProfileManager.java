package com.nexaas.pkceclienttest.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.nexaas.pkceclienttest.RetrofitInstance;
import com.nexaas.pkceclienttest.interfaces.PictureService;
import com.nexaas.pkceclienttest.interfaces.ProfileService;
import com.nexaas.pkceclienttest.interfaces.ResponseListener;
import com.nexaas.pkceclienttest.models.UserProfile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by alexandre on 24/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */

public class ProfileManager {

    public static void getProfile (String accessToken, final ResponseListener<UserProfile> listener) {
        ProfileService service = RetrofitInstance.getInstanceBearer(accessToken).create(ProfileService.class);

        Call<UserProfile> getProfile = service.getProfile();

        getProfile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful())
                    listener.onSuccess(response.body());

                else if (response.code() == 401)
                    listener.onFailure("Unauthorized");
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                listener.onFailure(RetrofitInstance.treatFailureMessage(t));
            }
        });
    }

    public static void getProfilePicture (String url, final ResponseListener<Bitmap> listener) {

        PictureService service = RetrofitInstance.getInstance().create(PictureService.class);

        Call<ResponseBody> call = service.getImage(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();

                    if (body != null && body.byteStream() != null) {
                        Bitmap bmp = BitmapFactory.decodeStream(body.byteStream());

                        listener.onSuccess(bmp);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
