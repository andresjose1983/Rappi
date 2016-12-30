package com.rappi.test;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rappi.test.adapter.RedditAdapter;
import com.rappi.test.main.IMainView;
import com.rappi.test.main.MainPresenter;
import com.rappi.test.model.RedditResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView,
        SearchView.OnQueryTextListener {

    @BindView(R.id.pb_load)
    ProgressBar mPbLoad;
    @BindView(R.id.rv_reddit)
    RecyclerView mRvReddit;

    private MainPresenter mMainPresenter;
    private SearchView mSearchView;
    private MenuItem mMenu;
    private RedditAdapter mRedditAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        mMenu = menu.findItem(R.id.action_search);
        // Associate searchable configuration with the SearchView
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setOnQueryTextListener(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mRedditAdapter.getFilter().filter(query.toUpperCase());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mRedditAdapter.getFilter().filter(newText.toUpperCase());
        return false;
    }

    @Override
    public void showProgressBar() {
        mPbLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressBar() {
        mPbLoad.setVisibility(View.GONE);
    }

    @Override
    public void showData(RedditResponse redditResponse) {
        final int span = 3;

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, span);
        mRvReddit.setLayoutManager(mLayoutManager);
        mRvReddit.setItemAnimator(new DefaultItemAnimator());
        mRvReddit.setHasFixedSize(true);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x / span;

        mRedditAdapter = new RedditAdapter(redditResponse.getData().getChildren(), width);
        mRvReddit.setAdapter(mRedditAdapter);
    }
}
