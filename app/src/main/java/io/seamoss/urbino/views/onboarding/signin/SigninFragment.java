package io.seamoss.urbino.views.onboarding.signin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseFragment;

/**
 * Created by Alexander Melton on 2/17/2017.
 */

public class SigninFragment extends BaseFragment implements SigninView {

    @Inject
    SigninPresenter signinPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Urbino.instance().getAppGraph().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        signinPresenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        signinPresenter.detachView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_signin, container, false);
        setUnbinder(ButterKnife.bind(this, fragmentView));
        return fragmentView;
    }
}
