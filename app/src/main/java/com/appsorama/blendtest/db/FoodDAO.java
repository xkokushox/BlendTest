package com.appsorama.blendtest.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.appsorama.blendtest.model.FoodModel;
import com.appsorama.blendtest.util.DebugUtils;

import java.util.ArrayList;

/**
 * Created by Jose Torres in FreakyByte on 19/04/16.
 */
public class FoodDAO extends MainDAO {
    public final static String TAG = "FoodDAO";

    public final static String TABLE_NAME = "table_fruit";
    public final static String ID_PK = "fruit_pk";

    public static final String QUERY_TABLE = "" + "CREATE TABLE " + TABLE_NAME
            + " (" + ID_PK + " INTEGER PRIMARY KEY autoincrement, " + TAG_NAME + " TEXT, " + TAG_CATEGORY + " TEXT);";

    public final static String[] FIELDS = {ID_PK, TAG_NAME, TAG_CATEGORY};


    public void insertAllFruits(ArrayList<FoodModel> aFruit) {
        dbAdapter.begginTransaction();
        for (FoodModel mModel : aFruit)
            if (!insertFruit(mModel, false))
                DebugUtils.logError("It was an error inserting fruits :: " + mModel.getName());
        // else
        //    DebugUtils.logDebug("Fruit :: " + mModel.getName());

        dbAdapter.setTransacctionSuccesfull();
    }

    public boolean insertFruit(FoodModel model, boolean openDatabase) {
        if (openDatabase)
            dbAdapter.begginTransaction();

        long idInsert = dbAdapter.insert(TABLE_NAME, getContentValues(model));
        success = idInsert != -1;

        if (success)
            StockInfoDAO.insertStock(idInsert, model, dbAdapter);

        if (openDatabase)
            dbAdapter.setTransacctionSuccesfull();

        return success;
    }

    public ArrayList<FoodModel> getAllFruits() {
        startQuery();

        ArrayList<FoodModel> aFruits = new ArrayList<>();

        dbAdapter.begginTransaction();

        Cursor cursor = dbAdapter.getData(TABLE_NAME, FIELDS, conditional, null, null);

        int _id = cursor.getColumnIndex(ID_PK);
        int _name = cursor.getColumnIndex(TAG_NAME);
        int _category = cursor.getColumnIndex(TAG_CATEGORY);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            FoodModel mFruit = new FoodModel();
            mFruit.setName(cursor.getString(_name));
            mFruit.setCategory(cursor.getString(_category));
            mFruit.setStock_info(StockInfoDAO.getFruitStock(dbAdapter, cursor.getLong(_id)));
            //  DebugUtils.logDebug(TAG, "Fruit:: " + mFruit.getName() + " Stock:: " + mFruit.getStock_info().getNotes());
            aFruits.add(mFruit);
            cursor.moveToNext();
        }

        cursor.close();
        dbAdapter.setTransacctionSuccesfull();
        stopQuery();

        return aFruits;
    }

    public ArrayList<FoodModel> getAllFruits(String sCategory) {
        startQuery();

        ArrayList<FoodModel> aFruits = new ArrayList<>();

        conditional = TAG_CATEGORY + " = '" + sCategory + "'";

        dbAdapter.begginTransaction();

        Cursor cursor = dbAdapter.getData(TABLE_NAME, FIELDS, conditional, null, null);

        int _id = cursor.getColumnIndex(ID_PK);
        int _name = cursor.getColumnIndex(TAG_NAME);
        int _category = cursor.getColumnIndex(TAG_CATEGORY);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            FoodModel mFruit = new FoodModel();
            mFruit.setName(cursor.getString(_name));
            mFruit.setCategory(cursor.getString(_category));
            mFruit.setStock_info(StockInfoDAO.getFruitStock(dbAdapter, cursor.getLong(_id)));
            aFruits.add(mFruit);
            cursor.moveToNext();
        }

        cursor.close();
        dbAdapter.setTransacctionSuccesfull();
        stopQuery();

        return aFruits;
    }

    public boolean isEmpty() {
        empty = false;
        conditional = "";

        dbAdapter.begginTransaction();

        Cursor cursor = dbAdapter.getData(TABLE_NAME, new String[]{ID_PK}, conditional, null, null);
        cursor.moveToFirst();

        empty = cursor.getCount() == 0;

        cursor.close();
        dbAdapter.setTransacctionSuccesfull();

        return empty;
    }

    public ArrayList<String> getCategories() {
        conditional = "";
        ArrayList<String> aCategories = new ArrayList<>();

        dbAdapter.begginTransaction();

        Cursor cursor = dbAdapter.getData(TABLE_NAME, FIELDS, conditional, TAG_CATEGORY, null);

        int _category = cursor.getColumnIndex(TAG_CATEGORY);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            aCategories.add(cursor.getString(_category));
            cursor.moveToNext();
        }

        cursor.close();
        dbAdapter.setTransacctionSuccesfull();

        DebugUtils.logDebug(TAG, "Categories Qty:: " + aCategories.size());

        return aCategories;
    }

    public void clearAllData() {
        dbAdapter.deleteAll(TABLE_NAME);
        dbAdapter.deleteAll(StockInfoDAO.TABLE_NAME);
    }

    private ContentValues getContentValues(FoodModel mModel) {
        ContentValues cValues = new ContentValues();
        cValues.put(TAG_NAME, mModel.getName());
        cValues.put(TAG_CATEGORY, mModel.getCategory());
        return cValues;
    }

}
