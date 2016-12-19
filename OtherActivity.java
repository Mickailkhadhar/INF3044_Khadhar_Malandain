package com.example.micka.application;

/**
 * Created by Micka on 18/12/2016.
 */

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class OtherActivity extends AppCompatActivity implements onListener {

    public static final String BIERS_UPDATE="com.octip.cours.inf4042_11.BIERS_UPDATE";
    public BiersAdapter biersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(this), intentFilter);

        GetBiersServices.startActionBiers(this);

        final RecyclerView rv = (RecyclerView)findViewById(R.id.rv_biere);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        biersAdapter = new BiersAdapter(getBiersFromFile());

        rv.setAdapter(biersAdapter);
    }

    public JSONArray getBiersFromFile(){
        try{
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));
        } catch (IOException e){
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e){
            e.printStackTrace();
            return new JSONArray();
        }
    }


    @Override
    public void onFinish() {
        biersAdapter.setNewBiere(getBiersFromFile());
    }
}

