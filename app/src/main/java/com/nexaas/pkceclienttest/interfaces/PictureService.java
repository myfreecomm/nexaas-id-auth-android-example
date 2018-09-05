package com.nexaas.pkceclienttest.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by alexandre on 26/08/2018.
 * Copyright© Nexaas™. All rights reserved.
 */

public interface PictureService {

    @GET
    Call<ResponseBody> getImage (@Url String uri);
}
