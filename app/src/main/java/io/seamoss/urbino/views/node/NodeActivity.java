package io.seamoss.urbino.views.node;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import javax.inject.Inject;

import butterknife.BindView;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseActivity;
import io.seamoss.urbino.data.models.tasks.Game;
import io.seamoss.urbino.data.models.tasks.Video;
import io.seamoss.urbino.views.home.boards.BoardsFragment;
import io.seamoss.urbino.views.node.tasks.GameActivity;
import io.seamoss.urbino.views.node.tasks.VideoPlayerActivity;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 3/16/2017.
 */

public class NodeActivity extends BaseActivity implements NodeView {


    @Inject
    NodePresenter nodePresenter;

    @BindView(R.id.node_pager)
    ViewPager viewPager;

    @BindView(R.id.node_tabs)
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");

        Urbino.instance().getAppGraph().inject(this);

        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getIntent().getExtras().getString("name"));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewPager.setAdapter(new NodePageAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(
                ContextCompat.getColor(this, R.color.colorDivider),
                ContextCompat.getColor(this, R.color.colorIcons)
        );
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorIcons));

    }

    @Override
    public void openVideo(Video video) {
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        intent.putExtras(getIntent());
        Bundle extras = new Bundle();
        extras.putString("url", video.getUrl());
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void launchGame(Game game) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtras(getIntent());
        Bundle extras = new Bundle();
        extras.putString("url", game.getUrl());
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.nodePresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.nodePresenter.detachView();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_node;
    }
}
