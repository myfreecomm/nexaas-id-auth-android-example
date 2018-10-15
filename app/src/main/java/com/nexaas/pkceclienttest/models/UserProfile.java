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
    private String uuid;
    @JsonField(name = "full_name")
    private String name;
    @JsonField
    private String nickname;
    @JsonField
    private String email;
    @JsonField
    private String birth;
    @JsonField
    private String gender;
    @JsonField
    private String language;
    @JsonField
    private String picture;
    @JsonField
    private String timezone;
    @JsonField
    private String country;
    @JsonField
    private String city;
    @JsonField
    private String bio;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}