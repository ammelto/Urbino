package io.seamoss.urbino.graph.modules;

import dagger.Module;
import dagger.Provides;
import io.seamoss.urbino.BuildConfig;
import io.seamoss.urbino.data.api.UrbinoApi;
import io.seamoss.urbino.graph.AppScope;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexander Melton on 2/27/2017.
 */

@Module
public class NetworkModule {

    @Provides
    @AppScope
    public Retrofit providesRetrofitAdapter(){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    public UrbinoApi providesBoardsApi(Retrofit adapter){
        return adapter.create(UrbinoApi.class);
    }
}
