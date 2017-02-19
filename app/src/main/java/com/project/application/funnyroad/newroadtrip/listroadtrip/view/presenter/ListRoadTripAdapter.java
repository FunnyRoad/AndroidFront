package com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Endroit;

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

    private List<Endroit> endroitList;
    public ArrayList<Endroit> listChecked = new ArrayList<>();

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
        }

        @Override
        public void onClick(View v){
            int position = getAdapterPosition();
            if(v.getId() == checkBox.getId()){
                if (checkBox.isChecked()){
                    Log.d("checked", "onClick: ");
                    listChecked.add(endroitList.get(position));
                }
            }
        }
    }


    public ListRoadTripAdapter(List<Endroit> endroitList) {
        this.endroitList = endroitList;
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
        Endroit trip = endroitList.get(position);
        holder.endroitName.setText(trip.getName());
        holder.endroitDescription.setText(trip.getDescription());
    }

    @Override
    public int getItemCount() {
        return endroitList.size();
    }
}
