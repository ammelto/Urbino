package io.seamoss.urbino.views.home.board_info;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseActivity;
import io.seamoss.urbino.data.models.Board;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 3/5/2017.
 */

public class BoardInfoActivity extends BaseActivity implements BoardInfoView {

    @Inject
    BoardInfoPresenter boardInfoPresenter;

    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.info_container)
    LinearLayout linearLayout;

    @BindView(R.id.info_delete)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Urbino.instance().getAppGraph().inject(this);

        Intent intent = getIntent();
        Boolean isSubscribed = intent.getBooleanExtra("SUBSCRIBED", false);
        String name = intent.getStringExtra("NAME");
        String url = intent.getStringExtra("URL");

        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(isSubscribed){
            floatingActionButton.hide();
            linearLayout.setPadding(0,0,0,0);
            button.setOnClickListener(this::showDeleteDialog);
        }else{
            floatingActionButton.setOnClickListener(boardInfoPresenter::onFabClick);
            ViewGroup layout = (ViewGroup) button.getParent();
            if(layout != null) layout.removeView(button);
        }

    }

    public void showDeleteDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this board?")
                .setTitle("Delete board")
                .setPositiveButton("Delete", (DialogInterface dialog, int id) -> boardInfoPresenter.onDeleteClick())
                .setNegativeButton("Cancel", (DialogInterface dialog, int id) -> dialog.cancel())
                .create()
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.boardInfoPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.boardInfoPresenter.detachView();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_boards_info;
    }
}
