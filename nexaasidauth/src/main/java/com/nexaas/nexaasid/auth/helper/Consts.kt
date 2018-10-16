package com.nexaas.nexaasid.auth.helper

object Consts {

    const val NEXAAS_ID_URL_PRODUCTION = "https://id.nexaas.com/"
    const val NEXAAS_ID_URL_SANDBOX = "https://sandbox.id.nexaas.com/"
    const val NEXAAS_ID_OAUTH_PATH = "oauth/authorize"
    const val NEXAAS_ID_TOKEN_PATH = "oauth/token"

    const val UNAUTHORIZED = "Unauthorized"
    const val TIMEOUT_EXCEPTION = "O servidor excedeu o tempo limite de conexão."
    const val UNEXPECTED_ERROR = "O servidor encontrou um erro inesperado na requisição."
}