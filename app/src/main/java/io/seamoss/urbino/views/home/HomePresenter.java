package io.seamoss.urbino.views.home;

import io.seamoss.urbino.base.mvp.BasePresenter;
import io.seamoss.urbino.data.api.UrbinoApi;
import io.seamoss.urbino.data.models.Board;
import io.seamoss.urbino.data.models.UrbinoProfile;
import io.seamoss.urbino.data.models.User;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 2/13/2017.
 */

public class HomePresenter extends BasePresenter<HomeView> {

    private User user;

    public HomePresenter(User user) {
        this.user = user;
    }

    @Override
    public void attachView(HomeView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

}
