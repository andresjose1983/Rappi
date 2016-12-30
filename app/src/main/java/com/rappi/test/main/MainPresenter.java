package com.rappi.test.main;

import com.rappi.test.model.RedditResponse;

/**
 * Created by Mendez Fernandez on 30/12/2016.
 */

public class MainPresenter implements IMainPresenter {

    private IMainView mIMainView;

    public MainPresenter(IMainView mIMainView) {
        this.mIMainView = mIMainView;
        new MainInteractor(this);
    }

    @Override
    public void showProgressBar() {
        mIMainView.showProgressBar();
    }

    @Override
    public void showError(String error) {
        mIMainView.showError(error);
    }

    @Override
    public void hideProgressBar() {
        mIMainView.hideProgressBar();
    }

    @Override
    public void showData(RedditResponse redditResponse) {
        mIMainView.showData(redditResponse);
    }
}
