package com.havrylyuk.newsmvp.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.havrylyuk.newsmvp.BuildConfig;
import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.ArticleResponse;
import com.havrylyuk.newsmvp.data.model.Source;
import com.havrylyuk.newsmvp.data.model.SourceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class RemoteDataSource implements IRemoteDataSource {

    private static final String LOG_TAG = RemoteDataSource.class.getSimpleName();

    private static RemoteDataSource INSTANCE;
    private ApiService service;

    private RemoteDataSource(ApiService service) {
        this.service = service;
    }

    public static RemoteDataSource getInstance(ApiService service) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(service);
        }
        return INSTANCE;
    }

    @Override
    public void getSources(@NonNull final LoadDataCallback<Source> callback) {
        Call<SourceResponse> articleResponseCall = service.getSource("en");
        articleResponseCall.enqueue(new Callback<SourceResponse>() {
            @Override
            public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
                if ("ok".equals(response.body().getStatus())){
                    if (!response.body().getSources().isEmpty()) {
                        callback.onDataLoaded(response.body().getSources());
                    } else {
                        Log.e(LOG_TAG, "Oops, something went wrong!");
                    }
                } else {
                    Log.e(LOG_TAG, "Oops, something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<SourceResponse> call, Throwable t) {
                Log.e(LOG_TAG, "Error:" + t.getMessage());
            }
        });

    }

    @Override
    public void getArticles(String source, @NonNull final LoadDataCallback<Article> callback) {
        Call<ArticleResponse> articleResponseCall = service.getArticle(BuildConfig.API_KEY, source, "top");
        articleResponseCall.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.body() != null) {
                    if ("ok".equals(response.body().getStatus())){
                        if (!response.body().getArticles().isEmpty()) {
                            callback.onDataLoaded(response.body().getArticles());
                        } else {
                            callback.onDataNotAvailable();
                            Log.e(LOG_TAG, "Oops, something went wrong!");
                        }
                    } else {
                        callback.onDataNotAvailable();
                        Log.e(LOG_TAG, "Oops, something went wrong!");
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.e(LOG_TAG, "Error:" + t.getMessage());
                callback.onDataNotAvailable();
            }
        });

    }
}
