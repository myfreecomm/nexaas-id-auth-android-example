package com.nexaas.nexaasid.auth.helper

import java.net.SocketTimeoutException

object Utils {

    fun treatFailureMessage(t: Throwable): String {
        return when (t is SocketTimeoutException) {
            true -> Consts.TIMEOUT_EXCEPTION
            false -> Consts.UNEXPECTED_ERROR
        }
    }
}