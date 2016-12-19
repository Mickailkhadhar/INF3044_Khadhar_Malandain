package com.example.micka.application;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Micka on 15/11/2016.
 */

class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {

    private JSONArray biers;

    public BiersAdapter(JSONArray jsonArray) {
        this.biers = jsonArray;
    }


    @Override
    public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_bier_element, null);

        BierHolder bierHolder = new BierHolder(view);
        return bierHolder;
    }

    @Override
    public void onBindViewHolder(BierHolder holder, int position) {
        try {
            JSONObject jsonObject = biers.getJSONObject(position);
            holder.name.setText(jsonObject.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return biers.length();
    }


    public void setNewBiere(JSONArray jsonArray) {
        this.biers = jsonArray;
        notifyDataSetChanged();
    }


    class BierHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public BierHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rv_biere_element_name);
        }
    }
}
