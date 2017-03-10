package com.project.application.funnyroad.newroadtrip.filteroadtrip.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.Variable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ameur on 28/02/2017.
 */

public class FiltrePlacesFragment extends Fragment {

    private Variable variables;

    @BindView(R.id.amusement_park)
    CheckBox cbAmusementPark;
    @BindView(R.id.aquarium)
    CheckBox cbAquarium;
    @BindView(R.id.art_gallery)
    CheckBox cbArtGallery;
    @BindView(R.id.campground)
    CheckBox cbCampground;
    @BindView(R.id.museum)
    CheckBox cbMuseum;
    @BindView(R.id.park)
    CheckBox cbPark;
    @BindView(R.id.stadium)
    CheckBox cbStadium;
    @BindView(R.id.zoo)
    CheckBox cbZoo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_filter_road_trip,container,false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this,view);

        initializeCheckboxes();

        variables = (Variable) getActivity().getApplicationContext();

        variables.setListTypes();

        cbAmusementPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbAmusementPark.isChecked()){
                    //variables.re
                    if (variables.existType("amusement_park")) {
                        return;
                    }
                    else {
                        variables.addType("amusement_park");
                    }
                }
                else {
                    if (variables.existType("amusement_park")) {
                        variables.removeType("amusement_park");
                    }
                    else {
                        return;
                    }
                }
            }
        });

        cbAquarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbAquarium.isChecked()){
                    if (variables.existType("aquarium")) {
                        return;
                    }
                    else {
                        variables.addType("aquarium");
                    }
                }
                else {
                    if (variables.existType("aquarium")) {
                        variables.removeType("aquarium");
                    }
                    else {
                        return;
                    }
                }
            }
        });

        cbArtGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbArtGallery.isChecked()){
                    if (variables.existType("art_gallery")) {
                        return;
                    }
                    else {
                        variables.addType("art_gallery");
                    }
                }
                else {
                    if (variables.existType("art_gallery")) {
                        variables.removeType("art_gallery");
                    }
                    else {
                        return;
                    }
                }
            }
        });

        cbCampground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbCampground.isChecked()){
                    if (variables.existType("campground")) {
                        return;
                    }
                    else {
                        variables.addType("campground");
                    }
                }
                else {
                    if (variables.existType("campground")) {
                        variables.removeType("campground");
                    }
                    else {
                        return;
                    }
                }
            }
        });

        cbMuseum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbMuseum.isChecked()){
                    if (variables.existType("museum")) {
                        return;
                    }
                    else {
                        variables.addType("museum");
                    }
                }
                else {
                    if (variables.existType("museum")) {
                        variables.removeType("museum");
                    }
                    else {
                        return;
                    }
                }
            }
        });

        cbPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbPark.isChecked()){
                    if (variables.existType("park")) {
                        return;
                    }
                    else {
                        variables.addType("park");
                    }
                }
                else {
                    if (variables.existType("park")) {
                        variables.removeType("park");
                    }
                    else {
                        return;
                    }
                }
            }
        });

        cbStadium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbStadium.isChecked()){
                    if (variables.existType("stadium")) {
                        return;
                    }
                    else {
                        variables.addType("stadium");
                    }
                }
                else {
                    if (variables.existType("stadium")) {
                        variables.removeType("stadium");
                    }
                    else {
                        return;
                    }
                }
            }
        });

        cbZoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbZoo.isChecked()){
                    if (variables.existType("zoo")) {
                        return;
                    }
                    else {
                        variables.addType("zoo");
                    }
                }
                else {
                    if (variables.existType("zoo")) {
                        variables.removeType("zoo");
                    }
                    else {
                        return;
                    }
                }
            }
        });

        //myTripsOrganizedPresenter = new MyTripsOrganizedPresenter(this , getContext());
        //myTripsOrganizedPresenter.getTripsById();

        return view;
    }

    public void initializeCheckboxes() {
        cbAmusementPark.setChecked(true);
        cbAquarium.setChecked(true);
        cbArtGallery.setChecked(true);
        cbCampground.setChecked(true);
        cbMuseum.setChecked(true);
        cbPark.setChecked(true);
        cbStadium.setChecked(true);
        cbZoo.setChecked(true);
    }
}
