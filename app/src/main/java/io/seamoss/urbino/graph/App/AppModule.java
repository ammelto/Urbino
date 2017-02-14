package io.seamoss.urbino.graph.App;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import io.seamoss.urbino.Urbino;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

@Module
public class AppModule {
    private final Urbino application;

    public AppModule(Urbino app){
        this.application = app;
    }

    @Provides @AppScope
    Context providesApplicationContext(){
        return application;
    }

    @Provides @AppScope
    SharedPreferences providesSharedPreferences(Context app){
        return app.getSharedPreferences("Shared_Prefs", Context.MODE_PRIVATE);
    }
}
