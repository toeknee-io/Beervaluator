package com.loosefang.beerfinder.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 12/31/2014.
 */
public class BeersDataSource {

    private static final String LOGTAG = "BEERVALUATOR";

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            BeersDBOpenHelper.COLUMN_NAME,
            BeersDBOpenHelper.COLUMN_STORE,
            BeersDBOpenHelper.COLUMN_ALC_PER,
            BeersDBOpenHelper.COLUMN_ALC_QTY,
            BeersDBOpenHelper.COLUMN_ALC_PRICE,
            BeersDBOpenHelper.COLUMN_ALC_DESC,
            BeersDBOpenHelper.COLUMN_RATING
    };

    public BeersDataSource(Context context) {

        dbhelper = new BeersDBOpenHelper(context);

    }

    public void open(){

        Log.i(LOGTAG, "Database opened");
        database = dbhelper.getWritableDatabase();
    }

    public void closed(){

        Log.i(LOGTAG, "Database closed");
        dbhelper.close();
    }

    public Beer create(Beer beer) {

        ContentValues values = new ContentValues();
        values.put(BeersDBOpenHelper.COLUMN_NAME, beer.getName());
        values.put(BeersDBOpenHelper.COLUMN_STORE, beer.getStore());
        values.put(BeersDBOpenHelper.COLUMN_ALC_PER, beer.getAlcPct());
        values.put(BeersDBOpenHelper.COLUMN_ALC_QTY, beer.getAlcQty());
        values.put(BeersDBOpenHelper.COLUMN_ALC_PRICE, beer.getPrice());
        values.put(BeersDBOpenHelper.COLUMN_RATING, beer.getRating());

        long insertid = database.insert(BeersDBOpenHelper.TABLE_BEERS, null, values);
        beer.setBeerId(insertid);

        return beer;
    }

    public List<Beer> findAll () {

        Cursor cursor = database.query(BeersDBOpenHelper.TABLE_BEERS, allColumns,
                null, null, null, null, null);

        Log.i(LOGTAG, "Returned: " + cursor.getCount() + " rows");

        List<Beer> beers = cursorToList(cursor);

        return beers;
    }
    public List<Beer> findFiltered (String selection, String orderBy) {

    String[] whereArgs = new String[]{"?"};

    Cursor cursor = database.query(BeersDBOpenHelper.TABLE_BEERS, allColumns,
            selection, null, null, null, orderBy);

    Log.i(LOGTAG + " Query", "Returned: " + cursor.getCount() + " rows ");

    List<Beer> beers = cursorToList(cursor);
    return beers;
}

    private List<Beer> cursorToList(Cursor cursor) {
        List<Beer> beers = new ArrayList<Beer>();
        if (cursor.getCount() > 0){
            while(cursor.moveToNext()) {
                Beer beer = new Beer();
                beer.setBeerId(cursor.getColumnIndex(BeersDBOpenHelper.COLUMN_ID));
                beer.setName(cursor.getString(cursor.getColumnIndex(BeersDBOpenHelper.COLUMN_NAME)));
                Log.i(LOGTAG, beer.getName());
                beer.setStore(cursor.getString(cursor.getColumnIndex(BeersDBOpenHelper.COLUMN_STORE)));
                beer.setAlcPct(cursor.getDouble(cursor.getColumnIndex(BeersDBOpenHelper.COLUMN_ALC_PER)));
                beer.setAlcQty(cursor.getDouble(cursor.getColumnIndex(BeersDBOpenHelper.COLUMN_ALC_QTY)));
                beer.setPrice(cursor.getDouble(cursor.getColumnIndex(BeersDBOpenHelper.COLUMN_ALC_PRICE)));
                beer.setRating(cursor.getDouble(cursor.getColumnIndex(BeersDBOpenHelper.COLUMN_RATING)));
                beers.add(beer);
            }
        }
        return beers;
    }

    public void clearDb() {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete(BeersDBOpenHelper.TABLE_BEERS, null, null);
    }
}
