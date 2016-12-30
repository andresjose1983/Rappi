package com.rappi.test.main;

import com.rappi.test.model.RedditResponse;

/**
 * Created by Mendez Fernandez on 30/12/2016.
 */

public interface IMainView {

    void showProgressBar();

    void showError(String error);

    void hideProgressBar();

    void showData(RedditResponse redditResponse);
}
