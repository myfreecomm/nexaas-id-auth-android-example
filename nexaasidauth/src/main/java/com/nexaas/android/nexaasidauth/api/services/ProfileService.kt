package com.nexaas.android.nexaasidauth.api.services

import com.nexaas.android.nexaasidauth.model.Contacts
import com.nexaas.android.nexaasidauth.model.Emails
import com.nexaas.android.nexaasidauth.model.PersonalInfo
import com.nexaas.android.nexaasidauth.model.ProfessionalInfo

import retrofit2.Call
import retrofit2.http.GET

interface ProfileService {

    @get:GET("api/v1/profile")
    val personalInfo: Call<PersonalInfo>

    @get:GET("api/v1/profile/professional_info")
    val professionalInfo: Call<ProfessionalInfo>

    @get:GET("api/v1/profile/contacts")
    val contacts: Call<Contacts>

    @get:GET("api/v1/profile/emails")
    val emails: Call<Emails>
}