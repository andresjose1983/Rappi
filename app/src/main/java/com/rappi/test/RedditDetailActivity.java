package com.rappi.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.rappi.test.model.Children;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RedditDetailActivity extends AppCompatActivity {

    private final static String CHILDREN_INTENT = "com.rappi.test.intent.CHILDREN_INTENT";

    @BindView(R.id.iv_photo_header)
    ImageView mIvPhotoHeader;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tb_reddit)
    Toolbar mToolbar;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_user)
    TextView mTvUser;
    @BindView(R.id.tv_sub_title)
    TextView mTvSubTitle;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.ctl_reddit)
    CollapsingToolbarLayout mCtlReddit;
    @BindView(R.id.abl_reddit)
    AppBarLayout mAblReddit;
    @BindView(R.id.cv_icon)
    CardView mCvIcon;

    public static void show(MainActivity mainActivity, Children children, ImageView mIvReddirHeader){
        Intent intent = new Intent(mainActivity, RedditDetailActivity.class)
                .putExtra(CHILDREN_INTENT, children);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(mainActivity, mIvReddirHeader, "photo_header");
        mainActivity.startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_detail);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){
        Children children = (Children)getIntent().getExtras().getSerializable(CHILDREN_INTENT);

        setSupportActionBar(mToolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //load image
        loadImage(children.getHeaderImg(), mIvPhotoHeader);
        loadImage(children.getIconImg(), mIvIcon);

        //User
        mTvUser.setText(children.getDisplayName());

        //Sub title
        mTvSubTitle.setText(children.getHeaderTitle());

        //Description
        mTvDescription.setText(children.getSubmitText());

        //Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(children.getCreated());
        mTvDate.setText(new SimpleDateFormat("EEEE MMMM d HH:mm:ss").format(calendar.getTime()));

        mAblReddit.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0){
                //  Collapsed
                mCtlReddit.setTitle(children.getTitle());
                mCvIcon.animate()
                        .alpha(0.0f)
                        .setDuration(150)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mCvIcon.setVisibility(View.GONE);
                            }
                        });
            }
            else{
                //Expanded
                mCtlReddit.setTitle(null);
                mCvIcon.animate()
                        .alpha(1.0f)
                        .setDuration(150)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mCvIcon.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });
    }

    private void loadImage(String url, ImageView imageView){
        Glide.with(this).load(url).crossFade(500).into(imageView);
    }
}
