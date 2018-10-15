package com.nexaas.android.nexaasidauth.auth

import android.content.Context
import android.net.Uri
import android.content.Intent
import com.nexaas.android.nexaasidauth.helper.Consts
import com.nexaas.android.nexaasidauth.helper.Environment
import com.nexaas.android.nexaasidauth.model.OAuthTokenRequest
import net.openid.appauth.*

class AuthConfig {

    companion object {

        @JvmStatic
        fun getAuthorizationCode(data: Intent) : String? {
            return AuthorizationResponse.fromIntent(data)?.authorizationCode
        }

        @JvmStatic
        fun getCodeVerifier(data: Intent): String? {
            return AuthorizationResponse.fromIntent(data)?.request!!.codeVerifier
        }

        @JvmStatic
        fun requestToken(clientId: String, clientSecret: String, redirectUri: String, authCode: String, codeVerifier: String): OAuthTokenRequest {
            return OAuthTokenRequest(
                    clientId,
                    clientSecret,
                    redirectUri,
                    "authorization_code",
                    authCode,
                    codeVerifier
            )
        }

        @JvmStatic
        fun authorize(context: Context, clientId: String, scheme: String, redirectUri: String, environment: Environment): Intent {
            val authService = AuthorizationService(context)
            return authService.getAuthorizationRequestIntent(getAuthRequest(clientId, scheme, redirectUri, environment))
        }

        private fun getAuthRequest(clientId: String, scheme: String, redirectUri: String, environment: Environment): AuthorizationRequest {
            val requestBuilder = AuthorizationRequest.Builder(
                    configureService(environment, redirectUri),
                    clientId,
                    ResponseTypeValues.CODE,
                    Uri.parse(scheme + redirectUri))

            requestBuilder.setScope("profile")

            return requestBuilder.build()
        }

        private fun configureService(environment: Environment, redirectUri: String): AuthorizationServiceConfiguration {
            return AuthorizationServiceConfiguration(
                    Uri.parse(getNexaasIdUrl(environment) + getNexaasIdOAuthPath()),
                    Uri.parse(getNexaasIdUrl(environment) + getNexaasIdTokenPath()),
                    Uri.parse(redirectUri))
        }

        private fun getNexaasIdUrl(environment: Environment): String {
            return when (environment == Environment.SANDBOX) {
                true -> Consts.NEXAAS_ID_URL_SANDBOX
                false -> Consts.NEXAAS_ID_URL_PRODUCTION
            }
        }

        private fun getNexaasIdOAuthPath(): String {
            return Consts.NEXAAS_ID_OAUTH_PATH
        }

        private fun getNexaasIdTokenPath(): String {
            return Consts.NEXAAS_ID_TOKEN_PATH
        }
    }
}