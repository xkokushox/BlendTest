package com.appsorama.blendtest.web;

import com.appsorama.blendtest.BlendApplication;
import com.appsorama.blendtest.R;

/**
 * Created by Jose Torres in Apps-O-Rama on 19/04/16.
 */
public class BlendApi {

    public static final String END_POINT_ITEMS = "/items.json";

    public static String geItemsUrl() {
        return BlendApplication.getInstance().getString(R.string.url_base) + END_POINT_ITEMS;
    }
}
