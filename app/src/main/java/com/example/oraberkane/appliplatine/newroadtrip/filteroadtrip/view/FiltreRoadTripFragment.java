package com.example.oraberkane.appliplatine.newroadtrip.filteroadtrip.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oraberkane.appliplatine.R;

import butterknife.ButterKnife;

/**
 * Created by oraberkane on 03/02/2017.
 */

public class FiltreRoadTripFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_filtre_road,container,false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this,view);

        //myTripsOrganizedPresenter = new MyTripsOrganizedPresenter(this , getContext());
        //myTripsOrganizedPresenter.getTripsById();

        return view;
    }



}
