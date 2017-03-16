package com.project.application.funnyroad.detailroadtripnew.presenter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailEndroit.view.DetailsEndroitActivity;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oraberkane on 04/02/2017.
 */


/**
 * Created by oa on 21/01/2017.
 */

public class ListEndroitAdapter extends RecyclerView.Adapter<ListEndroitAdapter.MyViewHolder> {

    private List<Place> placeList;
    private RoadTrip roadTrip;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // récuperation des composants utilisé pour chaque item de la recyclerview
        @BindView(R.id.endroitName)
        TextView endroitName;
        @BindView(R.id.ratingBarListPlace)
        RatingBar ratingBarListPlace;

        public MyViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            // un listener quand on clique sur un item de la recyclerView
            // nous renvoie dans l'activity detail de l'offre selectionnée
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), DetailsEndroitActivity.class);
                    intent.putExtra("endroitSelected", (Serializable) placeList.get(position));
                    intent.putExtra("roadTripId" , roadTrip.getId());
                    intent.putExtra("roadTripOwner" , roadTrip.getOwner().getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }


    public ListEndroitAdapter(List<Place> placeList , RoadTrip roadTrip) {
        this.placeList = placeList;
        this.roadTrip = roadTrip;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prototype_list_endroit , parent, false);

        return new MyViewHolder(itemView);
    }

    // remplir les champs d'un item de la recyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Place place = placeList.get(position);
        holder.endroitName.setText(place.getName());
        holder.ratingBarListPlace.setRating(place.getGrade());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }
}
