package com.nexaas.android.nexaasidauth.helper

import com.nexaas.android.nexaasidauth.NexxasIdAuthApp
import com.nexaas.android.nexaasidauth.R
import java.net.SocketTimeoutException

object Utils {

    fun getString(resId: Int) : String = NexxasIdAuthApp.appContext.getString(resId)

    fun treatFailureMessage(t: Throwable): String {
        return when (t is SocketTimeoutException) {
            true -> getString(R.string.timeout_error)
            false -> getString(R.string.unexpected_error)
        }
    }
}