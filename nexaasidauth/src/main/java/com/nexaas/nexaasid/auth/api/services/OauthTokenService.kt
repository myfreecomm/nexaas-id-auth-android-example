package com.nexaas.nexaasid.auth.api.services

import com.nexaas.nexaasid.auth.model.OAuthTokenRequest
import com.nexaas.nexaasid.auth.model.OAuthTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OauthTokenService {

    @POST("oauth/token")
    fun getOAuthTokenResponse(@Body oauthTokenRequest: OAuthTokenRequest): Call<OAuthTokenResponse>
}