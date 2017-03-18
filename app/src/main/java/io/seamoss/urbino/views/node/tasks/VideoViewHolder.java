package io.seamoss.urbino.views.node.tasks;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.BuildConfig;
import io.seamoss.urbino.R;
import io.seamoss.urbino.data.models.Task;
import io.seamoss.urbino.data.models.tasks.Video;
import io.seamoss.urbino.views.node.NodeActivity;
import io.seamoss.urbino.views.node.NodeView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class VideoViewHolder extends TaskViewholder implements YouTubeThumbnailView.OnInitializedListener {

    private Video video;

    @BindView(R.id.video_frame)
    FrameLayout frameLayout;

    @BindView(R.id.youtube_thumbnail)
    YouTubeThumbnailView youTubeThumbnailView;

    @BindView(R.id.video_title)
    TextView title;

    @BindView(R.id.video_description)
    TextView description;

    @BindView(R.id.video_bonus)
    FrameLayout bonus;

    @BindView(R.id.video_bonus_amount)
    TextView multiplier;

    private static final int RECOVERY_REQUEST = 1;

    public VideoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        youTubeThumbnailView.initialize(BuildConfig.YOUTUBE_KEY, this);
        frameLayout.setOnClickListener(this::onClick);
    }

    public void onClick(View v){
        ((NodeView) itemView.getContext()).openVideo(video);
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
        youTubeThumbnailLoader.setVideo(video.getUrl());
        youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailLoader.release();
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }
        });
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void bind(Video video) {
        this.video = video;
        if(video.isHasBonus()) multiplier.setText(String.format(itemView.getResources().getString(R.string.task_bonus), video.getMultiplier()));
        else bonus.setVisibility(View.INVISIBLE);
        Observable<JSONObject> observable = Observable.fromCallable(() -> getTitleQuietly(video.getUrl()));
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::buildView, e-> Timber.e(e));

    }

    public void buildView(JSONObject jsonObject){
        if(jsonObject == null) return;
        try{
            title.setText(jsonObject.getString("title"));
            description.setText(jsonObject.getString("author_name"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public JSONObject getTitleQuietly(String youtubeUrl) {
        try {
            if (youtubeUrl != null) {
                URL embededURL = new URL("https://www.youtube.com/oembed?url=http://www.youtube.com/watch?v=" +
                        youtubeUrl + "&format=json"
                );

                return new JSONObject(IOUtils.toString(embededURL));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
