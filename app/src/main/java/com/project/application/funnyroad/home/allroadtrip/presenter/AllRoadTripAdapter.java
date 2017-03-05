package com.project.application.funnyroad.home.allroadtrip.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.detailroadtripnew.view.DetailRoadTripActivity;
import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by you on 23/02/2017.
 */

public class AllRoadTripAdapter extends RecyclerView.Adapter<AllRoadTripAdapter.MyViewHolder> {

    private List<RoadTrip> tripsList;
    private Activity activity;
    private GoogleApiClient googleApiClient;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // récuperation des composants utilisé pour chaque item de la recyclerview
        @BindView(R.id.textViewBegin)
        TextView textViewBegin;
        @BindView(R.id.textViewDestination)
        TextView textViewDestination;
        @BindView(R.id.textViewDescription)
        TextView textViewDescription;

        public MyViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            // un listener quand on clique sur un item de la recyclerView
            // nous renvoie dans l'activity detail de l'offre selectionnée
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int position = getAdapterPosition();
                Intent intent = new Intent(v.getContext(), DetailRoadTripActivity.class);
                intent.putExtra("roadTripSelected",tripsList.get(position));
                view.getContext().startActivity(intent);
                }
            });
        }
    }


    public AllRoadTripAdapter(List<RoadTrip> tripsList , Activity activity , GoogleApiClient googleApiClient) {
        this.tripsList = tripsList;
        this.activity = activity;
        this.googleApiClient = googleApiClient;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prototype_all_road_trip , parent, false);
        return new MyViewHolder(itemView);
    }

    String namePlace ="";

    // remplir les champs d'un item de la recyclerView
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position){
        RoadTrip roadTrip = tripsList.get(position);
        if(roadTrip.getBegin() != null) {
            holder.textViewBegin.setText(Utility.getAdressByLatLng(this.activity ,roadTrip.getBegin().getLatitude() , roadTrip.getBegin().getLongitude() ));
        }
        if (!(roadTrip.getDestination().equals("") )){
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(googleApiClient, roadTrip.getDestination());
            placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                @Override
                public void onResult(PlaceBuffer places) {
                    if (!places.getStatus().isSuccess()) {
                        Log.d("allRoadTripAdapter", "Place query did not complete. Error: " +
                                places.getStatus().toString());
                        return;
                    }
                    // Selecting the first object buffer.
                    final Place place = places.get(0);
                    namePlace = place.getName().toString();
                    holder.textViewDestination.setText(namePlace);
                    //Log.d("allRoadTripAdapter", "onResult: " + place.getId() + " "+ namePlace);
                }
            });
        }

        holder.textViewDescription.setText(roadTrip.getName());
    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }
}
