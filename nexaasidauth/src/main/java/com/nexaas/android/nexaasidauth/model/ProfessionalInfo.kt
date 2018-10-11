package com.nexaas.android.nexaasidauth.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ProfessionalInfo(
    val id: String,
    val profession: String,
    val company: String,
    val position: String
)