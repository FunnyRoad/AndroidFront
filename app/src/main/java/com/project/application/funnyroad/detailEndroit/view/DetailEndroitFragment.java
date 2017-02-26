package com.project.application.funnyroad.detailEndroit.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailEndroit.presenter.DetailsEndroitAdapter;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by you on 20/02/2017.
 */

public class DetailEndroitFragment extends Fragment {

    @BindView(R.id.textViewNameEndroit)
    TextView textViewNameEndroit;
    @BindView(R.id.textViewDescriptionEndroit)
    TextView textViewDescriptionEndroit;
    @BindView(R.id.recycler_view_list_photo_endroit)
    RecyclerView recycler_view_list_photo_endroit;

    Place place;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_detail_endroit, container, false);

        ButterKnife.bind(this, view);

        Intent i = getActivity().getIntent();
        if (i != null) {
            place = (Place) i.getSerializableExtra("endroitSelected");
        }

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(200, 100, conf); // this creates a MUTABLE bitmap

        ArrayList<Bitmap> list = new ArrayList<>();
        list.add(bmp); list.add(bmp);list.add(bmp);
        place.setListPhotos(list);
        DetailsEndroitAdapter mAdapter = new DetailsEndroitAdapter(place.getListPhotos());
        recycler_view_list_photo_endroit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_list_photo_endroit.setItemAnimator(new DefaultItemAnimator());
        recycler_view_list_photo_endroit.setAdapter(mAdapter);

        return view;
    }
}
