package com.appsorama.blendtest.controller.home.listener;

import com.appsorama.blendtest.model.FoodModel;

import java.util.ArrayList;

/**
 * Created by Jose Torres in FreakyByte on 01/06/16.
 */
public interface OnRequestItemsListener {

    void onRequestFailed();

    void onRequestSuccess();

    void onRequestFinished();
}
