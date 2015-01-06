package com.loosefang.beervaluator;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.loosefang.beerfinder.db.Beer;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DisplaySavedBeersActivity extends ListActivity{

    Beer beer;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ArrayAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_saved_beers_layout);

        new RemoteDataTask().execute();
    }

    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(DisplaySavedBeersActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Grabbing Beervaluations");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "Beers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "Beers");
            query.orderByDescending("alcRating");
            try {
                ob = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Pass the results into an ArrayAdapter
            adapter = new ArrayAdapter<String>(DisplaySavedBeersActivity.this,
                    R.layout.listitem_beer);
            // Retrieve object "name" from Parse.com database
            for (ParseObject parseObject : ob) {
                beer = new Beer();

                beer.setName(parseObject.getString("name"));
                beer.setStore(parseObject.getString("location"));

                Number nAlcPct = parseObject.getNumber("alcPct");
                String strAlcPct = String.valueOf(nAlcPct);
                Double alcPct = Double.valueOf(strAlcPct);
                beer.setAlcPct(alcPct);

                Number nAlcQty = parseObject.getNumber("alcQty");
                String strAlcQty = String.valueOf(nAlcQty);
                Double alcQty = Double.valueOf(strAlcQty);
                beer.setAlcQty(alcQty);

                Number nAlcPrice = parseObject.getNumber("alcPrice");
                String strAlcPrice = String.valueOf(nAlcPrice);
                Double alcPrice = Double.valueOf(strAlcPrice);
                beer.setPrice(alcPrice);

                Number nRating = parseObject.getNumber("alcRating");
                String strRating = String.valueOf(nRating);
                Double rating = Double.valueOf(strRating);
                beer.setRating(rating);

                adapter.add(beer);
                Log.i("List Size", String.valueOf(ob.size()));
            }
            // Binds the Adapter to the ListView
            setListAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
            // Capture button clicks on ListView items
/*
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // Send single item click data to SingleItemView Class
                    Intent i = new Intent(DisplaySavedBeersActivity.this,
                            displaylistitemdetails.class);
                    // Pass data "name" followed by the position
                    i.putExtra("name", ob.get(position).getString("name")
                            .toString());
                    // Open SingleItemView.java Activity
                    startActivity(i);
                }
            });
*/
        }
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
                new RemoteDataTask().execute();
                break;
            case R.id.filter_by_beer:
                break;
            case R.id.filter_by_store:
                break;
            case R.id.filter_by_rating:
                break;
            default:
                new RemoteDataTask().execute();
                break;
        }
            return super.onOptionsItemSelected(item);

    }
}
