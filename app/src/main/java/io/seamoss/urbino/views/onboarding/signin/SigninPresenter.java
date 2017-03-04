package io.seamoss.urbino.views.onboarding.signin;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;

import io.seamoss.urbino.base.mvp.BasePresenter;
import io.seamoss.urbino.data.api.UrbinoApi;
import io.seamoss.urbino.data.models.UrbinoProfile;
import io.seamoss.urbino.data.models.User;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 2/17/2017.
 */

public class SigninPresenter extends BasePresenter<SigninView> {

    private User user;
    private UrbinoApi urbinoApi;
    private Subscription urbinoProfileSubscription;

    @Inject
    public SigninPresenter(User user, UrbinoApi urbinoApi) {
        this.user = user;
        this.urbinoApi = urbinoApi;
    }

    @Override
    public void attachView(SigninView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    protected void onSignInSuccess(GoogleSignInAccount account){
        user.setFirstName(account.getGivenName());
        user.setLastName(account.getFamilyName());
        user.setUUID(account.getIdToken());
        user.setImageUrl(account.getPhotoUrl());
        user.setDisplayName(account.getDisplayName());

        fetchUrbinoProfile();

    }

    protected void fetchUrbinoProfile(){
        urbinoProfileSubscription = urbinoApi.getUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateUser, e -> Timber.d(e, "Failed"));
    }

    public void updateUser(UrbinoProfile urbinoProfile){

        user.setBoards(urbinoProfile.getBoards());
        user.setBackgroundNav(urbinoProfile.getBackgroundUrl());
        finish();
    }

    private void finish(){
        urbinoProfileSubscription.unsubscribe();
        urbinoProfileSubscription = null;

        getView().launchHomeActivity();
    }
}
