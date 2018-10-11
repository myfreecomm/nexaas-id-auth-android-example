package com.nexaas.android.nexaasidauth.api

import com.github.aurae.retrofit2.LoganSquareConverterFactory
import com.nexaas.android.nexaasidauth.api.services.OauthTokenService
import com.nexaas.android.nexaasidauth.api.services.ProfileService

import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class RetrofitConfig(bearerToken: String?) {

    private val retrofit: Retrofit

    val profileService: ProfileService
        get() = this.retrofit.create(ProfileService::class.java)

    val oAuthTokenService: OauthTokenService
        get() = this.retrofit.create(OauthTokenService::class.java)

    init {
        this.retrofit = Retrofit.Builder()
                .baseUrl("https://sandbox.id.nexaas.com/")
                .addConverterFactory(LoganSquareConverterFactory.create())
                .client(getClient(bearerToken))
                .build()
    }

    companion object {

        private const val CONNECT_TIMEOUT = 10000
        private const val READ_TIMEOUT = 15000

        private fun getClient(bearerToken: String?): OkHttpClient {
            val clientBuilder = OkHttpClient.Builder()

            clientBuilder
                    .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                    .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)

            if (bearerToken == null)
                clientBuilder.addInterceptor(interceptor)
            else
                clientBuilder.addInterceptor(getInterceptorBearer(bearerToken))

            return clientBuilder.build()
        }

        private val interceptor: Interceptor
            get() = Interceptor { chain ->
                val request = chain.request()

                request.newBuilder()
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .addHeader("accept", "application.json;charset=UTF-8")
                        .build()

                chain.proceed(request)
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

        fun treatFailureMessage(t: Throwable): String {
            return when (t is SocketTimeoutException) {
                true -> "O servidor excedeu o tempo limeite de conexão."
                false -> "O servidor encontrou um erro inesperado na requisição."
            }
        }
    }
}