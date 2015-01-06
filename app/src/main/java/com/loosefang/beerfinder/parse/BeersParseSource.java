package com.loosefang.beerfinder.parse;

import android.util.Log;

import com.loosefang.beerfinder.db.Beer;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 1/4/2015.
 */
public class BeersParseSource {

    Beer beer;
    List<Beer> beers;


    public List<Beer> findAll() {

        beers = new ArrayList<Beer>();

        try {
            ParseQuery<ParseObject> all = new ParseQuery<ParseObject>("Beers");
            all.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> beersList, ParseException e) {
                    Log.i("Beer List", "Retrieved " + beersList.size() + " beers");
                    for (int i = 0; i < beersList.size(); i++) {
                        beer = new Beer();
                        ParseObject parseObject = beersList.get(i);
                        Log.i("Beer Name", parseObject.getString("name"));
                        Log.i("Location", parseObject.getString("location"));
                        beer.setName(parseObject.getString("name"));
                        beer.setStore(parseObject.getString("location"));
                        Number nRating = parseObject.getNumber("alcRating");
                        String strRating = String.valueOf(nRating);
                        Log.i("Rating", strRating);
                        Double rating = Double.valueOf(strRating);
                        beer.setRating(rating);
                        beers.add(beer);
                        Log.i("List Size", String.valueOf(beers.size()));
                    }
                }
            });
        } catch (Exception e) {
            Log.i("List Error: ", e.getMessage());
        }

        Log.i("Beer List", String.valueOf(beers.size()));
        return beers;

    }

    public List<Beer> findFiltered(String column, String sort) {

        beers = new ArrayList<Beer>();
        try {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Beers");
            if (sort == "asc") {
                query.orderByAscending(column);
            }
            if (sort == "desc") {
                query.orderByDescending(column);
            }
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> beersList, ParseException e) {
                    Log.i("Beer List", "Retrieved " + beersList.size() + " beers");
                    for (int i = 0; i < beersList.size(); i++) {
                        beer = new Beer();
                        ParseObject parseObject = beersList.get(i);
                        Log.i("Beer Name", parseObject.getString("name"));
                        Log.i("Location", parseObject.getString("location"));
                        beer.setName(parseObject.getString("name"));
                        beer.setStore(parseObject.getString("location"));
                        Number nRating = parseObject.getNumber("alcRating");
                        String strRating = String.valueOf(nRating);
                        Log.i("Rating", strRating);
                        Double rating = Double.valueOf(strRating);
                        beer.setRating(rating);
                        beers.add(beer);
                        Log.i("List Size", String.valueOf(beers.size()));
                    }
                }
            });
        } catch(Exception e) {Log.i("List Error: ", e.getMessage());}

        Log.i("Beer List",String.valueOf(beers.size()));

        return beers;
    }

}


