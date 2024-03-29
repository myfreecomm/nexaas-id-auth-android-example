package com.nexaas.nexaasid.auth.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class OAuthTokenResponse(
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "token_type")
    val tokenType: String,
    @Json(name = "expires_in")
    val expiresIn: Long,
    @Json(name = "refresh_token")
    val refreshToken: String,
    val scope: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "api_token")
    val apiToken: String
)