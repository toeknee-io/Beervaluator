package com.loosefang.beerfinder.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tony on 12/31/2014.
 */
public class BeersDBOpenHelper extends SQLiteOpenHelper {

    private static final String LOGTAG = "BEERVALUATOR";

    private static final String DATABASE_NAME = "beers.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_BEERS = "beers";
    public static final String COLUMN_ID = "beerId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_STORE = "store";
    public static final String COLUMN_ALC_PER = "percentage";
    public static final String COLUMN_ALC_QTY = "qty";
    public static final String COLUMN_ALC_PRICE = "price";
    public static final String COLUMN_ALC_DESC = "description";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_IMAGE = "image";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_BEERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_STORE + " TEXT, " +
                    COLUMN_ALC_PER + " NUMERIC, " +
                    COLUMN_ALC_QTY + " NUMERIC, " +
                    COLUMN_ALC_PRICE + " NUMERIC, " +
                    COLUMN_ALC_DESC + " TEXT, " +
                    COLUMN_RATING + " NUMERIC, " +
                    COLUMN_IMAGE + " TEXT " +
                    ")";
    
    public BeersDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG, "Table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + TABLE_BEERS);
        onCreate(db);

    }
}
