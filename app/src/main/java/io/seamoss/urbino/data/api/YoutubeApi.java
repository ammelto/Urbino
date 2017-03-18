package io.seamoss.urbino.data.api;

import java.util.List;

import io.seamoss.urbino.BuildConfig;
import io.seamoss.urbino.data.models.Board;
import io.seamoss.urbino.data.models.VideoMetaData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Alexander Melton on 3/18/2017.
 */

public interface YoutubeApi {

    @GET("https://www.googleapis.com/youtube/v3/videos?id={VIDEO_ID}&part=contentDetails&key=" + BuildConfig.YOUTUBE_KEY)
    Observable<VideoMetaData> getYoutubeVideoData(@Path("VIDEO_ID") String id);
}
