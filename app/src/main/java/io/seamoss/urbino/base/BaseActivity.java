package io.seamoss.urbino.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import javax.inject.Inject;

import io.seamoss.urbino.Urbino;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity{
    @Inject public SharedPreferences prefs;
    @Inject public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((Urbino) getApplication()).getAppGraph().inject(this);

    }

    protected void attachFragment(int containerViewId, Fragment fragment){
        attachFragment(containerViewId, fragment, false);
    }

    protected void attachFragment(int containerViewId, Fragment fragment, boolean addToBackstack){
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, fragment.getClass().getSimpleName());

        if(addToBackstack){
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getActiveFragment();
        if (fragment != null && fragment instanceof BackPressedInterface) {
            // if onBackPressed is true then the fragment overrides the Activity's onBackPressed
            if (((BackPressedInterface) fragment).onBackPressed()) {
                return;
            }
        }

        if (getFragmentManager().getBackStackEntryCount() == 1) {
            getFragmentManager().popBackStack();
            super.onBackPressed();
        } else if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private Fragment getActiveFragment(){
        FragmentManager fragmentManager = this.getFragmentManager();
        if(fragmentManager.getBackStackEntryCount() > 0){
            return fragmentManager
                    .findFragmentByTag(
                            fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1)
                    .getName());
        }
        return null;
    }
}
