package com.loosefang.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loosefang.beerfinder.db.Beer;
import com.loosefang.beervaluator.R;

/**
 * Created by Tony on 1/2/2015.
 */
public class BeervaluateDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Beer beers = new Beer();
        beers.getRating();

        String strRating = getArguments().getString("rating");
        Double dRating = Double.valueOf(strRating);
        Integer rating = dRating.intValue();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        ViewGroup vg = (ViewGroup)inflater.inflate(R.layout.beervaluate_layout, null);

        ImageView image = null;

        String evaluation = "";
        if(rating < 10) {
            evaluation = "Jerry would probably buy this";
            image = (ImageView) vg.findViewById(R.id.beervaluateDialogImg);
            image.setImageResource(R.drawable.bad_beer);
        }
        if(rating > 10 && rating < 20) {
            evaluation = "Not bad, not bad";
        }
        if(rating > 20) {
            evaluation = "Drink up!";
        }

        TextView setRating = (TextView) vg.findViewById(R.id.beervaluateRating);
        setRating.setText("Rating: " + strRating + "\n" + "\n");

        TextView setEval = (TextView) vg.findViewById(R.id.beervaluateEval);
        setEval.setText(evaluation);

        builder.setView(vg)
//                .setTitle("Results")
//               .setCustomTitle(vg.findViewById(R.id.beervaluateTitle))
//               .setMessage("Rating: " + strRating + "\n" + "\n" +
//                       "Beervaluation: " + evaluation)
               .setNeutralButton("Back to Drankin'", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                   }
               });
        return builder.create();
    }
}


