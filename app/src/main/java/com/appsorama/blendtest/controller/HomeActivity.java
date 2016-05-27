package com.appsorama.blendtest.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsorama.blendtest.R;
import com.appsorama.blendtest.controller.adapter.FoodListAdapter;
import com.appsorama.blendtest.db.FoodDAO;
import com.appsorama.blendtest.dialog.ProgressDialog;
import com.appsorama.blendtest.listener.RecyclerListListener;
import com.appsorama.blendtest.model.FoodModel;
import com.appsorama.blendtest.parser.ItemParser;
import com.appsorama.blendtest.util.DebugUtils;
import com.appsorama.blendtest.util.WidgetUtils;
import com.appsorama.blendtest.web.BlendApi;
import com.appsorama.blendtest.web.BlendRestClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jose Torres in Apps-O-Rama on 19/04/16.
 */
public class HomeActivity extends MainActivity implements RecyclerListListener, View.OnClickListener {
    public static final String TAG = "HomeActivity";

    private ArrayList<FoodModel> aFoodList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ImageView imgToolbarMenuItemCategory;
    private TextView txtEmptyView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressDialog mLoaderDialog;
    private Toolbar mToolbar;
    private FoodListAdapter mAdapter;
    private FoodDAO mFoodDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setSupportActionBar(getToolbar());

        getMenuItemCategories().setOnClickListener(HomeActivity.this);

        mFoodDao = new FoodDAO();

        getSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getItems();
            }
        });

        getListFood().setAdapter(getListAdapter());

        showLoader(getString(R.string.msg_downloading), false);
        getItems();
    }

    private void getItems() {

        BlendRestClient.get(BlendApi.geItemsUrl(), new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                DebugUtils.logError(TAG, "getItems:: " + responseString);

                switch (statusCode) {
                    case 0:
                        WidgetUtils.createShortToast(getString(R.string.error_no_network));
                        break;
                    default:
                        WidgetUtils.createShortToast("Status Code:: " + statusCode + " Response:: " + responseString);
                        break;
                }
                if (aFoodList.isEmpty()) {
                    aFoodList = mFoodDao.getAllFruits();
                    refreshFoodList();
                }

                onFinish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                aFoodList = ItemParser.parseListItems(responseString);

                mFoodDao.clearAllData();
                mFoodDao.insertAllFruits(aFoodList);

                refreshFoodList();

                onFinish();
            }

            @Override
            public void onFinish() {

                hideLoader();
            }
        });
    }


    private void refreshFoodList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getListAdapter().aListFood.clear();
                getListAdapter().aListFood.addAll(aFoodList);
                getTxtEmptyView().setVisibility(getListAdapter().aListFood.isEmpty() ? View.VISIBLE : View.GONE);
                getListFood().setVisibility(getListAdapter().aListFood.isEmpty() ? View.GONE : View.VISIBLE);
                getListAdapter().notifyDataSetChanged();
            }
        });
    }

    private void showLoader(final String sMessage, final boolean bCancelable) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mLoaderDialog == null || !mLoaderDialog.isShowing()) {
                    mLoaderDialog = new ProgressDialog(HomeActivity.this, sMessage);
                    mLoaderDialog.setCancelable(bCancelable);
                    mLoaderDialog.show();
                }
            }
        });
    }

    private void hideLoader() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getSwipeRefreshLayout().setRefreshing(false);
                if (mLoaderDialog != null)
                    mLoaderDialog.dismiss();
            }
        });
    }

    @Override
    public void OnItemClickListener(int iPosition) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_menu_item_category:
                Intent mIntentFoodCategory = new Intent(HomeActivity.this, FoodCategoryActivity.class);
                startActivity(mIntentFoodCategory);
                break;
            default:
                DebugUtils.logError(TAG, "OnClick:: view not handled " + v.getId());
                break;
        }
    }


    private Toolbar getToolbar() {
        if (mToolbar == null)
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        return mToolbar;
    }

    private TextView getTxtEmptyView() {
        if (txtEmptyView == null)
            txtEmptyView = (TextView) findViewById(R.id.txt_empty_list);
        return txtEmptyView;
    }

    private ImageView getMenuItemCategories() {
        if (imgToolbarMenuItemCategory == null)
            imgToolbarMenuItemCategory = (ImageView) findViewById(R.id.toolbar_menu_item_category);
        return imgToolbarMenuItemCategory;
    }

    private SwipeRefreshLayout getSwipeRefreshLayout() {
        if (swipeRefreshLayout == null)
            swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        return swipeRefreshLayout;
    }

    private RecyclerView getListFood() {
        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) findViewById(R.id.list_food);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        }
        return mRecyclerView;
    }

    private FoodListAdapter getListAdapter() {
        if (mAdapter == null)
            mAdapter = new FoodListAdapter(HomeActivity.this, HomeActivity.this);
        return mAdapter;
    }
}
