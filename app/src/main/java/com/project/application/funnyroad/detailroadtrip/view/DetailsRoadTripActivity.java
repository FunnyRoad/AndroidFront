package com.project.application.funnyroad.detailroadtrip.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;
import com.project.application.funnyroad.detailroadtrip.modele.Place;
import com.project.application.funnyroad.detailroadtrip.service.PlaceService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DetailsRoadTripActivity extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailsRoadTripFragment mDetailsRoadTripFragment = new DetailsRoadTripFragment();

        //desactiver la toolbar
        //mToolbar.setVisibility(View.GONE);

        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                .add(R.id.container_fragment, mDetailsRoadTripFragment)
                .commit();

        PlaceService placeService = new RestAdapter.Builder()
                .setEndpoint(PlaceService.ENDPOINT)
                .build()
                .create(PlaceService.class);

        placeService.listPlaces(new Callback<List<Place>>() {
            @Override
            public void success(List<Place> places, Response response) {
                afficherPlaces(places);
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    public void afficherPlaces(List<Place> places) {
        Toast.makeText(this,"nombre de places : "+places.size(),Toast.LENGTH_SHORT).show();
    }
}

