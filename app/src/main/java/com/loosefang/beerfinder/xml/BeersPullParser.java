package com.loosefang.beerfinder.xml;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;

import com.loosefang.beerfinder.db.Beer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BeersPullParser {

	private static final String LOGTAG = "BEERVALUATOR";

	private static final String Beer_NAME = "name";
	private static final String Beer_STORE = "store";
	private static final String Beer_RATING = "rating";
	
	private Beer currentBeer  = null;
	private String currentTag = null;
	List<Beer> Beers = new ArrayList<Beer>();

	public List<Beer> parseXML(Context context) {

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			
			InputStream stream = context.openFileInput("beers");
			xpp.setInput(stream, null);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					handleStartTag(xpp.getName());
                    Log.i("WHILE", xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					currentTag = null;
				} else if (eventType == XmlPullParser.TEXT) {
					handleText(xpp.getText());
                    Log.i("WHILE", xpp.getText());
				}
				eventType = xpp.next();
			}

		} catch (NotFoundException e) {
			Log.d(LOGTAG, "NFE: " + e.getMessage() + " / " + e.getStackTrace());
		} catch (XmlPullParserException e) {
			Log.d(LOGTAG, "XPPE: " + e.getMessage() + " / " + e.getStackTrace());
		} catch (IOException e) {
			Log.d(LOGTAG, "IOE: " + e.getMessage() + " / " + e.getStackTrace());
		}

		return Beers;
	}

	private void handleText(String text) {
		String xmlText = text;
		if (currentBeer != null && currentTag != null) {
			if (currentTag.equals("name")) {
				currentBeer.setName(xmlText);
                Log.i("WHILE",xmlText);
			}
			else if (currentTag.equals("store")) {
				currentBeer.setStore(xmlText);
                Log.i("WHILE",xmlText);
			}
			else if (currentTag.equals("rating")) {
				double rating = Double.parseDouble(xmlText);
				currentBeer.setRating(rating);
                Log.i("WHILE", xmlText);
			}
		}
	}

	private void handleStartTag(String name) {
		if (name.equals("beer")) {
			currentBeer = new Beer();
			Beers.add(currentBeer);
		}
		else {
			currentTag = name;
		}
	}
}
