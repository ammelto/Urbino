package io.seamoss.urbino.views.public_boards_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import io.seamoss.urbino.data.models.Subject;
import io.seamoss.urbino.views.public_boards_list.PublicBoardsFragment.PublicBoardsFragment;

/**
 * Created by Alexander Melton on 3/2/2017.
 */

public class PublicBoardsPagerAdapter extends FragmentStatePagerAdapter {

    private List<Subject> subjects;

    public PublicBoardsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        PublicBoardsFragment publicBoardsFragment = new PublicBoardsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("subject", subjects.get(position).getName());
        publicBoardsFragment.setArguments(bundle);
        return publicBoardsFragment;
    }

    @Override
    public int getCount() {
        if(subjects == null) return 0;
        else return subjects.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(subjects == null) return "";
        else return subjects.get(position).getName();
    }

    public void setSubjects(List<Subject> subjects){
        this.subjects = subjects;
    }
}
