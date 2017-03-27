package com.havrylyuk.newsmvp.sources;

import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.BasePresenter;
import com.havrylyuk.newsmvp.BaseView;
import com.havrylyuk.newsmvp.data.model.Source;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public interface SourcesContract  {

    interface View extends BaseView {

        void showSources(@NonNull List<Source> sources);

        void setRefreshing(boolean refreshing);

        boolean isNetworkAvailable();

        boolean isActive();

        void showLoadingSourcesError();

        void showNoSourcesData();
    }

    interface Presenter extends BasePresenter {

      void loadSources();
    }
}
