package com.nexaas.pkceclienttest.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

/**
 * Created by alexandre on 23/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */
@JsonObject
public class ErrorModel implements Serializable {

    private final static long serialVersionUID = -277564105068260841L;

    @JsonField
    private Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }
}
