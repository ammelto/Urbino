package io.seamoss.urbino.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import butterknife.Unbinder;

/**
 * Created by Alexander Melton on 2/13/2017.
 */

public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder = Unbinder.EMPTY;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    protected BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }
}
