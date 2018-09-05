package com.nexaas.pkceclienttest.interfaces;

import com.nexaas.pkceclienttest.models.NexaasIdUser;
import com.nexaas.pkceclienttest.models.OauthToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by alexandre on 23/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */

public interface OauthTokenService {

    @POST ("/oauth/token")
    Call<NexaasIdUser> oauthToken (@Body OauthToken oauthToken);
}
