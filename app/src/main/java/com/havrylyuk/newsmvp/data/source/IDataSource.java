package com.havrylyuk.newsmvp.data.source;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 28.03.2017.
 */

public interface IDataSource {

    interface  LoadDataCallback<T> {

        void onDataLoaded(List<T> list);

        void onDataNotAvailable();
    }
}
