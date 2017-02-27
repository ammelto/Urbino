package io.seamoss.urbino.data.api;

import rx.Observable;

import io.seamoss.urbino.BuildConfig;
import io.seamoss.urbino.data.models.UrbinoProfile;
import retrofit2.http.GET;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

public interface UrbinoApi {

    @GET("/getUser/" + BuildConfig.API_KEY)
    Observable<UrbinoProfile> getBoards();
}
