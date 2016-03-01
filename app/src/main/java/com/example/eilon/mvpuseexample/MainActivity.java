package com.example.eilon.mvpuseexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView showInfo;
    Button button;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showInfo = (TextView)findViewById(R.id.infoToPresent);
        button = (Button)findViewById(R.id.button);
        presenter = new Presenter(this, button, showInfo);
    }

    public void setDateInView(ArrayList<Pokemon> pokemons) {
        for(Pokemon pokemon:pokemons) {
            showInfo.setText(showInfo.getText() + pokemon.toString());
        }
    }
}
