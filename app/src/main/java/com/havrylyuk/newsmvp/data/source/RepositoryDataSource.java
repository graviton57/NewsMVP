package com.havrylyuk.newsmvp.data.source;

import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;
import com.havrylyuk.newsmvp.data.source.remote.RemoteDataSource;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public interface RepositoryDataSource {


    void getArticles(String source, RemoteDataSource.LoadDataCallback<Article> callback,  boolean isNetworkAvailable);

    void getAllArticles(RemoteDataSource.LoadDataCallback<Article> callback, boolean isNetworkAvailable);

    void saveArticles(List<Article> articles);

    void saveArticles(String source, List<Article> articles);

    void deleteAllArticles();

    void deleteArticles(String sourceId);


    void getSources(RemoteDataSource.LoadDataCallback<Source> callback, boolean isNetworkAvailable);

    void saveSources(List<Source> sources);

    void saveSource(Source source);

    void deleteAllSources();

}
