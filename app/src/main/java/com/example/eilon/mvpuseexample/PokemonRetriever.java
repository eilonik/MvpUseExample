package com.example.eilon.mvpuseexample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eilon on 01/03/16.
 * This class is the model in the MVP
 */
public class PokemonRetriever {

    private Presenter presenter;

    public PokemonRetriever(final Presenter presenter) {

        this.presenter = presenter;

        final Observable operationObservable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                Subscriber subscriber = (Subscriber)o;
                subscriber.onNext(getPokemons()); // The long task to be performed
                subscriber.onCompleted();
            }
        })
         // registering subscriber on threads
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread());

        operationObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                unsubscribeMe();
            }

            @Override
            public void onError(Throwable e) {
                presenter.finishedRetrievingData("Error retrieving pokemons :/ , " + e.getMessage());
                unsubscribeMe();
            }

            @Override
            public void onNext(Object o) {
                presenter.finishedRetrievingData((String) o);
            }

            // Unsubscribes the current observable
            private void unsubscribeMe() {
                if(!this.isUnsubscribed()) {
                    this.unsubscribe();
                }
            }
        });

    }


    // This method simulates reading from a database(in this case plane text file)
    public String getPokemons()  {

        String pokemonsList = "";

        // Reading the database file into pokemonsList
        InputStream inputStream = presenter.retrieveModelActivity().getApplicationContext()
                .getResources().openRawResource(R.raw.pokemons);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String temp = "";
        while (temp != null) {
            try {
                temp = bufferedReader.readLine();
                if(temp != null){
                    pokemonsList += temp + "\n";
                }
            } catch (Exception e){}
        }

        // Closing all open streams
        try{
            bufferedReader.close();
        } catch (Exception e){}

        try{

            inputStreamReader.close();
        } catch (Exception e){}

        try {

            inputStream.close();
        } catch (Exception e){}

        return pokemonsList;
    }
}
