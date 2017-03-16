package com.project.application.funnyroad.detailEndroit.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.addplace.model.Picture;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oraberkane on 04/02/2017.
 */


/**
 * Created by you on 21/01/2017.
 */

public class DetailsEndroitAdapter extends RecyclerView.Adapter<DetailsEndroitAdapter.MyViewHolder> {

    private List<Picture> endroitList;
    private Context ctx;

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
                }
            });
        }
    }


    public DetailsEndroitAdapter(List<Picture> endroitList , Context ctx) {
        this.endroitList = endroitList;
        this.ctx = ctx;
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
        int id = endroitList.get(position).getId();
        String type = endroitList.get(position).getType();
        Picasso.with(ctx).load("http://vps376653.ovh.net:8080/picture/"+id +"."+type).resize(600, 300).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return endroitList.size();
    }
}
