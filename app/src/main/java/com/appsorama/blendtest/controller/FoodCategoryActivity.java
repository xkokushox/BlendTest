package com.appsorama.blendtest.controller;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.appsorama.blendtest.R;
import com.appsorama.blendtest.controller.adapter.ScreenSlideFoodAdapter;
import com.appsorama.blendtest.controller.fragment.ScreenSlideFoodFragment;
import com.appsorama.blendtest.db.FoodDAO;

import java.util.ArrayList;

/**
 * Created by Jose Torres on 4/20/16.
 */
public class FoodCategoryActivity extends MainActivity {
    public static final String TAG = "FoodCategoryActivity";

    private Toolbar mToolbar;
    private ViewPager mPager;
    private ScreenSlideFoodAdapter mPagerAdapter;
    private TabLayout tabCategories;

    private FoodDAO mFoodDao;
    private ArrayList<String> aCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);

        setSupportActionBar(getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFoodDao = new FoodDAO();
        aCategories = mFoodDao.getCategories();

        getToolbar().setNavigationIcon(getResources().getDrawable(R.drawable.vector_navigation_back));
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getPager().setAdapter(getPagerAdapter());
        getPager().setPageTransformer(false, new CubeOutTransformer());
        getTabCategories().setupWithViewPager(getPager());

    }

    private Toolbar getToolbar() {
        if (mToolbar == null)
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        return mToolbar;
    }

    private ViewPager getPager() {
        if (mPager == null)
            mPager = (ViewPager) findViewById(R.id.pager_category);
        return mPager;
    }

    private TabLayout getTabCategories() {
        if (tabCategories == null)
            tabCategories = (TabLayout) findViewById(R.id.tab_categories);
        return tabCategories;
    }

    private ScreenSlideFoodAdapter getPagerAdapter() {
        if (mPagerAdapter == null) {
            mPagerAdapter = new ScreenSlideFoodAdapter(getSupportFragmentManager());
            for (String sTitle : aCategories) {
                Bundle bundle = new Bundle();
                ScreenSlideFoodFragment mCategoryFragment = new ScreenSlideFoodFragment();
                bundle.putString(ScreenSlideFoodFragment.TAG_CATEGORY, sTitle);
                mCategoryFragment.setArguments(bundle);
                mPagerAdapter.addFragment(mCategoryFragment, sTitle);
            }

        }
        return mPagerAdapter;
    }


}
