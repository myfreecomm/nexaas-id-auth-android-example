package com.nexaas.pkceclienttest.interfaces;

import com.nexaas.pkceclienttest.models.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by alexandre on 24/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */

public interface ProfileService {

    @GET ("api/v1/profile")
    Call<UserProfile> getProfile ();

}
