package com.nexaas.nexaasid.auth.api.services

import com.nexaas.nexaasid.auth.model.OAuthTokenRequest
import com.nexaas.nexaasid.auth.model.OAuthTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query

interface OauthTokenService {

    @POST("oauth/token")
    fun getOAuthTokenResponse(@Body oauthTokenRequest: OAuthTokenRequest): Call<OAuthTokenResponse>

    @GET("applications/sign_out")
    fun signOut(@Query("access_token") accessToken: String): Call<Void>
}