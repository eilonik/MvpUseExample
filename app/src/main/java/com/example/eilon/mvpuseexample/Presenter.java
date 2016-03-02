package com.example.eilon.mvpuseexample;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eilon on 01/03/16.
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
        final PokemonRetriever pokemonRetriever = new PokemonRetriever();
        final Observable operationObservable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                Subscriber subscriber = (Subscriber)o;
                subscriber.onNext(pokemonRetriever.getPokemons());
                subscriber.onCompleted();
            }
        })
                // registering subscriber on threads
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        operationObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                placeData.setText("Error retrieving pokemons :/ , " + e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                placeData.setText((String)o);
            }
        });
    }
}
