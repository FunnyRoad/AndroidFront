package com.project.application.funnyroad.detailroadtripnew.view;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by oa on 27/02/2017.
 */

public class AutoCompleteAdapter extends ArrayAdapter<AutoCompletePlace> {
    private GoogleApiClient mGoogleApiClient;

    public AutoCompleteAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate( android.R.layout.simple_list_item_1, parent, false);
            holder.text = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(position).getDescription());

        return convertView;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.mGoogleApiClient = googleApiClient;
    }

    private class ViewHolder {
        TextView text;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                if(mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
                    Toast.makeText( getContext(), "Not connected", Toast.LENGTH_SHORT ).show();
                    return null;
                }

                clear();
                Log.d("autocomplete", "performFiltering: " + constraint.toString());
                displayPredictiveResults(constraint.toString());

                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }

    private void displayPredictiveResults(String query) {

        Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, query, null, new AutocompleteFilter.Builder().build())
                .setResultCallback (
                        new ResultCallback<AutocompletePredictionBuffer>() {
                            @Override
                            public void onResult(AutocompletePredictionBuffer buffer) {
                                if(buffer == null)
                                    return;

                                if(buffer.getStatus().isSuccess()) {
                                    for(AutocompletePrediction prediction : buffer) {
                                        //Add as a new item to avoid IllegalArgumentsException when buffer is released
                                        add(new AutoCompletePlace(prediction.getPlaceId(), prediction.getFullText(null).toString()));
                                    }
                                }

                                //Prevent memory leak by releasing buffer
                                buffer.release();
                            }
                        }, 60, TimeUnit.SECONDS);
    }
}
