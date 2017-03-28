package com.havrylyuk.newsmvp.data.source.remote;

import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;
import com.havrylyuk.newsmvp.data.source.IDataSource;
import com.havrylyuk.newsmvp.data.source.RepositoryDataSource;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public interface IRemoteDataSource extends IDataSource {

    void getSources(@NonNull  LoadDataCallback<Source> callback);

    void getArticles(String source, @NonNull LoadDataCallback<Article> callback);
}
