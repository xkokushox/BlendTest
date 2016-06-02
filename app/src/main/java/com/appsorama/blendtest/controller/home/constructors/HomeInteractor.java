package com.appsorama.blendtest.controller.home.constructors;

import com.appsorama.blendtest.controller.home.listener.OnRequestItemsListener;

/**
 * Created by Jose Torres in FreakyByte on 01/06/16.
 */
public interface HomeInteractor {

    void getItemsFromServer(OnRequestItemsListener mListener);
}
