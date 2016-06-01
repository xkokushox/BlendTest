package com.appsorama.blendtest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.appsorama.blendtest.BlendApplication;
import com.appsorama.blendtest.util.DebugUtils;

/**
 * Created by Jose Torres in FreakyByte on 19/04/16.
 */
public class DBAdapter {
    public static final String TAG = "DBAdapter";

    private static final String MDBNAME = "BlendTest.db";
    private static int mDbVersion = 1;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public DBAdapter() {
    }

    public static void deleteDatabase(final Context context) {
        context.deleteDatabase(MDBNAME);
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     * initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public DBAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper();
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Close the opened Data Base.
     */
    public void close() {
        mDbHelper.close();
    }

    public void begginTransaction() {
        open();
        mDb.beginTransaction();
    }

    public void setTransacctionSuccesfull() {
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        close();
    }

    /**
     * Insert a new record into the specified table
     *
     * @param table   - The name of the table.
     * @param cValues - The values to set.
     * @return
     */
    public long insert(String table, ContentValues cValues) {
        long isInserted = -1;

        try {
            isInserted = mDb.insert(table, null, cValues);
        } catch (Exception e) {
            setTransacctionSuccesfull();
            DebugUtils.logError("DbAdapter.insert", e.toString());
        }
        return isInserted;
    }

    /**
     * Update a record from the specified table
     *
     * @param table   - The name of the table.
     * @param cValues - The values to set.
     * @return
     */
    public long update(String table, ContentValues cValues, String whereClause, String[] whereArgs) {
        long isUpdated = -1;
        try {
            isUpdated = mDb.update(table, cValues, whereClause, whereArgs);
        } catch (Exception e) {
            setTransacctionSuccesfull();
            DebugUtils.logError("DbAdapter.update", e.toString());
        }
        return isUpdated;
    }

    /**
     * Delete an entry from database
     *
     * @param table     - The name of the table.
     * @param condition - sql delete condition
     * @return Return 1 if was successful else returns -1.
     */
    public int delete(String table, String condition, String[] args) {
        try {
            mDb.delete(table, condition, args);
            return 1;
        } catch (Exception e) {
            DebugUtils.logError("DbAdapter.delete", e.toString());
            return -1;
        }
    }

    public int execSql(String sqlStatement, String[] whereArgs) {
        try {
            open();
            mDb.execSQL(sqlStatement, whereArgs);
            close();
            return 1;
        } catch (Exception e) {
            close();
            DebugUtils.logError("DbAdapter.delete", e.toString());
            return -1;
        }
    }

    /**
     * Delete all records in the specified table.
     *
     * @param table - The table to delete records.
     * @return 1 if the delete was successfull, otherwise -1.
     */
    public int deleteAll(String table) {
        try {
            open();
            mDb.delete(table, null, null);
            close();
            return 1;
        } catch (Exception e) {
            close();
            DebugUtils.logError("DbAdapter.deleteAll", e.toString());
            return -1;
        }
    }

    /**
     * Make a query to the local Data Base.
     *
     * @param tables      - The table to request data.
     * @param columns     - The columns to obtain data.
     * @param conditional - The conditional(WHERE clause). Put this on NULL if there is
     *                    no conditional.
     * @return A cursor with the data obtained.
     */
    public Cursor getData(String tables, String[] columns, String conditional) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(tables);
        return queryBuilder.query(mDb, columns, conditional, null, null, null, null);
    }

    public Cursor getData(String tables, String[] columns, String conditional, String orderBy) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(tables);

        return queryBuilder.query(mDb, columns, conditional, null, null, null, orderBy);
    }

    public Cursor getData(String tables, String[] columns, String conditional,
                          String groupBy, String orderBy) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(tables);

        return queryBuilder.query(mDb, columns, conditional, null, groupBy, null, orderBy);
    }

    public Cursor getData(boolean distinct, String tables, String[] columns, String conditional, String groupBy, String orderBy) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(tables);
        queryBuilder.setDistinct(distinct);

        return queryBuilder.query(mDb, columns, conditional, null, groupBy, null, orderBy);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper() {
            super(BlendApplication.getInstance(), MDBNAME, null, mDbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(FoodDAO.QUERY_TABLE);
            db.execSQL(StockInfoDAO.QUERY_TABLE);
            DebugUtils.logDebug(TAG, "Database Create");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + FoodDAO.QUERY_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + StockInfoDAO.QUERY_TABLE);

        }
    }

}
