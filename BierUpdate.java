package com.example.micka.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Micka on 18/12/2016.
 */

public class BierUpdate extends BroadcastReceiver  {

    onListener listener;

    BierUpdate(onListener listener){
        this.listener = listener;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, R.string.toast2, Toast.LENGTH_LONG).show();
        listener.onFinish();
    }

}

