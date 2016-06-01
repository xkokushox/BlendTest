package com.appsorama.blendtest.parser;

import com.appsorama.blendtest.util.DebugUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Jose Torres in FreakyByte on 19/04/16.
 */
public class MainParser {
    protected static Gson mGson = new Gson();
    protected static long init, now;


    public static void startParsed() {
        init = System.currentTimeMillis();
    }

    public static void stopParsed() {
        now = System.currentTimeMillis();
        DebugUtils.logDebug("Finished parse in :: " + (now - init) + " ms");
    }

    public static boolean parseSection(JSONObject mJson, String sTag) {
        try {
            if (mJson == null || !mJson.has(sTag) || mJson.getString(sTag) == null || mJson.getString(sTag).equals("null") || mJson.getString(sTag).isEmpty())
                return false;
            else
                return true;
        } catch (Exception ex) {
            DebugUtils.logError(sTag + " : " + ex.toString());
            return false;
        }
    }

}
