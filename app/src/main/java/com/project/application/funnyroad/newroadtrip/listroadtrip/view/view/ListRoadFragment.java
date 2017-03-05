package com.project.application.funnyroad.newroadtrip.listroadtrip.view.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.login.model.User;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter.ListRoadTripAdapter;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter.PresenterListRoadTrip;
import com.project.application.funnyroad.newroadtrip.view.ActivityNewMRoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 03/02/2017.
 */

public class ListRoadFragment extends Fragment implements IServiceListRoad {

    @BindView(R.id.recycler_view_list_endroit)
    RecyclerView recycler_view_list_endroit;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    ListRoadTripAdapter listRoadTripAdapter;
    PresenterListRoadTrip presenterListRoadTrip;
    String departure;
    String arrival;
    String nameRoadTrip;
    ArrayList<Place> listPlaceChecked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_list_road,container,false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this,view);

        presenterListRoadTrip = new PresenterListRoadTrip(this);

        // recevoir le depart et l'arrivée
        departure = getActivity().getIntent().getStringExtra("DEPARTURE");
        arrival = getActivity().getIntent().getStringExtra("ARRIVAL");
        nameRoadTrip = getActivity().getIntent().getStringExtra("NAMEROADTRIP");

        // presenter qui appel le web service pour recevoir la liste des endroits entre ville depart et arrivee
        //presenterListRoadTrip.getListPlaces(departure, arrival);

        List<Place> listPlaces = new ArrayList<>();

        Place e1 = new Place("Tour effeil" ,"un truc de fou cet endroit" , 48.8584 , 2.2945);
        Place e2 = new Place("Palais beaux arts" ," le monde aux années 80" , 48.866667 , 2.333333);
        Place e3 = new Place("Parc asterix" ," joli endroit", 50.62925 , 3.057256000000052);

        listPlaces.add(e1); listPlaces.add(e2); listPlaces.add(e3);
        listRoadTripAdapter = new ListRoadTripAdapter(listPlaces);
        recycler_view_list_endroit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_list_endroit.setItemAnimator(new DefaultItemAnimator());
        recycler_view_list_endroit.setAdapter(listRoadTripAdapter);


        return view;
    }

    @OnClick(R.id.buttonAdd)
    public void sendCheckedBox(){
        listPlaceChecked = listRoadTripAdapter.listChecked;
        ArrayList<Integer> listEndroitCheckedId = new ArrayList<>();
        for(Place e : listPlaceChecked){
            listEndroitCheckedId.add(e.getId());
        }
        //RoadTrip roadTrip = new RoadTrip(nameRoadTrip , departure , arrival , listEndroitCheckedId , Utility.getIdUser(getActivity()));
        //presenterListRoadTrip.createRoadTrip(roadTrip);

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
    public void loadingList(ArrayList<Place> listPlaces) {
        listRoadTripAdapter = new ListRoadTripAdapter(listPlaces);
        recycler_view_list_endroit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_list_endroit.setItemAnimator(new DefaultItemAnimator());
        recycler_view_list_endroit.setAdapter(listRoadTripAdapter);
    }

    @Override
    public void gotToVisual(){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listPlaceChecked" , listPlaceChecked);
        Intent i = new Intent(getActivity() , ActivityNewMRoadTrip.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void errorLoading(String msg) {
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
