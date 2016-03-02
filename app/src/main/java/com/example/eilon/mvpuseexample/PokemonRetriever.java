package com.example.eilon.mvpuseexample;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eilon on 01/03/16.
 * This class is the model in the MVP
 */
public class PokemonRetriever {

    public PokemonRetriever(final Presenter presenter) {

        final Observable operationObservable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                Subscriber subscriber = (Subscriber)o;
                subscriber.onNext(getPokemons());
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

    public String getPokemons() {
        String pokemonsList = "GOOD TEXT\nGOOD TEXT";

        return pokemonsList;
    }
}
