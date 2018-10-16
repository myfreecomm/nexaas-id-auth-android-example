package com.nexaas.nexaasid.auth.callback

interface ResponseCallback<T> {
    fun onSuccess(item: T)
    fun onFailure(error: String)
}