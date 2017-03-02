package io.seamoss.urbino.views.public_boards_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.seamoss.urbino.views.public_boards_list.PublicBoardsFragment.PublicBoardsFragment;

/**
 * Created by Alexander Melton on 3/2/2017.
 */

public class PublicBoardsPagerAdapter extends FragmentStatePagerAdapter {
    public PublicBoardsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position % 4) {
            //case 0:
            //    return RecyclerViewFragment.newInstance();
            //case 1:
            //    return RecyclerViewFragment.newInstance();
            //case 2:
            //    return WebViewFragment.newInstance();
            default:
                return new PublicBoardsFragment();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Mathematics";
            case 1:
                return "English";
            case 2:
                return "History";
            case 3:
                return "Engineering";
            case 4:
                return "Literature";
            case 5:
                return "Misc";
        }
        return "";
    }
}
