package com.rappi.test.service;

import com.rappi.test.model.RedditResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mendez Fernandez on 29/12/2016.
 */

public interface RedditService {

    @GET("/reddits.json")
    Call<RedditResponse> get();
}
