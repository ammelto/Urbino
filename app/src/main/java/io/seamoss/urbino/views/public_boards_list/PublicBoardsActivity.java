package io.seamoss.urbino.views.public_boards_list;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseActivity;
import io.seamoss.urbino.base.BaseFragment;

/**
 * Created by Alexander Melton on 2/28/2017.
 */

public class PublicBoardsActivity extends BaseActivity implements PublicBoardsView {

    @Inject
    PublicBoardsPresenter publicBoardsPresenter;

    @BindView(R.id.materialViewPager)
    MaterialViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Urbino.instance().getAppGraph().inject(this);

        Toolbar toolbar = viewPager.getToolbar();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
            setSupportActionBar(toolbar);
        }

        viewPager.getViewPager().setAdapter(new PublicBoardsPagerAdapter(getSupportFragmentManager()));
        viewPager.getViewPager().setOffscreenPageLimit(viewPager.getViewPager().getAdapter().getCount());
        viewPager.getPagerTitleStrip().setViewPager(viewPager.getViewPager());

        viewPager.setMaterialViewPagerListener(this::getHeaderDesign);
    }

    public HeaderDesign getHeaderDesign(int page){
        switch (page){
            case 0:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.colorPrimary,
                        "http://learninglog.carleton.ca/wp-content/uploads/2014/09/shutterstock_124077961.jpg"
                );
            case 1:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.colorPrimary,
                        "https://wallpaperscraft.com/image/tubes_paint_letter_english_rainbow_80750_2048x1152.jpg"
                );
            case 2:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.colorPrimary,
                        "http://feelgrafix.com/data_images/out/9/830823-history.jpg"
                );
            case 3:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.colorPrimary,
                        "http://wonderfulengineering.com/wp-content/uploads/2013/06/Engineering-construction-wallpaper2.jpg"
                );
            case 4:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.colorPrimary,
                        "http://cdn.wallpapersafari.com/98/77/OclYIe.jpg"
                );
            case 5:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.colorPrimary,
                        "http://wallpaper-gallery.net/images/education-wallpaper/education-wallpaper-25.jpg"
                );
        }
        return null;
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
}
