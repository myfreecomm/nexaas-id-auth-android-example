package com.nexaas.pkceclienttest.interfaces;

/**
 * Created by alexandre on 23/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */

public interface ResponseListener<T> {
    void onSuccess (T item);
    void onFailure (String error);
}
