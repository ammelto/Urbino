package io.seamoss.urbino.graph.modules;

import dagger.Module;
import dagger.Provides;
import io.seamoss.urbino.views.home.HomePresenter;
import io.seamoss.urbino.views.home.boards.BoardsPresenter;
import io.seamoss.urbino.views.onboarding.OnboardingPresenter;
import io.seamoss.urbino.views.onboarding.signin.SigninPresenter;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

@Module
public class PresenterModule {

    @Provides
    HomePresenter providesHomePresenter(){
        return new HomePresenter();
    }

    @Provides
    BoardsPresenter providesBoardPresenter(){ return new BoardsPresenter();}

    @Provides
    OnboardingPresenter providesOnboardingPresenter(){return new OnboardingPresenter();}

    @Provides
    SigninPresenter providesSigninPresenter(){ return new SigninPresenter();}
}
