package io.seamoss.urbino.graph;

import dagger.Component;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseActivity;
import io.seamoss.urbino.graph.modules.ActivityModule;
import io.seamoss.urbino.graph.modules.AppModule;
import io.seamoss.urbino.graph.modules.PresenterModule;
import io.seamoss.urbino.views.home.HomeActivity;
import io.seamoss.urbino.base.nav.NavHeadView;
import io.seamoss.urbino.views.home.boards.BoardsFragment;
import io.seamoss.urbino.views.onboarding.OnboardingActivity;
import io.seamoss.urbino.views.onboarding.signin.SigninFragment;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

@AppScope
@Component(modules = {AppModule.class, PresenterModule.class, ActivityModule.class})
public interface AppGraph {

    void inject(Urbino application);

    void inject(BaseActivity baseActivity);

    void inject(HomeActivity homeActivity);

    void inject(NavHeadView navHeadView);

    void inject(OnboardingActivity onboardingActivity);

    void inject(SigninFragment signinFragment);

    void inject(BoardsFragment boardsFragment);
}
