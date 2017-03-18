package io.seamoss.urbino.views.node;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import io.seamoss.urbino.views.node.tasks.TaskFragment;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class NodePageAdapter extends FragmentPagerAdapter {

    public NodePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        return new TaskFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Partitive";
            case 1:
                return "Quotitive";
            default:
                return "Tab " + position;
        }
    }

}
