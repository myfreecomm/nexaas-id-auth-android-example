package com.nexaas.nexaasid.auth.sample.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by alexandre on 23/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */
@JsonObject
public class NexaasIdUser {

    @JsonField(name = "access_token")
    private String accessToken;
    @JsonField(name = "token_type")
    private String tokenType;
    @JsonField(name = "refresh_token")
    private String refreshToken;
    @JsonField(name = "scope")
    private String scope;
    @JsonField(name = "expires_in")
    private long expiresIn;
    @JsonField(name = "api_token")
    private String apiToken;

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}