package com.nexaas.nexaasid.auth.api

import com.nexaas.nexaasid.auth.api.services.OauthTokenService
import com.nexaas.nexaasid.auth.api.services.ProfileService
import com.nexaas.nexaasid.auth.helper.Consts
import com.nexaas.nexaasid.auth.helper.Environment
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitConfig(bearerToken: String?, environment: Environment) {

    private val retrofit: Retrofit

    val profileService: ProfileService
        get() = this.retrofit.create(ProfileService::class.java)

    val oAuthTokenService: OauthTokenService
        get() = this.retrofit.create(OauthTokenService::class.java)

    init {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        this.retrofit = Retrofit.Builder()
                .baseUrl(getBaseUrl(environment))
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(getClient(bearerToken))
                .build()
    }

    companion object {

        private const val CONNECT_TIMEOUT = 10000
        private const val READ_TIMEOUT = 15000

        private fun getBaseUrl(environment: Environment) : String {
            return when(environment == Environment.SANDBOX) {
                true -> Consts.NEXAAS_ID_URL_SANDBOX
                false -> Consts.NEXAAS_ID_URL_PRODUCTION
            }
        }

        private fun getClient(bearerToken: String?): OkHttpClient {
            val clientBuilder = OkHttpClient.Builder()

            clientBuilder
                    .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                    .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)

            if (bearerToken != null)
                clientBuilder.addInterceptor(getInterceptorBearer(bearerToken))

            return clientBuilder.build()
        }

        private fun getInterceptorBearer(bearer: String): Interceptor {
            return Interceptor { chain ->
                var request = chain.request()

                request = request.newBuilder()
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .addHeader("accept", "application.json;charset=UTF-8")
                        .addHeader("Authorization", "Bearer $bearer")
                        .build()

                chain.proceed(request)
            }
        }
    }
}