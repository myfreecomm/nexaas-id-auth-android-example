package com.nexaas.pkceclienttest.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by alexandre on 24/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */
@JsonObject
public class UserProfile {

    @JsonField(name = "id")
    public String uuid;
    @JsonField(name = "full_name")
    public String name;
    @JsonField
    public String nickname;
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
    @JsonField
    public String timezone;
    @JsonField
    public String country;
    @JsonField
    public String city;
    @JsonField
    public String bio;

    public UserProfile() {}

    public UserProfile(String uuid, String name, String nickName, String email, String birth,
                       String gender, String language, String picture, String timezone,
                       String country, String city, String bio) {

        this.uuid = uuid;
        this.name = name;
        this.nickname = nickName;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.language = language;
        this.picture = picture;
        this.timezone = timezone;
        this.country = country;
        this.city = city;
        this.bio = bio;
    }
}
