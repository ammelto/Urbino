package io.seamoss.urbino.views.public_boards_list;

import java.util.List;

import javax.inject.Inject;

import io.seamoss.urbino.base.mvp.BasePresenter;
import io.seamoss.urbino.data.api.UrbinoApi;
import io.seamoss.urbino.data.models.Subject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 2/28/2017.
 */

public class PublicBoardsPresenter extends BasePresenter<PublicBoardsView> {

    private Subscription urbinoSubjectsSubscription;
    private UrbinoApi urbinoApi;

    @Inject
    public PublicBoardsPresenter(UrbinoApi urbinoApi) {
        this.urbinoApi = urbinoApi;
    }

    @Override
    public void attachView(PublicBoardsView view) {
        super.attachView(view);
        fetchSubjects();
    }

    private void fetchSubjects(){
        urbinoSubjectsSubscription = urbinoApi.getSubjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::buildSubjectsViewPager, e -> Timber.d(e, "Failed"));
    }

    private void buildSubjectsViewPager(List<Subject> subjects){
        getView().buildViewPager(subjects);
    }

    @Override
    public void detachView() {
        urbinoSubjectsSubscription.unsubscribe();
        urbinoSubjectsSubscription = null;
        super.detachView();
    }

}
