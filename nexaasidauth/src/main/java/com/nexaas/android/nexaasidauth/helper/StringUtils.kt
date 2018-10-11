package com.nexaas.android.nexaasidauth.helper

import com.nexaas.android.nexaasidauth.NexxasIdAuthApp

object StringUtils {

    fun getString(resId: Int) : String = NexxasIdAuthApp.appContext.getString(resId)
}