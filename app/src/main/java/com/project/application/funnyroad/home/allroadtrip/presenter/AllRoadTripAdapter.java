package com.project.application.funnyroad.home.allroadtrip.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class AllRoadTripAdapter extends RecyclerView.Adapter<AllRoadTripAdapter.MyViewHolder>  {

    private List<RoadTrip> tripsList;
    private Activity activity;
    private GoogleApiClient googleApiClient;
    private String namePlace = "" ;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // récuperation des composants utilisé pour chaque item de la recyclerview
        @BindView(R.id.textViewBegin)
        TextView textViewBegin;
        @BindView(R.id.textViewDestination)
        TextView textViewDestination;
        @BindView(R.id.textViewDescription)
        TextView textViewDescription;
        @BindView(R.id.buttonFollow)
        Button buttonFollow;

        public MyViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            buttonFollow.setOnClickListener(this);
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), DetailRoadTripActivity.class);
                    intent.putExtra("roadTripSelected",tripsList.get(position));
                    activity.startActivity(intent);
                }
            });
        }
            // un listener quand on clique sur un item de la recyclerView
            // nous renvoie dans l'activity

        @Override
        public void onClick(View v) {
            PresenterAllRoadTrip presenterAllRoadTrip = new PresenterAllRoadTrip(activity);
            int position = getAdapterPosition();
            if(v.getId() == buttonFollow.getId()) {
                presenterAllRoadTrip.addUserToGuestList( Utility.getIdUser(activity) , tripsList.get(position).getId());
                buttonFollow.setBackgroundResource(R.drawable.ic_check_black_24dp);
            }
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
                        return;
                    }
                    // Selecting the first object buffer.
                    final Place place = places.get(0);
                    namePlace = place.getName().toString();
                    holder.textViewDestination.setText(namePlace);
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
