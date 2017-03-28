package com.havrylyuk.newsmvp.news;

import android.support.annotation.NonNull;
import android.util.Log;

import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.source.RepositoryDataSource;
import com.havrylyuk.newsmvp.data.source.remote.IRemoteDataSource;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class NewsPresenter implements NewsContract.Presenter {

    private static final String LOG_TAG = NewsPresenter.class.getSimpleName();

    private NewsContract.View view;
    private RepositoryDataSource repository;

    public NewsPresenter(@NonNull RepositoryDataSource repository,
                         @NonNull NewsContract.View view) {
        this.repository = checkNotNull(repository, "repository cannot be null");
        this.view = checkNotNull(view, "View cannot be null!");
    }

    @Override
    public void loadArticles(String sourceId) {
        loadNewsFromRepository(sourceId, view.isNetworkAvailable());
    }

    private void loadNewsFromRepository(String source, boolean isNetworkAvailable ) {
        view.setRefreshing(true);
        repository.getArticles(source, new IRemoteDataSource.LoadDataCallback<Article>() {
                @Override
                public void onDataLoaded(List<Article> list) {
                    if (list.isEmpty()) {
                        view.showNoSourcesData();
                    }
                    view.showArticles(list);
                    view.setRefreshing(false);
                }

                @Override
                public void onDataNotAvailable() {
                    if (!view.isActive()) {
                        return;
                    }
                    view.showLoadingSourcesError();
                }
            }, isNetworkAvailable);


    }
}
