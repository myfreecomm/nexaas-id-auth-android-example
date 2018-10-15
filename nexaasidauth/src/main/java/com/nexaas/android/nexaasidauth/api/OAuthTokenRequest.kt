package com.nexaas.android.nexaasidauth.api

import com.nexaas.android.nexaasidauth.callback.ResponseCallback
import com.nexaas.android.nexaasidauth.helper.Consts
import com.nexaas.android.nexaasidauth.helper.Environment
import com.nexaas.android.nexaasidauth.helper.Utils
import com.nexaas.android.nexaasidauth.model.OAuthTokenRequest
import com.nexaas.android.nexaasidauth.model.OAuthTokenResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OAuthTokenRequest {

    companion object {

        @JvmStatic
        fun getOAuthTokenResponse(tokenRequest: OAuthTokenRequest, environment: Environment,
                                  callback: ResponseCallback<OAuthTokenResponse?>) {

            RetrofitConfig(null, environment).oAuthTokenService.getOAuthTokenResponse(tokenRequest)
                    .enqueue(object : Callback<OAuthTokenResponse> {
                        override fun onResponse(call: Call<OAuthTokenResponse>, response: Response<OAuthTokenResponse>) {
                            if (response.isSuccessful)
                                callback.onSuccess(response.body())
                            else if (response.code() == 401)
                                callback.onFailure(Consts.UNAUTHORIZED)
                        }

                        override fun onFailure(call: Call<OAuthTokenResponse>, t: Throwable) {
                            callback.onFailure(Utils.treatFailureMessage(t))
                        }
                    })
        }
    }
}