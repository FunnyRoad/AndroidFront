package com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailEndroit.view.DetailsEndroitActivity;
import com.project.application.funnyroad.newroadtrip.Variable;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.utils.CustomPlace;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.io.Serializable;
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

public class ListPlacesAdapter extends RecyclerView.Adapter<ListPlacesAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<CustomPlace> listPlaces;

    private Variable variables;

    public ArrayList<CustomPlace> listChecked = new ArrayList<CustomPlace>();

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //ATTRIBUTES

        @BindView(R.id.placeName)
        TextView placeName;
        @BindView(R.id.placeAddress)
        TextView placeDescription;
        @BindView(R.id.placeGrade)
        RatingBar placeGrade;
        @BindView(R.id.gradeValue)
        TextView gradeValue;
        @BindView(R.id.checkBox)
        CheckBox checkBox;

        //CONSTRUCTOR

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
                    listChecked.add(listPlaces.get(position));
                    listPlaces.get(position).setPlaceChecked(true);
                }
                if (!checkBox.isChecked()){
                    Log.d("unchecked", "onClick: ");
                    listChecked.remove(listPlaces.get(position));
                    listPlaces.get(position).setPlaceChecked(false);
                }
            }
            else{
                Intent intent = new Intent(v.getContext(), DetailsEndroitActivity.class);
                intent.putExtra("endroitSelected", (Serializable) listPlaces.get(position));
                //v.getContext().startActivity(intent);
            }

        }
    }

    public ListPlacesAdapter(Context context, List<CustomPlace> listPlaces) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listPlaces = listPlaces;
        this.variables = (Variable) this.context.getApplicationContext();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_from_list_places, parent, false);
        return new MyViewHolder(itemView);
    }

    // remplir les champs d'un item de la recyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        CustomPlace place = listPlaces.get(position);
        holder.placeName.setText(place.getPlaceName());
        switch (place.getPlaceType()) {
            case "amusement_park":
                holder.placeDescription.setText("Parc d'attractions");
                break;
            case "aquarium":
                holder.placeDescription.setText("Aquarium");
                break;
            case "art_gallery":
                holder.placeDescription.setText("Gallerie d'art");
                break;
            case "campground":
                holder.placeDescription.setText("Camping");
                break;
            case "museum":
                holder.placeDescription.setText("Musée");
                break;
            case "park":
                holder.placeDescription.setText("Parc");
                break;
            case "stadium":
                holder.placeDescription.setText("Stade");
                break;
            case "zoo":
                holder.placeDescription.setText("Zoo");
                break;
            default:
                holder.placeDescription.setText("Autre");
                break;
        }
        if (Float.parseFloat(place.getPlaceGrade()) != -1.0) {
            holder.placeGrade.setRating(Float.parseFloat(place.getPlaceGrade()));
            holder.gradeValue.setText(place.getPlaceGrade());
        } else {
            holder.placeGrade.setRating(0);
            holder.gradeValue.setText("Non noté");
        }
        if (place.getPlaceChecked()) {
            holder.checkBox.setChecked(true);
        }
        else {
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return listPlaces.size();
    }
}
