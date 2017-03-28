package com.havrylyuk.newsmvp;

import android.content.Context;
import android.support.annotation.NonNull;


import com.havrylyuk.newsmvp.data.source.Repository;
import com.havrylyuk.newsmvp.data.source.local.LocalDataSource;
import com.havrylyuk.newsmvp.data.source.remote.ApiClient;
import com.havrylyuk.newsmvp.data.source.remote.ApiService;
import com.havrylyuk.newsmvp.data.source.remote.RemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static Repository provideRepository(@NonNull Context context) {
        checkNotNull(context);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        return Repository.getInstance(RemoteDataSource.getInstance(apiService),
                LocalDataSource.getInstance(context.getContentResolver()));
    }
}
