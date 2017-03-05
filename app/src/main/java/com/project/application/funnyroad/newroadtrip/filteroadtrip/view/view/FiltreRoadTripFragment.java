package com.project.application.funnyroad.newroadtrip.filteroadtrip.view.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.filteroadtrip.view.presenter.PresenterFilter;
import com.project.application.funnyroad.newroadtrip.view.ActivityNewMRoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 03/02/2017.
 */

public class FiltreRoadTripFragment extends Fragment implements IServiceFiltreRoadTrip{

    @BindView(R.id.checkBoxMonument)
    CheckBox checkBoxMonument;
    @BindView(R.id.checkBoxMuseum)
    CheckBox checkBoxMuseum;
    @BindView(R.id.checkBox3)
    CheckBox checkBox3;
    @BindView(R.id.checkBox4)
    CheckBox checkBox4;
    @BindView(R.id.checkBox5)
    CheckBox checkBox5;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    PresenterFilter presenterFilter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_filtre_road,container,false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this,view);

        presenterFilter = new PresenterFilter(this);

        return view;
    }

    @OnClick(R.id.buttonFilter)
    public void filter(){
        ArrayList<String> listFilterChecked = new ArrayList<>();
        if (checkBoxMonument.isChecked()){
            listFilterChecked.add(checkBoxMonument.getText().toString());
        }
        if (checkBoxMuseum.isChecked()){
            listFilterChecked.add(checkBoxMuseum.getText().toString());
        }
        if (checkBox3.isChecked()){
            listFilterChecked.add(checkBox3.getText().toString());
        }
        if (checkBox4.isChecked()){
            listFilterChecked.add(checkBox4.getText().toString());
        }
        if (checkBox5.isChecked()){
            listFilterChecked.add(checkBox5.getText().toString());
        }

        presenterFilter.getPlacesByFilters(listFilterChecked);

    }

    @Override
    public void showLoading(boolean bool) {
        if (bool) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void gotToListRoadTripWithNewListPlaces(ArrayList<Place> places) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listPlaceByType" , places);
        Intent i = new Intent(getActivity() , ActivityNewMRoadTrip.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void errorSend(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("Fermer" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
