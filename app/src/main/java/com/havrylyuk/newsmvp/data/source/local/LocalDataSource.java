package com.havrylyuk.newsmvp.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;

import java.util.List;


/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class LocalDataSource implements ILocalDataSource {


    private static final String LOG_TAG = LocalDataSource.class.getSimpleName();

    private static LocalDataSource INSTANCE;
    private Context context;

    private LocalDataSource(Context context) {
        this.context = context;
    }

    public static LocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getSource(@NonNull String sourceId, @NonNull GetDataCallback callback) {

    }

    @Override
    public void getAllSources() {

    }

    @Override
    public void saveSource(Source source) {

    }

    @Override
    public void saveSources(List<Source> source) {

    }

    @Override
    public void deleteAllSources() {

    }

    @Override
    public void deleteSource(@NonNull String sourceId) {

    }

    @Override
    public void getArticles(@NonNull String sourceId, @NonNull GetDataCallback<Article> callback) {

    }

    @Override
    public void getAllArticles() {

    }

    @Override
    public void saveArticles(Article article) {

    }

    @Override
    public void saveArticles(List<Article> articles) {

    }

    @Override
    public void deleteAllArticles() {

    }

    @Override
    public void deleteArticle(@NonNull String articleId) {

    }


}
