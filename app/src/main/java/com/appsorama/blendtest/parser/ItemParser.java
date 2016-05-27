package com.appsorama.blendtest.parser;

import com.appsorama.blendtest.model.FoodModel;
import com.appsorama.blendtest.util.DebugUtils;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose Torres in Apps-O-Rama on 19/04/16.
 */
public class ItemParser extends MainParser {
    public static final String TAG = "ItemParser";

    public static ArrayList<FoodModel> parseListItems(String sResponse) {
        startParsed();

        ArrayList<FoodModel> mFruitList = null;

        try {
            JSONObject jsonInit = new JSONObject(sResponse);
            mFruitList = mGson.fromJson(jsonInit.getString("items"), new TypeToken<List<FoodModel>>() {
            }.getType());

        } catch (Exception ex) {
            DebugUtils.logError(TAG, ex);
        }

        stopParsed();

        DebugUtils.logDebug(TAG, "Parsed Fruit Items:: " + mFruitList.size());

        return mFruitList == null ? new ArrayList<FoodModel>() : mFruitList;
    }

}
