package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ProfileViewPagerAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private List<Fragment> mListFragment;

    public ProfileViewPagerAdapter(@NonNull FragmentManager fm, Context context, List<Fragment> listFragment) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        mListFragment = listFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mListFragment.get(0);
            case 1:
                return mListFragment.get(1);
            case 2:
                return mListFragment.get(2);
            default:
                break;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.profile_viewpager_title_1);
            case 1:
                return mContext.getString(R.string.profile_viewpager_title_2);
            case 2:
                return mContext.getString(R.string.profile_viewpager_title_3);
            default:
                return null;
        }
    }
}
