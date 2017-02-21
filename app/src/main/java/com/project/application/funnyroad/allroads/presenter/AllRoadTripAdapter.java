package com.project.application.funnyroad.allroads.presenter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.allroads.model.RoadTrip;
import com.project.application.funnyroad.detailroadtripnew.view.DetailRoadTripActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oraberkane on 04/02/2017.
 */


/**
 * Created by you on 21/01/2017.
 */

public class AllRoadTripAdapter extends RecyclerView.Adapter<AllRoadTripAdapter.MyViewHolder> {

    private List<RoadTrip> tripsList;

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


    public AllRoadTripAdapter(List<RoadTrip> tripsList) {
        this.tripsList = tripsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prototype_all_road_trip , parent, false);
        return new MyViewHolder(itemView);
    }

    // remplir les champs d'un item de la recyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        RoadTrip roadTrip = tripsList.get(position);
        holder.textViewBegin.setText(roadTrip.getBegin());
        holder.textViewDestination.setText(roadTrip.getDestination());
        holder.textViewDescription.setText(roadTrip.getDescription());
    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }
}
