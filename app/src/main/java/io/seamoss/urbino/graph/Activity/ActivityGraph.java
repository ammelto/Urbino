package io.seamoss.urbino.graph.Activity;

import dagger.Component;
import io.seamoss.urbino.graph.App.AppGraph;
import io.seamoss.urbino.views.home.HomeFragment;
import io.seamoss.urbino.views.nav.NavActivity;

/**
 * Created by Alexander Melton on 2/13/2017.
 */

@ActivityScope
@Component(dependencies = AppGraph.class, modules = ActivityModule.class)
public interface ActivityGraph {
    void inject(HomeFragment homeFragment);
}
