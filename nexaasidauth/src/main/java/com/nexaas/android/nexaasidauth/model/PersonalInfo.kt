package com.nexaas.android.nexaasidauth.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class PersonalInfo(
    val id: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    val nickname: String,
    val email: String,
    val birth: String,
    val gender: String,
    val language: String,
    val picture: String,
    val timezone: String,
    val country: String,
    val city: String,
    val bio: String
)