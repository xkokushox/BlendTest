package com.appsorama.blendtest.controller.home.impl;

import com.appsorama.blendtest.BlendApplication;
import com.appsorama.blendtest.R;
import com.appsorama.blendtest.controller.home.constructors.HomeInteractor;
import com.appsorama.blendtest.controller.home.listener.OnRequestItemsListener;
import com.appsorama.blendtest.db.FoodDAO;
import com.appsorama.blendtest.parser.ItemParser;
import com.appsorama.blendtest.util.DebugUtils;
import com.appsorama.blendtest.util.WidgetUtils;
import com.appsorama.blendtest.web.BlendApi;
import com.appsorama.blendtest.web.BlendRestClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jose Torres in FreakyByte on 01/06/16.
 */
public class HomeInteractorImpl implements HomeInteractor {

    public static final String TAG = "HomeInteractorImpl";

    @Override
    public void getItemsFromServer(final OnRequestItemsListener mListener) {


        BlendRestClient.get(BlendApi.geItemsUrl(), new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                DebugUtils.logError(TAG, "getItems:: " + responseString);

                switch (statusCode) {
                    case 0:
                        WidgetUtils.createShortToast(BlendApplication.getInstance().getString(R.string.error_no_network));
                        break;
                    default:
                        // WidgetUtils.createShortToast("Status Code:: " + statusCode + " Response:: " + responseString);
                        break;
                }

                mListener.onRequestFailed();

                onFinish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                FoodDAO mFoodDAo = new FoodDAO();
                mFoodDAo.clearAllData();
                mFoodDAo.insertAllFruits(ItemParser.parseListItems(responseString));

                mListener.onRequestSuccess();

                onFinish();
            }

            @Override
            public void onFinish() {
                mListener.onRequestFinished();
            }
        });
    }
}
