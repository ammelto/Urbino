package io.seamoss.urbino.data.api;

import java.util.List;

import io.seamoss.urbino.data.models.Board;
import io.seamoss.urbino.data.models.Subject;
import retrofit2.http.Path;
import rx.Observable;

import io.seamoss.urbino.BuildConfig;
import io.seamoss.urbino.data.models.UrbinoProfile;
import retrofit2.http.GET;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

public interface UrbinoApi {

    @GET("/getUser/" + BuildConfig.API_KEY)
    Observable<UrbinoProfile> getUser();

    @GET("/boards/getSubjects/" + BuildConfig.API_KEY)
    Observable<List<Subject>> getSubjects();

    @GET("/boards/getBoards/" + BuildConfig.API_KEY + "/{subject}")
    Observable<List<Board>> getPublicBoards(@Path("subject") String subject);
}
