package com.nexaas.android.nexaasidauth.api

import com.nexaas.android.nexaasidauth.callback.ResponseCallback
import com.nexaas.android.nexaasidauth.helper.Consts
import com.nexaas.android.nexaasidauth.helper.Environment
import com.nexaas.android.nexaasidauth.helper.Utils
import com.nexaas.android.nexaasidauth.model.Contacts
import com.nexaas.android.nexaasidauth.model.Emails
import com.nexaas.android.nexaasidauth.model.PersonalInfo
import com.nexaas.android.nexaasidauth.model.ProfessionalInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRequest {

    companion object {

        @JvmStatic
        fun getPersonalInfo(accessToken: String, environment: Environment, callback: ResponseCallback<PersonalInfo?>) {

            RetrofitConfig(accessToken, environment).profileService.personalInfo
                    .enqueue(object : Callback<PersonalInfo> {
                        override fun onResponse(call: Call<PersonalInfo>, response: Response<PersonalInfo>) {
                            if (response.isSuccessful)
                                callback.onSuccess(response.body())
                            else if (response.code() == 401)
                                callback.onFailure(Consts.UNAUTHORIZED)
                        }

                        override fun onFailure(call: Call<PersonalInfo>, t: Throwable) {
                            callback.onFailure(Utils.treatFailureMessage(t))
                        }
                    })
        }

        fun getProfessionalInfo(accessToken: String, environment: Environment, callback: ResponseCallback<ProfessionalInfo?>) {

            RetrofitConfig(accessToken, environment).profileService.professionalInfo
                    .enqueue(object : Callback<ProfessionalInfo> {
                        override fun onResponse(call: Call<ProfessionalInfo>, response: Response<ProfessionalInfo>) {
                            if (response.isSuccessful)
                                callback.onSuccess(response.body())
                            else if (response.code() == 401)
                                callback.onFailure(Consts.UNAUTHORIZED)
                        }

                        override fun onFailure(call: Call<ProfessionalInfo>, t: Throwable) {
                            callback.onFailure(Utils.treatFailureMessage(t))
                        }
                    })
        }

        fun getContacts(accessToken: String, environment: Environment, callback: ResponseCallback<Contacts?>) {

            RetrofitConfig(accessToken, environment).profileService.contacts
                    .enqueue(object : Callback<Contacts> {
                        override fun onResponse(call: Call<Contacts>, response: Response<Contacts>) {
                            if (response.isSuccessful)
                                callback.onSuccess(response.body())
                            else if (response.code() == 401)
                                callback.onFailure(Consts.UNAUTHORIZED)
                        }

                        override fun onFailure(call: Call<Contacts>, t: Throwable) {
                            callback.onFailure(Utils.treatFailureMessage(t))
                        }
                    })
        }

        fun getEmails(accessToken: String, environment: Environment, callback: ResponseCallback<Emails?>) {

            RetrofitConfig(accessToken, environment).profileService.emails
                    .enqueue(object : Callback<Emails> {
                        override fun onResponse(call: Call<Emails>, response: Response<Emails>) {
                            if (response.isSuccessful)
                                callback.onSuccess(response.body())
                            else if (response.code() == 401)
                                callback.onFailure(Consts.UNAUTHORIZED)
                        }

                        override fun onFailure(call: Call<Emails>, t: Throwable) {
                            callback.onFailure(Utils.treatFailureMessage(t))
                        }
                    })
        }
    }
}