package com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailEndroit.view.DetailsEndroitActivity;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oraberkane on 04/02/2017.
 */


/**
 * Created by you on 21/01/2017.
 */

public class ListRoadTripAdapter extends RecyclerView.Adapter<ListRoadTripAdapter.MyViewHolder> {

    private List<Place> placeList;
    public ArrayList<Place> listChecked = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // récuperation des composants utilisé pour chaque item de la recyclerview
        @BindView(R.id.endroitName)
        TextView endroitName;
        @BindView(R.id.endroitDescription)
        TextView endroitDescription;
        @BindView(R.id.checkBox)
        CheckBox checkBox;



        public MyViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            checkBox.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int position = getAdapterPosition();
            if(v.getId() == checkBox.getId()){
                if (checkBox.isChecked()){
                    Log.d("checked", "onClick: ");
                    listChecked.add(placeList.get(position));
                }
            }
            Intent intent = new Intent(v.getContext(), DetailsEndroitActivity.class);
            intent.putExtra("endroitSelected", placeList.get(position));
            v.getContext().startActivity(intent);
        }
    }


    public ListRoadTripAdapter(List<Place> placeList) {
        this.placeList = placeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prototype_raw_list_road , parent, false);

        return new MyViewHolder(itemView);
    }

    // remplir les champs d'un item de la recyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Place trip = placeList.get(position);
        holder.endroitName.setText(trip.getName());
        holder.endroitDescription.setText(trip.getDescription());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }
}
