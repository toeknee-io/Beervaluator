package com.loosefang.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.loosefang.beerfinder.db.Beer;

/**
 * Created by Tony on 1/2/2015.
 */
public class SaveBeerDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Beer beers = new Beer();
        beers.getRating();

        String beerName = getArguments().getString("name");
        String storeName = getArguments().getString("store");
        String rating = getArguments().getString("rating");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Saved!")
                .setMessage("Beer: " + beerName + "\n" +
                "Location: " + storeName + "\n" +
                "Rating: " + rating + "\n")
                .setNeutralButton("Back to Drankin'", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                ;

        return builder.create();
    }
}


