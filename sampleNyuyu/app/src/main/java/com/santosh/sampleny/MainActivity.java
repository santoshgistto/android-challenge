package com.santosh.sampleny;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.santosh.sampleny.starships.StarShipsCustomView;



public class MainActivity extends AppCompatActivity {

    StarShipsCustomView starShipsCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starShipsCustomView = (StarShipsCustomView) findViewById(R.id.starship_list);
        starShipsCustomView.setTitle(R.string.starship);
        // 1 : is a initial page
        starShipsCustomView.loadData(1);
    }
}
