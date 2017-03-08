package io.seamoss.urbino.views.public_boards_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.nav.BaseNavActivity;
import io.seamoss.urbino.data.models.Board;
import io.seamoss.urbino.data.models.Subject;
import io.seamoss.urbino.views.home.board_info.BoardInfoActivity;
import io.seamoss.urbino.views.public_boards_list.board_info_public.BoardInfoPublicActivity;

/**
 * Created by Alexander Melton on 2/28/2017.
 */

public class PublicBoardsActivity extends BaseNavActivity implements PublicBoardsView {

    @Inject
    PublicBoardsPresenter publicBoardsPresenter;

    @BindView(R.id.materialViewPager)
    MaterialViewPager viewPager;

    @BindView(R.id.public_board_logo)
    TextView textView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private List<Subject> subjects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Urbino.instance().getAppGraph().inject(this);

        Toolbar toolbar = viewPager.getToolbar();

        if(getSupportActionBar() != null) {
            setSupportActionBar(toolbar);
            viewPager.getToolbar().setNavigationOnClickListener(this::navClickListener);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    public void navClickListener(View v){
        drawerLayout.openDrawer(Gravity.START);
    }

    public HeaderDesign getHeaderDesign(int page){
        return HeaderDesign.fromColorResAndUrl(
                R.color.colorPrimary,
                subjects.get(page).getImage()
        );
    }

    @Override
    public void buildViewPager(List<Subject> subjects) {
        this.subjects = subjects;
        PublicBoardsPagerAdapter publicBoardsPagerAdapter = new PublicBoardsPagerAdapter(getSupportFragmentManager());
        publicBoardsPagerAdapter.setSubjects(subjects);
        viewPager.getViewPager().setAdapter(publicBoardsPagerAdapter);
        viewPager.getViewPager().setOffscreenPageLimit(viewPager.getViewPager().getAdapter().getCount());
        viewPager.getPagerTitleStrip().setViewPager(viewPager.getViewPager());

        viewPager.setMaterialViewPagerListener(this::getHeaderDesign);
    }

    @Override
    public void onResume() {
        super.onResume();
        publicBoardsPresenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        publicBoardsPresenter.detachView();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_public_boards_list;
    }

    @Override
    public void gotoBoardInfoActivity(Board board) {
        Intent intent = new Intent(this, BoardInfoPublicActivity.class);
        intent.putExtras(getIntent());
        intent.putExtra("NAME", board.getName());
        intent.putExtra("URL", board.getUrl());
        intent.putExtra("SUBSCRIBED", false);
        startActivity(intent);
    }
}
