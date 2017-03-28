package com.havrylyuk.newsmvp.news;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.havrylyuk.newsmvp.Injection;
import com.havrylyuk.newsmvp.R;
import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;
import com.havrylyuk.newsmvp.sources.SourcesActivity;
import com.havrylyuk.newsmvp.sources.SourcesPresenter;
import com.havrylyuk.newsmvp.sources.SourcesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class NewsActivity extends AppCompatActivity implements NewsContract.View {

    public static final String EXTRA_SOURCE_ID = "EXTRA_SOURCE_ID";
    private NewsContract.Presenter presenter;
    private NewsRecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String sourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent()!=null){
            sourceId = getIntent().getStringExtra(EXTRA_SOURCE_ID);
            Log.d("NewsActivity", "sourceId=" + sourceId);
        }
        setContentView(R.layout.activity_content);
        initToolbar();
        initRecycler();
        initSwipeToRefresh();
        initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadArticles(sourceId);
    }

    // Create the presenter
    private void initPresenter() {
        presenter = new NewsPresenter(
                Injection.provideRepository(getApplicationContext()),this);
    }

    @Override
    public void showArticles(@NonNull List<Article> articles) {
        if (adapter!=null){
            adapter.addArticles(articles);
        }
    }

    @Override
    public void setRefreshing(final boolean refreshing) {
        if (swipeRefreshLayout == null) {
            return;
        }
        // Make sure setRefreshing() is called after the layout is done with everything else.
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(refreshing);
            }
        });
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void showLoadingSourcesError() {
        setRefreshing(false);
        Toast.makeText(this, R.string.error_load_data,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoSourcesData() {
        setRefreshing(false);
        Toast.makeText(this, R.string.empty_list,Toast.LENGTH_SHORT).show();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(getTitle());
        }
    }

    private void initRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        adapter = new NewsRecyclerViewAdapter(new NewsRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Article article) {
                showNews(article.getUrl());
            }
        }, new ArrayList<Article>());
        recyclerView.setAdapter(adapter);
    }

    private void showNews(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    private void initSwipeToRefresh() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadArticles(sourceId);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
}
