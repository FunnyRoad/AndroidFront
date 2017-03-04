package com.project.application.funnyroad.detailroadtripnew.presenter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailEndroit.view.DetailsEndroitActivity;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oraberkane on 04/02/2017.
 */


/**
 * Created by you on 21/01/2017.
 */

public class ListEndroitAdapter extends RecyclerView.Adapter<ListEndroitAdapter.MyViewHolder> {

    private List<Place> placeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // récuperation des composants utilisé pour chaque item de la recyclerview
        @BindView(R.id.endroitName)
        TextView endroitName;

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
                    intent.putExtra("endroitSelected", placeList.get(position));
                    view.getContext().startActivity(intent);
                }
            });
        }
    }


    public ListEndroitAdapter(List<Place> placeList) {
        this.placeList = placeList;
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
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }
}
