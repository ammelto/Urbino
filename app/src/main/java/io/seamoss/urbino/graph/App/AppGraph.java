package io.seamoss.urbino.graph.App;

import dagger.Component;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseActivity;
import io.seamoss.urbino.views.home.HomeFragment;
import io.seamoss.urbino.views.nav.NavActivity;
import io.seamoss.urbino.views.nav.NavHeadView;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

@AppScope
@Component(modules = {AppModule.class})
public interface AppGraph {

    void inject(Urbino application);

    void inject(BaseActivity baseActivity);

    void inject(NavActivity navActivity);
    void inject(NavHeadView navHeadView);
}
