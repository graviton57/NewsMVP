package com.havrylyuk.newsmvp.data.source;

import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;
import com.havrylyuk.newsmvp.data.source.local.LocalDataSource;
import com.havrylyuk.newsmvp.data.source.remote.IRemoteDataSource;
import com.havrylyuk.newsmvp.data.source.remote.RemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.common.internal.Preconditions.checkNotNull;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class Repository implements RepositoryDataSource {

    private static Repository INSTANCE = null;

    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;


    public static Repository getInstance(RemoteDataSource remoteDataSource,
                                              LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private Repository(@NonNull RemoteDataSource remoteDataSource,
                            @NonNull LocalDataSource localDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
        this.localDataSource = checkNotNull(localDataSource);
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getArticles(String sourceID,
                            IDataSource.LoadDataCallback<Article> callback,
                            boolean isNetworkAvailable) {

        checkNotNull(callback);
        if (isNetworkAvailable){
            getArticlesFromRemoteDataSource(sourceID, callback);
        } else {
            getArticlesFromLocalDataSource(sourceID, callback);
        }
    }

    @Override
    public void saveArticles(@NonNull List<Article> articles) {

    }


    @Override
    public void deleteAllArticles() {

    }

    @Override
    public void getSources(@NonNull IDataSource.LoadDataCallback<Source> callback,
                           boolean isNetworkAvailable) {
        checkNotNull(callback);
        if (isNetworkAvailable){
            getSourcesFromRemoteDataSource(callback);
        } else {
            getSourcesFromLocalDataSource(callback);
        }
    }

    @Override
    public void saveSources(@NonNull List<Source> sources) {
        localDataSource.saveSources(sources);
    }


    @Override
    public void deleteAllSources() {
        localDataSource.deleteAllSources();
    }

    private void getArticlesFromLocalDataSource(String sourceId,
                                                final IDataSource.LoadDataCallback<Article> callback) {
        checkNotNull(callback);
        localDataSource.getArticles(sourceId, callback);

    }

    private void getArticlesFromRemoteDataSource(final String sourceId,
                                                 final IDataSource.LoadDataCallback<Article> callback) {
        remoteDataSource.getArticles(sourceId, new IDataSource.LoadDataCallback<Article>() {
            @Override
            public void onDataLoaded(List<Article> articles) {
                refreshArticleLocalDataSource(sourceId, articles);
                callback.onDataLoaded(new ArrayList<>(articles));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getSourcesFromLocalDataSource(@NonNull final IDataSource.LoadDataCallback<Source> callback) {
        checkNotNull(callback);
        localDataSource.getAllSources(callback);
    }

    private void getSourcesFromRemoteDataSource(@NonNull final IRemoteDataSource.LoadDataCallback<Source> callback) {
        remoteDataSource.getSources(new IRemoteDataSource.LoadDataCallback<Source>() {
            @Override
            public void onDataLoaded(List<Source> sources) {
               refreshSourceLocalDataSource(sources);
               callback.onDataLoaded(new ArrayList<>(sources));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshSourceLocalDataSource(List<Source> sources) {
        localDataSource.deleteAllSources();
        localDataSource.saveSources(sources);
    }

    private void refreshArticleLocalDataSource(String sourceId, List<Article> articles) {
        localDataSource.deleteAllArticles();
        localDataSource.saveArticles(sourceId, articles);
    }
}
