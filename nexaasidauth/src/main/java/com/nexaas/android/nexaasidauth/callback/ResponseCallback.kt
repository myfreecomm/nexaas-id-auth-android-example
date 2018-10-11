package com.nexaas.android.nexaasidauth.callback

interface ResponseCallback<T> {
    fun onSuccess(item: T)
    fun onFailure(error: String)
}