package com.appsorama.blendtest.web;

import android.os.Looper;

import com.appsorama.blendtest.util.DebugUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

/**
 * Created by Jose Torres in Apps-O-Rama on 19/04/16.
 */
public class BlendRestClient {
    static final String TAG = "FortuneCatRestClient";

    public static AsyncHttpClient syncHttpClient = new SyncHttpClient();
    public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    private static AsyncHttpClient getClient() {
        if (Looper.myLooper() == null)
            return syncHttpClient;
        return asyncHttpClient;
    }

    public static void get(String sUrl, AsyncHttpResponseHandler responseHandler) {
        DebugUtils.logDebug(TAG, "[GET] " + sUrl);
        getClient().removeAllHeaders();
        getClient().addHeader("X-Auth-Token", "token");
        getClient().get(sUrl, responseHandler);
    }

}
