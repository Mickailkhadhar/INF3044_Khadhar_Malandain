package com.example.micka.application;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.media.MediaPlayer;


public class  MainActivity extends AppCompatActivity {
    AlertDialog dialog;
    NotificationManager manage;
    Notification.Builder notif;
    MediaPlayer mySound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button b = (Button) findViewById(R.id.button);
        mySound = MediaPlayer.create(this, R.raw.surprise);



        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.sentence)
                .setPositiveButton("Nop", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        dialog = builder.create();

        notif = new Notification.Builder(this).setContentTitle("Surprise!!!").setSmallIcon(android.R.drawable.alert_dark_frame);
        Intent resultIntent = new Intent(this, OtherActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notif.setContentIntent(resultPendingIntent);
        manage = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toast:
                Toast.makeText(getApplicationContext(), getString(R.string.toast), Toast.LENGTH_LONG).show();
                break;

            case R.id.dialog:
                dialog.show();
                break;

            case R.id.notif:
                manage.notify(0,notif.build());
                break;
        }

        return super.onOptionsItemSelected(item);
    }






        public void play(View view) {

            mySound = MediaPlayer.create(this, R.raw.surprise);
            mySound.start();
        }




    }

