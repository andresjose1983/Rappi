package com.rappi.test.service;

import com.rappi.test.App;
import com.rappi.test.BuildConfig;
import com.rappi.test.model.RedditResponse;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mendez Fernandez on 29/12/2016.
 */

public final class RestClient {

    private static RedditService mRedditService;

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(new Cache(App.getInstance().getCacheDir(), 10 * 1024 * 1024)) // 10 MB
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (App.checkInternetConnection()) {
                        request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                    } else {
                        request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                    }
                    return chain.proceed(request);
                });

        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        mRedditService = retrofit.create(RedditService.class);
    }

    /**
     * GET JSON
     * @return
     */
    public static Call<RedditResponse> get(){
        return mRedditService.get();
    }

}
