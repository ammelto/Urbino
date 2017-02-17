package io.seamoss.urbino.graph.components;

import dagger.Component;
import io.seamoss.urbino.base.nav.NavHeadView;
import io.seamoss.urbino.graph.ActivityScope;
import io.seamoss.urbino.graph.AppGraph;
import io.seamoss.urbino.graph.modules.ActivityModule;
import io.seamoss.urbino.views.home.HomeActivity;

/**
 * Created by Alexander Melton on 2/13/2017.
 */

@ActivityScope
@Component(dependencies = AppGraph.class, modules = ActivityModule.class)
public interface ActivityComponent {

}
