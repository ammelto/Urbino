package io.seamoss.urbino.views.onboarding.slides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.base.BaseFragment;

/**
 * Created by Alexander Melton on 2/17/2017.
 */

public class IntroFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_onboarding_intro, container, false);
        setUnbinder(ButterKnife.bind(this, fragmentView));
        return fragmentView;
    }
}
