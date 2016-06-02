package com.appsorama.blendtest.controller.home.impl;

import com.appsorama.blendtest.BlendApplication;
import com.appsorama.blendtest.R;
import com.appsorama.blendtest.controller.home.constructors.HomePresenter;
import com.appsorama.blendtest.controller.home.listener.OnRequestItemsListener;
import com.appsorama.blendtest.controller.home.constructors.HomeView;

/**
 * Created by Jose Torres in FreakyByte on 01/06/16.
 */
public class HomePresenterImpl implements HomePresenter, OnRequestItemsListener {

    private HomeView mHomeView;
    private HomeInteractorImpl mHomeInteractor;

    public HomePresenterImpl(HomeView homeView) {
        mHomeView = homeView;
        mHomeInteractor = new HomeInteractorImpl();
    }

    @Override
    public void getItems() {
        if (mHomeView == null)
            return;

        mHomeView.showLoader(BlendApplication.getInstance().getString(R.string.msg_downloading), false);
        mHomeInteractor.getItemsFromServer(this);
    }

    @Override
    public void onRefreshFoodList() {
        if (mHomeView == null)
            return;

        mHomeInteractor.getItemsFromServer(this);
    }


    @Override
    public void onDestroy() {
        mHomeView = null;
        mHomeInteractor = null;
    }

    @Override
    public void onRequestFailed() {
        mHomeView.refreshAdapter();
    }

    @Override
    public void onRequestSuccess() {
        mHomeView.fillAdapter();
    }

    @Override
    public void onRequestFinished() {
        mHomeView.hideLoader();
    }
}
