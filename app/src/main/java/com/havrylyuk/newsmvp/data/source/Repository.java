package com.havrylyuk.newsmvp.data.source;

import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;
import com.havrylyuk.newsmvp.data.source.local.ILocalDataSource;
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
    public void getArticles(String source,
                            RemoteDataSource.LoadDataCallback<Article> callback,
                            boolean isNetworkAvailable) {

        checkNotNull(callback);
        if (isNetworkAvailable){
            getArticlesFromRemoteDataSource(source, callback);
        } else {
            getArticlesFromLocalDataSource(source, callback);
        }
    }

    @Override
    public void getAllArticles(RemoteDataSource.LoadDataCallback<Article> callback,
                               boolean isNetworkAvailable) {

    }

    @Override
    public void saveArticles(List<Article> articles) {

    }

    @Override
    public void saveArticles(String source, List<Article> articles) {

    }

    @Override
    public void deleteArticles(String sourceId) {

    }

    @Override
    public void deleteAllArticles() {

    }

    @Override
    public void getSources(RemoteDataSource.LoadDataCallback<Source> callback,
                           boolean isNetworkAvailable) {
        checkNotNull(callback);
        if (isNetworkAvailable){
            getSourcesFromRemoteDataSource(callback);
        } else {
            getSourcesFromLocalDataSource(callback);
        }
    }



    @Override
    public void saveSources(List<Source> sources) {
        localDataSource.saveSources(sources);
    }

    @Override
    public void saveSource(Source source) {
        localDataSource.saveSource(source);
    }

    @Override
    public void deleteAllSources() {
        localDataSource.deleteAllSources();
    }

    private void getArticlesFromLocalDataSource(String sourceId,
                                                final IRemoteDataSource.LoadDataCallback<Article> callback) {
        localDataSource.getArticles(sourceId, new ILocalDataSource.GetDataCallback<Article>() {
            @Override
            public void onDateLoaded(Article entity) {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void getArticlesFromRemoteDataSource(String sourceId,
                                                 final IRemoteDataSource.LoadDataCallback<Article> callback) {
        remoteDataSource.getArticles(sourceId, new IRemoteDataSource.LoadDataCallback<Article>() {
            @Override
            public void onDataLoaded(List<Article> articles) {
                refreshArticleLocalDataSource(articles);
                callback.onDataLoaded(new ArrayList<>(articles));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }


    private void getSourcesFromLocalDataSource(@NonNull final IRemoteDataSource.LoadDataCallback<Source> callback) {
        localDataSource.getAllSources();
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

    private void refreshArticleLocalDataSource(List<Article> articles) {
        localDataSource.deleteAllArticles();
        localDataSource.saveArticles(articles);
    }
}
