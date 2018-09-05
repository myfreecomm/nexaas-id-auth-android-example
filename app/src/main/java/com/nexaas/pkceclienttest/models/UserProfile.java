package com.nexaas.pkceclienttest.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by alexandre on 24/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */

@JsonObject
public class UserProfile {

    @JsonField (name = "id")
    public String uuid;
    @JsonField (name = "full_name")
    public  String name;
    @JsonField (name = "nickname")
    public String nickName;
    @JsonField
    public String email;
    @JsonField
    public String birth;
    @JsonField
    public String gender;
    @JsonField
    public String language;
    @JsonField
    public String picture;
    @JsonField (name = "timezone")
    public String timeZone;
    @JsonField
    public String country;
    @JsonField
    public String city;
    @JsonField
    public String bio;

}
