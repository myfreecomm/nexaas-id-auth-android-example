package com.nexaas.android.nexaasidauth.api.services;

import com.nexaas.android.nexaasidauth.model.Contacts;
import com.nexaas.android.nexaasidauth.model.Emails;
import com.nexaas.android.nexaasidauth.model.PersonalInfo;
import com.nexaas.android.nexaasidauth.model.ProfessionalInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileService {

    @GET("api/v1/profile")
    Call<PersonalInfo> getPersonalInfo();

    @GET("api/v1/profile/professional_info")
    Call<ProfessionalInfo> getProfessionalInfo();

    @GET("api/v1/profile/contacts")
    Call<Contacts> getContacts();

    @GET("api/v1/profile/emails")
    Call<Emails> getEmails();
}