package com.example.eilon.mvpuseexample;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eilon on 01/03/16.
 */
public class Presenter {

    private MainActivity viewToHandle;
    private Button buttonToListen;
    private TextView placeData;

    public Presenter() {}

    public Presenter(MainActivity viewToHandle, Button buttonToListen, TextView placeDate) {
        this.viewToHandle = viewToHandle;
        this.buttonToListen = buttonToListen;
        this.placeData = placeDate;
        buttonToListen.setBackgroundResource(R.mipmap.pokeball);
        buttonToListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick();
            }
        });
    }

    private void handleClick() {
        buttonToListen.setBackgroundResource(R.mipmap.pokeball_open);
        placeData.setText("Retrieving pokemon list ...");
        //TODO RxAndroid Async request from Model
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        placeData.setText("");
        viewToHandle.setDateInView(pokemonList);
    }

}
