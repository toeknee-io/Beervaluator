package com.loosefang.beervaluator;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loosefang.beerfinder.db.Beer;
import com.loosefang.dialogs.BeervaluateDialog;
import com.loosefang.dialogs.SaveBeerDialog;
import com.parse.ParseObject;


public class MainActivity extends FragmentActivity {

    private static final String LOGTAG = "BEERVALUATOR";

    public final static String BEER_NAME = "";
    public final static String STORE_NAME = "";
    public final static String RATING = "";

    String beerName;
    String storeName;

    String alcPercent;
    String alcQty;
    String alcPrice;

    Double dPercent;
    Double dQty;
    Double dPrice;

    Double rating;
    String strRating;

//    BeersDataSource datasource;

    ParseObject beers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        datasource = new BeersDataSource(this);
//        datasource.open();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
/*
        Integer id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    public void setBeerData() {
        EditText getBeerName = (EditText) findViewById(R.id.beerName);
        beerName = getBeerName.getText().toString();

        EditText getStoreName = (EditText) findViewById(R.id.storeName);
        storeName = getStoreName.getText().toString();

        EditText getPercent = (EditText) findViewById(R.id.alcPercent);
        alcPercent = getPercent.getText().toString();
        dPercent = Double.parseDouble(alcPercent);

        EditText getQty = (EditText) findViewById(R.id.alcQty);
        alcQty = getQty.getText().toString();
        dQty = Double.parseDouble(alcQty);

        EditText getPrice = (EditText) findViewById(R.id.alcPrice);
        alcPrice = getPrice.getText().toString();
        dPrice = Double.parseDouble(alcPrice);

        rating = dPercent * dQty / dPrice;
    }

    public void beervaluate(View v) {

        try {

            setBeerData();
/*
            EditText getBeerName = (EditText) findViewById(R.id.beerName);
            beerName = getBeerName.getText().toString();

            EditText getStoreName = (EditText) findViewById(R.id.storeName);
            storeName = getStoreName.getText().toString();

            EditText getPercent = (EditText) findViewById(R.id.alcPercent);
            alcPercent = getPercent.getText().toString();
            dPercent = Double.parseDouble(alcPercent);

            EditText getQty = (EditText) findViewById(R.id.alcQty);
            alcQty = getQty.getText().toString();
            dQty = Double.parseDouble(alcQty);

            EditText getPrice = (EditText) findViewById(R.id.alcPrice);
            alcPrice = getPrice.getText().toString();
            dPrice = Double.parseDouble(alcPrice);

            rating = dPercent * dQty / dPrice;
*/
            String evaluation = "";
            if(rating < 10) {
                evaluation = "Jerry would probably buy this";
            }
            if(rating > 10 && rating < 20) {
                evaluation = "Not bad, not bad";
            }
            if(rating > 20) {
                evaluation = "Drink up!";
            }
/*
            TextView displayRating = (TextView) findViewById(R.id.alcRating);
            displayRating.setText(strRating + "\n" + evaluation);
*/
            this.strRating = String.format("%.1f", rating);
            Log.i(LOGTAG, strRating);


            Bundle bundle = new Bundle();
            bundle.putString("name", beerName);
            bundle.putString("store", storeName);
            bundle.putString("rating", strRating);

            DialogFragment dialog = new BeervaluateDialog();
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "BeervaluateDialog");

        } catch (Exception e) {
            Toast toast = new Toast(this);
            toast.makeText(this, "Please make sure all fields are filled out", Toast.LENGTH_LONG).show();
        }
    }

/*
    public void saveBeer(View v) throws IOException {
        beervaluate(null);

        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();

        xmlSerializer.setOutput(writer);

            xmlSerializer.startDocument("UTF-8", true);

            xmlSerializer.startTag("", "beer");

                xmlSerializer.startTag("", "name");
                xmlSerializer.text(beerName);
                xmlSerializer.endTag("", "name");

                xmlSerializer.startTag("", "store");
                xmlSerializer.text(storeName);
                xmlSerializer.endTag("", "store");

                xmlSerializer.startTag("", "rating");
                xmlSerializer.text(String.valueOf(rating));
                xmlSerializer.endTag("", "rating");

             xmlSerializer.endTag("", "beer");

            xmlSerializer.endDocument();


        String text = writer.toString();

        FileOutputStream fos = openFileOutput("beers", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();

        Toast toast = new Toast(this);
        toast.makeText(this, "Beer Saved!: " + writer.toString(), Toast.LENGTH_LONG).show();

    }

    public void readFile(View v) throws IOException, JSONException {

        FileInputStream fis = openFileInput("beers");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while(bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }

        Log.i("STREAM", b.toString());

        bis.close();
        fis.close();

        JSONArray data = new JSONArray(b.toString());

        StringBuffer beersBuffer = new StringBuffer();
        for(Integer i = 0; i < data.length(); i++) {
            String beer = data.getJSONObject(i).getString("beer");
            beersBuffer.append(beer + "\n");
            String store = data.getJSONObject(i).getString("store");
            beersBuffer.append(store + "\n");
            String rating = data.getJSONObject(i).getString("rating");
            beersBuffer.append(rating + "\n");
        }

        Toast toast = new Toast(this);
        toast.makeText(this, "Beer Read!: \n" + beersBuffer.toString(), Toast.LENGTH_LONG).show();
    }
*/
    public void seeSavedBeers(View v) {
        Intent intent = new Intent(this, DisplaySavedBeersActivity.class);
        startActivity(intent);
    }
/*
    public void createData(View view){
        
        beervaluate(null);
                
        Beer beer = new Beer();
        beer.setName(beerName);
        beer.setStore(storeName);
        beer.setAlcPct(dPercent);
        beer.setAlcQty(dQty);
        beer.setPrice(dPrice);
        beer.setRating(rating);
        beer = datasource.create(beer);

        Log.i(LOGTAG, "Beer created with id " + beer.getBeerId());
        
        Bundle bundle = new Bundle();
        bundle.putString("name", beerName);
        bundle.putString("store", storeName);
        bundle.putString("rating", strRating);

        DialogFragment dialog = new SaveBeerDialog();
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(), "SaveBeerDialog");
    }
*/

    public void createData(View view){

        setBeerData();

        Beer beer = new Beer();
        beer.setName(beerName);
        beer.setStore(storeName);
        beer.setAlcPct(dPercent);
        beer.setAlcQty(dQty);
        beer.setPrice(dPrice);
        beer.setRating(rating);

        beers = new ParseObject("Beers");
        beers.put("name", beerName);
        beers.put("location", storeName);
        beers.put("alcPct", dPercent);
        beers.put("alcQty", dQty);
        beers.put("alcPrice", dPrice);
        beers.put("alcRating", rating);

        beers.saveInBackground();

        Bundle bundle = new Bundle();
        bundle.putString("name", beerName);
        bundle.putString("store", storeName);
        bundle.putString("rating", strRating);

        DialogFragment dialog = new SaveBeerDialog();
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(), "SaveBeerDialog");

    }
}
