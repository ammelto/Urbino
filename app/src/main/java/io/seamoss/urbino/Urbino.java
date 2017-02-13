package io.seamoss.urbino;

import android.app.Application;

import io.seamoss.urbino.graph.AppGraph;
import io.seamoss.urbino.graph.AppModule;
import io.seamoss.urbino.graph.DaggerAppGraph;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

public class Urbino extends Application {

    AppGraph appGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        appGraph = DaggerAppGraph
                .builder()
                .appModule(new AppModule(this))
                .build();

        appGraph.inject(this);
    }

    public AppGraph getAppGraph(){ return appGraph;}
}
