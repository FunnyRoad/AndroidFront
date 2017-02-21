package com.project.application.funnyroad.detailroadtripnew.view;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.addplace.view.view.AddPlaceActivity;
import com.project.application.funnyroad.allroads.model.RoadTrip;
import com.project.application.funnyroad.detailroadtrip.modele.Place;


import com.project.application.funnyroad.detailroadtripnew.presenter.DetailsRoadTripAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by you on 18/02/2017.
 */

public class DetailRoadTripFragment extends Fragment {

    @BindView(R.id.textViewDetailDepart)
    TextView textViewDetailDepart;
    @BindView(R.id.textViewDetailDestination)
    TextView textViewDetailDestination;
    @BindView(R.id.textViewDetailDescription)
    TextView textViewDetailDescription;

    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.im2)
    ImageView im2;

    RoadTrip roadTrip;

    @BindView(R.id.details_road_recyclerView)
    RecyclerView details_road_recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_detail_road_trip, container, false);

        ButterKnife.bind(this, view);

        Intent i = getActivity().getIntent();
        if (i != null){
            roadTrip = (RoadTrip) i.getSerializableExtra("roadTripSelected");
            textViewDetailDepart.setText(roadTrip.getBegin());
            textViewDetailDestination.setText(roadTrip.getDestination());
            textViewDetailDescription.setText(roadTrip.getDescription());
        }




        //mPresenterLogin = new PresenterLogin(this);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
       // Bitmap bmp = Bitmap.createBitmap(200, 100, conf); // this creates a MUTABLE bitmap

        List<Place> listPhoto = new ArrayList<>();
        Place e1 = new Place();
        Place e2 = new Place();
        Place e3 = new Place();
        Place e4 = new Place();

        im1.buildDrawingCache();
        Bitmap bmp1 = im1.getDrawingCache();
        im2.buildDrawingCache();
        Bitmap bmp2 = im2.getDrawingCache();
        e1.setPhoto(bmp1);e2.setPhoto(bmp2);//e3.setPhoto(bmp);e4.setPhoto(bmp);
        listPhoto.add(e1); listPhoto.add(e2); listPhoto.add(e3); listPhoto.add(e4);

        DetailsRoadTripAdapter mAdapter = new DetailsRoadTripAdapter(listPhoto);
        details_road_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        details_road_recyclerView.setItemAnimator(new DefaultItemAnimator());
        details_road_recyclerView.setAdapter(mAdapter);

        return view;
    }

    @OnClick(R.id.addPlace)
    public void goToAddPlace(){

        Intent intent = new Intent(this.getContext() , AddPlaceActivity.class);
        startActivity(intent);
    }








}
