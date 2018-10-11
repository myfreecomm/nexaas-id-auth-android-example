package com.nexaas.android.nexaasidauth.auth

import android.content.Context
import android.net.Uri
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.AuthorizationService
import android.content.Intent
import com.nexaas.android.nexaasidauth.R
import com.nexaas.android.nexaasidauth.helper.Environment
import com.nexaas.android.nexaasidauth.model.OAuthTokenRequest
import com.nexaas.android.nexaasidauth.helper.StringUtils

class AuthConfig {

    private fun getAuthRequest(clientId: String, redirectUri: String, environment: Environment): AuthorizationRequest {
        val requestBuilder = AuthorizationRequest.Builder(
                configureService(environment),
                clientId,
                ResponseTypeValues.CODE,
                Uri.parse(redirectUri))

        requestBuilder.setScope("profile")

        return requestBuilder.build()
    }

    private fun configureService(environment: Environment): AuthorizationServiceConfiguration {
        return AuthorizationServiceConfiguration(
                Uri.parse(getNexaasIdUrl(environment) + getNexaasIdOAuthPath()),
                Uri.parse(getNexaasIdUrl(environment) + getNexaasIdTokenPath()))
    }

    fun authorize(context: Context, clientId: String, redirectUri: String, environment: Environment): Intent {
        val authService = AuthorizationService(context)
        return authService.getAuthorizationRequestIntent(getAuthRequest(clientId, redirectUri, environment))
    }

    fun requestToken(clientId: String, clientSecret: String, redirectUri: String, authCode: String): OAuthTokenRequest {
        return OAuthTokenRequest(
                clientId,
                clientSecret,
                redirectUri,
                "authorization_code",
                authCode
        )
    }

    private fun getNexaasIdUrl(environment: Environment): String {
        return when (environment == Environment.SANDBOX) {
            true -> StringUtils.getString(R.string.nexaas_id_url_sandbox)
            false -> StringUtils.getString(R.string.nexaas_id_url_production)
        }
    }

    private fun getNexaasIdOAuthPath(): String {
        return StringUtils.getString(R.string.nexaas_id_oauth_path)
    }

    private fun getNexaasIdTokenPath(): String {
        return StringUtils.getString(R.string.nexaas_id_token_path)
    }
}