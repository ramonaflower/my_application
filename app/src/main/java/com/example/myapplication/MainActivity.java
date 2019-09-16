package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.view_pager_profile_patient)
    ViewPager mViewPagerProfilePatient;
    @BindView(R.id.tab_layout_title)
    TabLayout mTabLayoutTitle;
    private List<Fragment> mListFragment = new ArrayList<>();

    private ProfileViewPagerAdapter mViewpagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_patient);
        ButterKnife.bind(this);
        setupView();
    }

    private void setupView() {
        mListFragment.clear();
        mListFragment.add(new ProfileFragment());
        mListFragment.add(new ProfileFragment());
        mListFragment.add(new ProfileFragment());
        mViewpagerAdapter = new ProfileViewPagerAdapter(getSupportFragmentManager(), this, mListFragment);
        mViewPagerProfilePatient.setAdapter(mViewpagerAdapter);
        mTabLayoutTitle.setupWithViewPager(mViewPagerProfilePatient);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == Constants.REQUEST_CODE_CAPTURE_IMAGE)
          //  mListFragment.get(0).onActivityResult(requestCode, resultCode, data);
    }
}
