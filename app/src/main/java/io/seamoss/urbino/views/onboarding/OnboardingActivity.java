package io.seamoss.urbino.views.onboarding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import javax.inject.Inject;

import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.views.onboarding.signin.SigninFragment;
import io.seamoss.urbino.views.onboarding.slides.IntroFragment;

public class OnboardingActivity extends AppIntro implements OnboardingView {

    @Inject
    OnboardingPresenter onboardingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Urbino.instance().getAppGraph().inject(this);
        Fragment signinFragment = new SigninFragment();
        Fragment introFragment = new IntroFragment();
        
        addSlide(introFragment);
        addSlide(signinFragment);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onboardingPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.onboardingPresenter.detachView();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
