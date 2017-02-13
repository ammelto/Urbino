package io.seamoss.urbino.graph;

import dagger.Component;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseActivity;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

@AppScope
@Component(modules = {AppModule.class})
public interface AppGraph {

    void inject(Urbino application);

    void inject(BaseActivity baseActivity);
}
