package com.havrylyuk.newsmvp.data.source.local;

import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public interface ILocalDataSource {


    interface GetDataCallback<T> {

        void onDateLoaded(T entity);

        void onDataNotAvailable();
    }

    void getSource(@NonNull String sourceId, @NonNull GetDataCallback<Source> callback);

    void getAllSources();

    void saveSource(Source source);

    void saveSources(List<Source> source);

    void deleteAllSources();

    void deleteSource(@NonNull String sourceId);

    void getArticles(@NonNull String sourceId, @NonNull GetDataCallback<Article> callback);

    void getAllArticles();

    void saveArticles(Article article);

    void saveArticles(List<Article> articles);

    void deleteAllArticles();

    void deleteArticle(@NonNull String articleId);
}
