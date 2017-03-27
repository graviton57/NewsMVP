package com.havrylyuk.newsmvp.news;

import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.BasePresenter;
import com.havrylyuk.newsmvp.BaseView;
import com.havrylyuk.newsmvp.data.model.Article;
import com.havrylyuk.newsmvp.data.model.Source;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public interface NewsContract {

    interface View extends BaseView {

        void showArticles(@NonNull List<Article> sources);

        void setRefreshing(boolean refreshing);

        boolean isNetworkAvailable();

        boolean isActive();

        void showLoadingSourcesError();

        void showNoSourcesData();
    }

    interface Presenter extends BasePresenter {

        void loadArticles(String sourceId);
    }
}
