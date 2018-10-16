package com.nexaas.nexaasid.auth.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Emails(
    val id: String,
    val emails: List<String>
)