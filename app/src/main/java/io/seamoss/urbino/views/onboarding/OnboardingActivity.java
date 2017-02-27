package io.seamoss.urbino.views.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import javax.inject.Inject;

import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.views.onboarding.signin.SigninActivity;

public class OnboardingActivity extends AppIntro implements OnboardingView {

    @Inject
    OnboardingPresenter onboardingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Urbino.instance().getAppGraph().inject(this);

        addSlide(AppIntroFragment.newInstance("Urbino",
                "Why should school be boring?",
                R.drawable.ic_logo_24dp,
                ResourcesCompat.getColor(getResources(), R.color.tab5, null)));
        addSlide(AppIntroFragment.newInstance("Placeholder",
                "Text",
                R.drawable.ic_logo_24dp,
                ResourcesCompat.getColor(getResources(), R.color.tab3, null)));
        addSlide(AppIntroFragment.newInstance("Placeholder",
                "Text",
                R.drawable.ic_logo_24dp,
                ResourcesCompat.getColor(getResources(), R.color.tab2, null)));
        addSlide(AppIntroFragment.newInstance("Placeholder",
                "Text",
                R.drawable.ic_logo_24dp,
                ResourcesCompat.getColor(getResources(), R.color.tab4, null)));

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
        Intent intent = new Intent(this, SigninActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
        finish();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
