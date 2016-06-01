package com.appsorama.blendtest.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.appsorama.blendtest.model.FoodModel;
import com.appsorama.blendtest.model.StockModel;

/**
 * Created by Jose Torres in FreakyByte on 19/04/16.
 */
public class StockInfoDAO extends MainDAO {
    public final static String TAG = "StockInfoDAO";

    public final static String TABLE_NAME = "table_stock";
    public final static String ID_PK = "stock_pk";

    public static final String QUERY_TABLE = "" + "CREATE TABLE " + TABLE_NAME
            + " (" + ID_PK + " INTEGER PRIMARY KEY autoincrement, " + TAG_RECEIVED + " TEXT, " + TAG_QUANTITY + " INTEGER, " + TAG_NOTES + " TEXT, " + TAG_FRUIT_ID + " REAL);";

    public final static String[] FIELDS = {ID_PK, TAG_RECEIVED, TAG_QUANTITY, TAG_NOTES, TAG_FRUIT_ID};

    public static boolean insertStock(long id, FoodModel model, DBAdapter mAdapter) {
        long idInsert = mAdapter.insert(TABLE_NAME, getContentValues(model.getStock_info(), id));
        // DebugUtils.logDebug(TAG, "Inserted Stock:: " + (idInsert != -1));
        return idInsert != -1;
    }

    public static StockModel getFruitStock(DBAdapter mAdapter, long id) {
        StockModel mStock = new StockModel();

        Cursor cursor = mAdapter.getData(TABLE_NAME, FIELDS, TAG_FRUIT_ID + " = " + id, null, null);

        int _received = cursor.getColumnIndex(TAG_RECEIVED);
        int _quantity = cursor.getColumnIndex(TAG_QUANTITY);
        int _notes = cursor.getColumnIndex(TAG_NOTES);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            mStock.setReceived(cursor.getString(_received));
            mStock.setQuantity(cursor.getInt(_quantity));
            mStock.setNotes(cursor.getString(_notes));
            cursor.moveToNext();
        }

        cursor.close();

        return mStock;
    }

    public void clearAllData() {
        dbAdapter.deleteAll(TABLE_NAME);
    }

    private static ContentValues getContentValues(StockModel mModel, long id) {
        ContentValues cValues = new ContentValues();
        cValues.put(TAG_RECEIVED, mModel.getReceived());
        cValues.put(TAG_QUANTITY, mModel.getQuantity());
        cValues.put(TAG_NOTES, mModel.getNotes());
        cValues.put(TAG_FRUIT_ID, id);
        return cValues;
    }
}
