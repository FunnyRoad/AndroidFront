package com.project.application.funnyroad.home.allroadtrip.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.home.allroadtrip.presenter.AllRoadTripAdapter;
import com.project.application.funnyroad.home.allroadtrip.presenter.PresenterAllRoadTrip;
import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by you on 23/02/2017.
 */

public class AllRoadTripFragment extends Fragment implements IServiceAllRoadTrip{

    @BindView(R.id.recycler_view_all_road_trip)
    RecyclerView recycler_view_all_road_trip;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewListEmpty)
    TextView textViewListEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_road_trip_fragment,container,false);

        Log.d("ALLroadtrip", "onCreateView: ");
        ButterKnife.bind(this, view );

        PresenterAllRoadTrip presenterAllRoadTrip = new PresenterAllRoadTrip(this);
        presenterAllRoadTrip.getAllRoadTrip();

        /*RoadTrip roadTrip1 = new RoadTrip("lille" , "lyon" , "découvrir et s'amuser");
        RoadTrip roadTrip2 = new RoadTrip("lens" , "marseille" , "découvrir et s'amuser");
        RoadTrip roadTrip3 = new RoadTrip("sochaux" , "montpellier" , "découvrir et s'amuser");
        ArrayList<RoadTrip> listRoadTrip = new ArrayList<>();
        listRoadTrip.add(roadTrip1);listRoadTrip.add(roadTrip2);listRoadTrip.add(roadTrip3);

        AllRoadTripAdapter mAdapter = new AllRoadTripAdapter(listRoadTrip);
        recycler_view_all_road_trip.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_all_road_trip.setItemAnimator(new DefaultItemAnimator());
        recycler_view_all_road_trip.setAdapter(mAdapter);
        */

        return view;
    }

    @Override
    public void showLoading(boolean bool) {
        if(bool) {
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void getAllRoadTrip(ArrayList<RoadTrip> listRoadTrip) {
        AllRoadTripAdapter mAdapter = new AllRoadTripAdapter(listRoadTrip);
        recycler_view_all_road_trip.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_all_road_trip.setItemAnimator(new DefaultItemAnimator());
        recycler_view_all_road_trip.setAdapter(mAdapter);
    }

    @Override
    public void listRoadTripEmpty() {
        textViewListEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadingListError(String msg) {
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
