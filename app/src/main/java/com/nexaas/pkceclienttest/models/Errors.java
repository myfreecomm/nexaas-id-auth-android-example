package com.nexaas.pkceclienttest.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

/**
 * Created by alexandre on 23/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */
@JsonObject
public class Errors implements Serializable {
    private final static long serialVersionUID = 8758054494170264545L;

    @JsonField
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
