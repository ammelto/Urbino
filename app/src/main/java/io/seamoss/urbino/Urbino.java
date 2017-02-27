package io.seamoss.urbino;

import android.app.Application;

import io.seamoss.urbino.graph.AppGraph;
import io.seamoss.urbino.graph.modules.ActivityModule;
import io.seamoss.urbino.graph.modules.AppModule;
import io.seamoss.urbino.graph.DaggerAppGraph;
import io.seamoss.urbino.graph.modules.PresenterModule;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

public class Urbino extends Application {

    private AppGraph appGraph;
    private static Urbino instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appGraph = DaggerAppGraph
                .builder()
                .appModule(new AppModule(this))
                .presenterModule(new PresenterModule())
                .activityModule(new ActivityModule())
                .build();

        appGraph.inject(this);

        Timber.plant(new Timber.DebugTree());
    }

    public static Urbino instance(){
        return instance;
    }

    public AppGraph getAppGraph(){ return appGraph;}
}
