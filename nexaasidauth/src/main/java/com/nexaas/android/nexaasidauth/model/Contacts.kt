package com.nexaas.android.nexaasidauth.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Contacts(
    val id: String,
    @Json(name = "phone_numbers")
    val phoneNumbers: List<String>
)