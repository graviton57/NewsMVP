package com.havrylyuk.newsmvp.sources;

import android.support.annotation.NonNull;

import com.havrylyuk.newsmvp.data.model.Source;
import com.havrylyuk.newsmvp.data.source.RepositoryDataSource;
import com.havrylyuk.newsmvp.data.source.remote.IRemoteDataSource;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class SourcesPresenter implements SourcesContract.Presenter {

    private static final String LOG_TAG = SourcesPresenter.class.getSimpleName();

    private SourcesContract.View view;
    private RepositoryDataSource repository;

    public SourcesPresenter(@NonNull RepositoryDataSource repository,
                            @NonNull SourcesContract.View view) {
        this.repository = checkNotNull(repository, "repository cannot be null");
        this.view = checkNotNull(view, "View cannot be null!");
    }

    @Override
    public void loadSources() {
        loadSourcesFromRepository(view.isNetworkAvailable());
    }

    private void loadSourcesFromRepository(boolean isNetworkAvailable ) {
        view.setRefreshing(true);
        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
       // EspressoIdlingResource.increment(); // App is busy until further notice
            repository.getSources(new IRemoteDataSource.LoadDataCallback<Source>() {
                @Override
                public void onDataLoaded(List<Source> list) {
                    if (list.isEmpty()) {
                        view.showNoSourcesData();
                    }
                    view.showSources(list);
                    view.setRefreshing(false);
                }

                @Override
                public void onDataNotAvailable() {
                    if (!view.isActive()) {
                        return;
                    }
                    view.showLoadingSourcesError();
                }
            }, isNetworkAvailable);

    }


}
