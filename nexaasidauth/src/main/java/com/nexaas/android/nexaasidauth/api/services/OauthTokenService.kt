package com.nexaas.android.nexaasidauth.api.services

import com.nexaas.android.nexaasidauth.model.OAuthTokenRequest
import com.nexaas.android.nexaasidauth.model.OAuthTokenResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OauthTokenService {

    @POST("oauth/token")
    fun getOAuthTokenResponse(@Body oauthTokenRequest: OAuthTokenRequest): Call<OAuthTokenResponse>
}