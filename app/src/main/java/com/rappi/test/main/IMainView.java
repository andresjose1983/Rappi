package com.rappi.test.main;

import android.widget.ImageView;

import com.rappi.test.model.Children;

/**
 * Created by Mendez Fernandez on 30/12/2016.
 */

public interface IMainView extends IMain{

    void goToDetail(Children children, ImageView mIvReddirHeader);
}
