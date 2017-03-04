package io.seamoss.urbino.views.public_boards_list;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseActivity;
import io.seamoss.urbino.base.BaseFragment;
import io.seamoss.urbino.data.api.UrbinoApi;
import io.seamoss.urbino.data.models.Subject;
import rx.Subscription;

/**
 * Created by Alexander Melton on 2/28/2017.
 */

public class PublicBoardsActivity extends BaseActivity implements PublicBoardsView {

    @Inject
    PublicBoardsPresenter publicBoardsPresenter;

    @BindView(R.id.materialViewPager)
    MaterialViewPager viewPager;

    @BindView(R.id.public_board_logo)
    TextView textView;

    private List<Subject> subjects;

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
}
