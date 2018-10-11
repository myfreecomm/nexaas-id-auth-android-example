package com.nexaas.pkceclienttest.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by alexandre on 23/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */
@JsonObject
public class NexaasIdUser {
    @JsonField(name = "access_token")
    public String accessToken;
    @JsonField(name = "token_type")
    public String tokenType;
    @JsonField(name = "refresh_token")
    public String refreshToken;
    @JsonField(name = "scope")
    public String scope;
    @JsonField(name = "expires_in")
    public long expiresIn;
    @JsonField(name = "api_token")
    public String apiToken;

    public NexaasIdUser() {}

    public NexaasIdUser(String accessToken, String tokenType, String refreshToken, String scope,
                        long expiresIn, String apiToken) {

        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.expiresIn = expiresIn;
        this.apiToken = apiToken;
    }
}