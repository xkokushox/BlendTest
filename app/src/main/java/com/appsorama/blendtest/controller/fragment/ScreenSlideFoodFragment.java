package com.appsorama.blendtest.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsorama.blendtest.R;
import com.appsorama.blendtest.controller.adapter.FoodListAdapter;
import com.appsorama.blendtest.db.FoodDAO;
import com.appsorama.blendtest.listener.RecyclerListListener;
import com.appsorama.blendtest.model.FoodModel;
import com.appsorama.blendtest.util.DebugUtils;

import java.util.ArrayList;

/**
 * Created by Jose Torres in FreakyByte on 4/20/16.
 */
public class ScreenSlideFoodFragment extends Fragment implements RecyclerListListener {
    public static final String TAG = "ScreenSlideFoodFragment";
    public static final String TAG_CATEGORY = "FoodCategory";

    private ViewGroup rootView;
    private RecyclerView mRecyclerView;
    private TextView txtEmptyView;
    private FoodListAdapter mAdapter = null;
    private ArrayList<FoodModel> aFoodList;
    private String sCategory = "";
    private FoodDAO mFoodDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DebugUtils.logDebug(TAG, "OnCreate:: " + sCategory);
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_food, container, false);

        Bundle bundle = this.getArguments();
        mFoodDao = new FoodDAO();
        mRecyclerView = null;
        mRecyclerView = null;

        if (bundle != null)
            sCategory = getArguments().getString(TAG_CATEGORY);

        aFoodList = mFoodDao.getAllFruits(sCategory);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getListFood().setAdapter(getListAdapter());
        getListFood().setHasFixedSize(true);
        getListFood().setLayoutManager(mLinearLayoutManager);

        getListAdapter().getListFood().clear();
        getListAdapter().getListFood().addAll(aFoodList);

        getTxtEmptyView().setVisibility(getListAdapter().getListFood().isEmpty() ? View.VISIBLE : View.GONE);
        getListFood().setVisibility(getListAdapter().getListFood().isEmpty() ? View.GONE : View.VISIBLE);

        return rootView;
    }

    @Override
    public void OnItemClickListener(int iPosition) {

    }

    private RecyclerView getListFood() {
        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_food);
        }
        return mRecyclerView;
    }

    private TextView getTxtEmptyView() {
        if (txtEmptyView == null)
            txtEmptyView = (TextView) rootView.findViewById(R.id.txt_empty_list);
        return txtEmptyView;
    }

    private FoodListAdapter getListAdapter() {
        if (mAdapter == null)
            mAdapter = new FoodListAdapter(getActivity(), ScreenSlideFoodFragment.this);
        return mAdapter;
    }
}