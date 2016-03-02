package com.example.eilon.mvpuseexample;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by eilon on 01/03/16.
 * This class is the presenter in the MVP
 */
public class Presenter {

    private Button buttonToListen;
    private TextView placeData;

    public Presenter() {}

    public Presenter(Button buttonToListen, TextView placeDate) {
        this.buttonToListen = buttonToListen;
        this.placeData = placeDate;
        buttonToListen.setBackgroundResource(R.mipmap.pokeball);
        buttonToListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                handleClick();
            }
        });
    }

    private void handleClick() {
        buttonToListen.setBackgroundResource(R.mipmap.pokeball_open);
        placeData.setText("Retrieving pokemon list ...");
        final PokemonRetriever pokemonRetriever = new PokemonRetriever(this);

    }


    // This method is invoked in order to notify the presenter
    // that data is emitted from the PokemonRetriever
    public void finishedRetrievingData(String text) {
        placeData.setText(text);
    }
}
