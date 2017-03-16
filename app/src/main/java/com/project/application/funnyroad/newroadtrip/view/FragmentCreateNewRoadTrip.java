package com.project.application.funnyroad.newroadtrip.view;

import android.content.Intent;
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
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.home.model.Departure;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.home.view.ActivityHome2;
import com.project.application.funnyroad.newroadtrip.Variable;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter.ListPlacesAdapter;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter.PresenterListRoadTrip;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.utils.CustomPlace;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.view.IServiceListRoad;
import com.project.application.funnyroad.newroadtrip.model.RoadTripSent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sameur on 10/03/2017.
 */

public class FragmentCreateNewRoadTrip extends Fragment implements IServiceListRoad {

    //ATTRIBUTES

    @BindView(R.id.editTextRTTitle)
    EditText editTextRTTitle;
    @BindView(R.id.placesFromRT)
    RecyclerView recyclerViewPlaces;
    @BindView(R.id.addRT)
    Button buttonAddRT;

    private PresenterListRoadTrip presenterListRoadTrip;

    private ListPlacesAdapter listPlacesAdapter;

    private List<CustomPlace> listPlacesChosen;

    private List<Integer> listIdPlacesChosen;

    private RoadTripSent roadTrip;

    private Variable variables;

    //METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        variables = (Variable) getActivity().getApplicationContext();
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_create_road_trip, container, false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this, view);

        this.listPlacesChosen = variables.getListPlaceChosen();
        /*
        for (int i=0;i<listPlacesChosen.size();i++) {
            listIdPlacesChosen.add(listPlacesChosen.get(i))
        }
        */

        presenterListRoadTrip = new PresenterListRoadTrip(this);

        listPlacesAdapter = new ListPlacesAdapter(getActivity(),this.listPlacesChosen);
        recyclerViewPlaces.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewPlaces.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlaces.setAdapter(listPlacesAdapter);

        return view;
    }

    @OnClick(R.id.addRT)
    public void createRoadTrip() {
        roadTrip = new RoadTripSent(editTextRTTitle.getText().toString(),
                Utility.getIdUser(getActivity()),
                new Departure(variables.getPlaceDeparture().getId(),variables.getPlaceDeparture().getLatLng().latitude,variables.getPlaceDeparture().getLatLng().longitude),
                variables.getPlaceArrival().getId(),
                new ArrayList<Integer>(),
                new ArrayList<Integer>()
        );
        presenterListRoadTrip.createRoadTrip(roadTrip);
    }

    @Override
    public void showLoading(boolean bool) {

    }

    @Override
    public void createRoadTrip(RoadTrip roadTrip) {
        Intent intent = new Intent(getContext() , ActivityHome2.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void errorLoading(String msg) {

    }
}
