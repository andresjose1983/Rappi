package com.rappi.test.main;

import com.rappi.test.model.RedditResponse;
import com.rappi.test.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mendez Fernandez on 30/12/2016.
 */

public class MainInteractor {

    private IMainPresenter mIMainPresenter;

    public MainInteractor(IMainPresenter mIMainPresenter) {
        this.mIMainPresenter = mIMainPresenter;
        this.getData();
    }

    public void getData(){
        Call<RedditResponse> redditResponseCall = RestClient.get();
        mIMainPresenter.showProgressBar();
        redditResponseCall.enqueue(new Callback<RedditResponse>() {
            @Override
            public void onResponse(Call<RedditResponse> call, Response<RedditResponse> response) {
                mIMainPresenter.hideProgressBar();
                mIMainPresenter.showData(response.body());
            }

            @Override
            public void onFailure(Call<RedditResponse> call, Throwable t) {
                mIMainPresenter.hideProgressBar();
                mIMainPresenter.showError(t.getMessage());
            }
        });
    }
}
