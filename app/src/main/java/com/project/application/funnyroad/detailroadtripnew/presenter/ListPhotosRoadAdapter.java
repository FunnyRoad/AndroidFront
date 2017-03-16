package com.project.application.funnyroad.detailroadtripnew.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailroadtripnew.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oa on 15/03/2017.
 */

public class ListPhotosRoadAdapter extends RecyclerView.Adapter<ListPhotosRoadAdapter.MyViewHolder>{

        private List<Integer> listPicture;
        private Post post;
        private Context ctx;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            // récuperation des composants utilisé pour chaque item de la recyclerview
            @BindView(R.id.descritpionPhotoRoad)
            TextView descritpionPhotoRoad;
            @BindView(R.id.imageViewPhotoRoad)
            ImageView imageViewPhotoRoad;

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


        public ListPhotosRoadAdapter(List<Integer> listPicture , Post post , Context ctx) {
            this.listPicture = listPicture;
            this.post = post;
            this.ctx =ctx;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.prototype_photos_road , parent, false);

            return new MyViewHolder(itemView);
        }

        // remplir les champs d'un item de la recyclerView
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position){
            int pictureId = listPicture.get(position);
            holder.descritpionPhotoRoad.setText(post.getText());
            Picasso.with(ctx).load("http://vps376653.ovh.net:8080/post/picture/"+pictureId).resize(600, 200).into(holder.imageViewPhotoRoad);
        }

        @Override
        public int getItemCount() {
            return listPicture.size();
        }


}
