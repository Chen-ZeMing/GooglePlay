package com.example.czlgh.googleplay74.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;

import com.example.czlgh.googleplay74.R;
import com.example.czlgh.googleplay74.ui.fragment.BaseFragment;
import com.example.czlgh.googleplay74.ui.fragment.FragmentFactory;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends AppCompatActivity {


    private ViewPager mViewpager;
    private TabPageIndicator mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        mViewpager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.indicator);

        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());


        mViewpager.setAdapter(myAdapter);
        mTabLayout.setViewPager(mViewpager);

        mTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BaseFragment fragment=FragmentFactory.createFragment(position);
                fragment.loadData();
                Log.d("MainActivity", "onPageSelected: sfdf");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    class MyAdapter extends FragmentPagerAdapter {

        //一开始进入app时,每一个选项卡里都填充着mloadingpager,什么都没干,因为状态没有更新，


        private final String[] mTabNames;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mTabNames = new String[]{"首页","应用","游戏","专题","推荐","分类","排行"};

        }
////什么都没做的时候 就会出现fragment，并且都是失败的状态，
        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment= FragmentFactory.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }
    }
}
