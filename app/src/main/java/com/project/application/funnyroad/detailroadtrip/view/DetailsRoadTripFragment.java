package com.project.application.funnyroad.detailroadtrip.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailroadtrip.modele.Place;
import com.project.application.funnyroad.detailroadtripnew.presenter.DetailsRoadTripAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsRoadTripFragment extends Fragment {

    @BindView(R.id.details_road_recyclerView)
    RecyclerView details_road_recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_details_road_trip, container, false);
        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this, view);

        //mPresenterLogin = new PresenterLogin(this);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(200, 100, conf); // this creates a MUTABLE bitmap

        List<Place> listPhoto = new ArrayList<>();
        Place e1 = new Place();
        Place e2 = new Place();
        Place e3 = new Place();
        Place e4 = new Place();
        e1.setPhoto(bmp);e2.setPhoto(bmp);e3.setPhoto(bmp);e4.setPhoto(bmp);
        listPhoto.add(e1); listPhoto.add(e2); listPhoto.add(e3); listPhoto.add(e4);

        DetailsRoadTripAdapter mAdapter = new DetailsRoadTripAdapter(listPhoto);
        details_road_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        details_road_recyclerView.setItemAnimator(new DefaultItemAnimator());
        details_road_recyclerView.setAdapter(mAdapter);

        return view;
    }

}
