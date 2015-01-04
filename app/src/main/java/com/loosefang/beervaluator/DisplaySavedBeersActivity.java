package com.loosefang.beervaluator;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.loosefang.beerfinder.db.BeersDataSource;
import com.loosefang.beerfinder.db.Beer;

import java.util.List;

/**
 * Created by Tony on 12/29/2014.
 */
public class DisplaySavedBeersActivity extends ListActivity{

    BeersDataSource datasource;
    private List<Beer> beers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_saved_beers_layout);

//        BeersPullParser parser = new BeersPullParser();
//        List<Beer> beers = parser.parseXML(this);

        datasource = new BeersDataSource(this);
        datasource.open();

        beers = datasource.findAll();

        if (beers.size() == 0){
            Toast toast = new Toast(this);
            toast.makeText(this, "No beers saved yet", Toast.LENGTH_LONG).show();
        }

        Log.i("BEERLIST", beers.toString());

        refreshDisplay();

    }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_beers, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.filter_by_all:
                beers = datasource.findAll();
                refreshDisplay();
                break;
            case R.id.filter_by_beer:
                beers = datasource.findFiltered("name IS NOT NULL", "name ASC");
                refreshDisplay();
                break;
            case R.id.filter_by_store:
                beers = datasource.findFiltered("store IS NOT NULL", "store ASC");
                refreshDisplay();
                break;
            case R.id.filter_by_rating:
                beers = datasource.findFiltered("rating", "rating DESC");
                refreshDisplay();
                break;
            case R.id.clearDb:
                datasource.clearDb();
                refreshDisplay();
                break;
            default:
                break;
        }
            return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onResume() {
        super.onResume();
        datasource.open();

    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.closed();

    }

    public void refreshDisplay() {
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.saved_beers_list, beers);
        setListAdapter(adapter);
    }
}
