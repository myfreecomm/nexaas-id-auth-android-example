package com.nexaas.nexaasid.auth.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class OAuthTokenRequest(
    @Json(name = "client_id")
    val clientId: String,
    @Json(name = "client_secret")
    val clientSecret: String,
    @Json(name = "redirect_uri")
    val redirectUri: String,
    @Json(name = "grant_type")
    val grantType: String,
    val code: String,
    @Json(name = "code_verifier")
    val codeVerifier: String
)