package io.seamoss.urbino.graph.modules;

import dagger.Module;
import dagger.Provides;
import io.seamoss.urbino.data.api.UrbinoApi;
import io.seamoss.urbino.data.models.User;
import io.seamoss.urbino.views.active_board.ActiveBoardPresenter;
import io.seamoss.urbino.views.home.HomePresenter;
import io.seamoss.urbino.views.home.boards.BoardsPresenter;
import io.seamoss.urbino.views.node.NodePresenter;
import io.seamoss.urbino.views.public_boards_list.PublicBoardsPresenter;
import io.seamoss.urbino.views.onboarding.OnboardingPresenter;
import io.seamoss.urbino.views.onboarding.signin.SigninPresenter;
import io.seamoss.urbino.views.home.board_info.BoardInfoPresenter;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

@Module
public class PresenterModule {

    @Provides
    HomePresenter providesHomePresenter(User user){
        return new HomePresenter(user);
    }

    @Provides
    BoardsPresenter providesBoardPresenter(User user){ return new BoardsPresenter(user);}

    @Provides
    OnboardingPresenter providesOnboardingPresenter(){return new OnboardingPresenter();}

    @Provides
    SigninPresenter providesSigninPresenter(User user, UrbinoApi urbinoApi){ return new SigninPresenter(user, urbinoApi);}

    @Provides
    PublicBoardsPresenter providesPublicBoardsPresenter(UrbinoApi urbinoApi){ return new PublicBoardsPresenter(urbinoApi); }

    @Provides
    BoardInfoPresenter providesBoardInfoPresenter(){return new BoardInfoPresenter();}

    @Provides
    ActiveBoardPresenter providesActiveBoardPresenter(){return new ActiveBoardPresenter();}

    @Provides
    NodePresenter providesNodePresenter(){return new NodePresenter();}
}
