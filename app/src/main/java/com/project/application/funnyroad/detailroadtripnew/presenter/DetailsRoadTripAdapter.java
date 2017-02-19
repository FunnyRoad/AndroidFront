package com.project.application.funnyroad.detailroadtripnew.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailroadtrip.modele.Endroit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oraberkane on 04/02/2017.
 */


/**
 * Created by you on 21/01/2017.
 */

public class DetailsRoadTripAdapter extends RecyclerView.Adapter<DetailsRoadTripAdapter.MyViewHolder> {

    private List<Endroit> tripsList;

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


    public DetailsRoadTripAdapter(List<Endroit> tripsList) {
        this.tripsList = tripsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_photos_details , parent, false);

        return new MyViewHolder(itemView);
    }

    // remplir les champs d'un item de la recyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Endroit trip = tripsList.get(position);
        holder.photo.setImageBitmap(trip.getPhoto());
    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }
}
