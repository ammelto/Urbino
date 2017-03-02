package io.seamoss.urbino.graph.modules;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.data.SharedPrefsManager;
import io.seamoss.urbino.data.models.User;
import io.seamoss.urbino.domain.repository.UserRepository;
import io.seamoss.urbino.graph.ActivityScope;
import io.seamoss.urbino.graph.AppScope;
import io.seamoss.urbino.views.home.HomePresenter;
import rx.Observable;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

@Module
public class AppModule {
    private final Urbino application;
    private final User currentUser;

    public AppModule(Urbino app){
        this.application = app;
        this.currentUser = new User();
    }

    @Provides
    @AppScope
    Context providesApplicationContext(){
        return application;
    }

    @Provides
    @AppScope
    SharedPreferences providesSharedPreferences(Context app){
        return app.getSharedPreferences("Shared_Prefs", Context.MODE_PRIVATE);
    }


    @Provides
    @AppScope
    SharedPrefsManager providesSharedPrefsManager(){
        return new SharedPrefsManager(application);
    }

    @Provides
    @AppScope
    User providesCurrentUser(){
        return currentUser;
    }

}
