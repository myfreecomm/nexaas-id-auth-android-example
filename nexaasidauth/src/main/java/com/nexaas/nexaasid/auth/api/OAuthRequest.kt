package com.nexaas.nexaasid.auth.api

import com.nexaas.nexaasid.auth.callback.ResponseCallback
import com.nexaas.nexaasid.auth.helper.Consts
import com.nexaas.nexaasid.auth.helper.Environment
import com.nexaas.nexaasid.auth.helper.Utils
import com.nexaas.nexaasid.auth.model.OAuthTokenRequest
import com.nexaas.nexaasid.auth.model.OAuthTokenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OAuthRequest {

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

        @JvmStatic
        fun signOut(accessToken: String, environment: Environment) {
            RetrofitConfig(null, environment).oAuthTokenService.signOut(accessToken)
                    .enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {}

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Utils.treatFailureMessage(t)
                        }
                    })
        }
    }
}