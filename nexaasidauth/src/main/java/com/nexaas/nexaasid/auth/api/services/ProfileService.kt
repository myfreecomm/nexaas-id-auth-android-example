package com.nexaas.nexaasid.auth.api.services

import com.nexaas.nexaasid.auth.model.Contacts
import com.nexaas.nexaasid.auth.model.Emails
import com.nexaas.nexaasid.auth.model.PersonalInfo
import com.nexaas.nexaasid.auth.model.ProfessionalInfo
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