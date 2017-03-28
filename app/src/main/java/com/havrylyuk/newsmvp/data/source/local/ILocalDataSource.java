package com.havrylyuk.newsmvp.data.source.local;

import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;
import com.havrylyuk.newsmvp.data.source.IDataSource;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public interface ILocalDataSource extends IDataSource {

    void getSource(@NonNull String sourceId, @NonNull LoadDataCallback<Source> callback);

    void getAllSources(@NonNull LoadDataCallback<Source> callback);

    void refreshSource(@NonNull Source source);

    void saveSource(@NonNull Source source);

    void saveSources(@NonNull List<Source> source);

    void deleteAllSources();

    void deleteSource(@NonNull String sourceId);

    void getArticles(@NonNull String sourceId, @NonNull LoadDataCallback<Article> callback);

    void getAllArticles();

    void refreshArticle(@NonNull String sourceId, @NonNull Article article);

    void saveArticles(@NonNull String sourceId, @NonNull Article article);

    void saveArticles(@NonNull String sourceId, @NonNull List<Article> articles);

    void deleteAllArticles();

    void deleteArticles(@NonNull String sourceId);
}
