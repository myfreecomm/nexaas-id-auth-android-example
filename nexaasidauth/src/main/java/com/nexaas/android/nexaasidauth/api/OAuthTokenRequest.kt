package com.nexaas.android.nexaasidauth.api

import com.nexaas.android.nexaasidauth.callback.ResponseCallback
import com.nexaas.android.nexaasidauth.model.OAuthTokenRequest
import com.nexaas.android.nexaasidauth.model.OAuthTokenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OAuthTokenRequest {

    fun getOAuthTokenResponse(tokenRequest: OAuthTokenRequest, callback: ResponseCallback<OAuthTokenResponse?>) {

        RetrofitConfig(null).oAuthTokenService.getOAuthTokenResponse(tokenRequest)
                .enqueue(object : Callback<OAuthTokenResponse> {
                    override fun onResponse(call: Call<OAuthTokenResponse>, response: Response<OAuthTokenResponse>) {
                        if (response.isSuccessful)
                            callback.onSuccess(response.body())
                        else if (response.code() == 401)
                            callback.onFailure("Unauthorized")
                    }

                    override fun onFailure(call: Call<OAuthTokenResponse>, t: Throwable) {
                        callback.onFailure(RetrofitConfig.treatFailureMessage(t))
                    }
                })
    }
}