package com.appsorama.blendtest.db;

import com.appsorama.blendtest.util.DebugUtils;

/**
 * Created by Jose Torres in Apps-O-Rama on 19/04/16.
 */
public class MainDAO {

    protected DBAdapter dbAdapter;
    protected boolean success = true;
    protected String conditional = "";
    protected boolean empty;
    protected static long init, now;


    public MainDAO() {
        dbAdapter = new DBAdapter();
    }

    public static void startQuery() {
        init = System.currentTimeMillis();
    }

    public static void stopQuery() {
        now = System.currentTimeMillis();
        DebugUtils.logDebug("Finished Query in :: " + (now - init) + " ms");
    }

    public static final String TAG_NAME = "name";
    public static final String TAG_CATEGORY = "category";
    public static final String TAG_RECEIVED = "received";
    public static final String TAG_QUANTITY = "quantity";
    public static final String TAG_NOTES = "notes";
    public static final String TAG_FRUIT_ID = "fruit_id";
}
