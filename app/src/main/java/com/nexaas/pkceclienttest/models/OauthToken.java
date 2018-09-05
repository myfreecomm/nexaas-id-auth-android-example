package com.nexaas.pkceclienttest.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by alexandre on 23/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */

@JsonObject
public class OauthToken {

//    @JsonField
    public String scope;
    @JsonField (name = "grant_type")
    public String grantType;
    @JsonField (name = "client_id")
    public String clientId;
    @JsonField (name = "client_secret")
    public String clientSecret;
    @JsonField (name = "code")
    public String code;
    @JsonField (name = "redirect_uri")
    public String redirectUri;
    @JsonField (name = "code_verifier")
    public String codeVerifier;

    public OauthToken (){
        grantType = "authorization_code";
        scope = "profile";
    }
}
