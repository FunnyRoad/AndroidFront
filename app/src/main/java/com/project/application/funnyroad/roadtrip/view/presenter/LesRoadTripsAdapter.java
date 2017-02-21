package com.project.application.funnyroad.roadtrip.view.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.roadtrip.view.model.RoadTrip;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sameur on 20/02/2017.
 */

public class LesRoadTripsAdapter extends RecyclerView.Adapter<LesRoadTripsAdapter.MyViewHolder> {

    private List<RoadTrip> roadTripsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // récuperation des composants utilisé pour chaque item de la recyclerview
        @BindView(R.id.imageView_photo)
        ImageView photo;

        public MyViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            // un listener quand on clique sur un item de la recyclerView
            // nous renvoie dans l'activity detail de l'offre selectionnée
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    //Intent intent = new Intent(v.getContext(), RequestsActivity.class);
                    //intent.putExtra("tripSelectedId",""+tripsList.get(position).getId_trip());
                    //view.getContext().startActivity(intent);
                }
            });
        }
    }


    public LesRoadTripsAdapter(List<RoadTrip> roadTripsList) {
        this.roadTripsList = roadTripsList;
    }

    @Override
    public LesRoadTripsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_photos_details , parent, false);

        return new LesRoadTripsAdapter.MyViewHolder(itemView);
    }

    // remplir les champs d'un item de la recyclerView
    @Override
    public void onBindViewHolder(LesRoadTripsAdapter.MyViewHolder holder, int position){
        RoadTrip roadTrip = roadTripsList.get(position);
        holder.photo.setImageBitmap(roadTrip.getPhoto());
    }

    @Override
    public int getItemCount() {
        return roadTripsList.size();
    }
}
