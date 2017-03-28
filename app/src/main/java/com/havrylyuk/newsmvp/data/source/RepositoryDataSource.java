package com.havrylyuk.newsmvp.data.source;

import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;

import java.util.List;

/**
 *
 * Created by Igor Havrylyuk on 28.03.2017.
 */

public interface RepositoryDataSource {


    void getArticles(String source, IDataSource.LoadDataCallback<Article> callback, boolean isNetworkAvailable);

    void saveArticles(@NonNull List<Article> articles);

    void deleteAllArticles();

    void getSources(@NonNull IDataSource.LoadDataCallback<Source> callback, boolean isNetworkAvailable);

    void saveSources(@NonNull List<Source> sources);

    void deleteAllSources();

}
