package com.project.application.funnyroad.newroadtrip.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.Variable;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter.ListPlacesAdapter;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.utils.CustomPlace;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sameur on 10/03/2017.
 */

public class FragmentCreateNewRoadTrip extends Fragment {

    //ATTRIBUTES

    @BindView(R.id.editTextRTTitle)
    EditText editTextRTTitle;
    @BindView(R.id.placesFromRT)
    RecyclerView recyclerViewPlaces;
    @BindView(R.id.addRT)
    Button buttonAddRT;

    private ListPlacesAdapter listPlacesAdapter;

    private List<CustomPlace> listPlacesChosen;

    private Variable variables;

    //METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        variables = (Variable) getActivity().getApplicationContext();
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_create_road_trip, container, false);

        this.listPlacesChosen = variables.getListPlaceChosen();

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this, view);

        listPlacesAdapter = new ListPlacesAdapter(getActivity(),this.listPlacesChosen);
        recyclerViewPlaces.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewPlaces.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlaces.setAdapter(listPlacesAdapter);

        return view;
    }
    /*
    @OnClick(R.id.addRT)
    public void createRoadTrip
    */
}
