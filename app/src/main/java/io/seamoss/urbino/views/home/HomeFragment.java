package io.seamoss.urbino.views.home;

import android.os.Bundle;

import io.seamoss.urbino.base.BackPressedInterface;
import io.seamoss.urbino.base.BaseFragment;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

public class HomeFragment extends BaseFragment implements HomeView, BackPressedInterface{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
