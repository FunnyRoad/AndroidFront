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
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
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

public class AllRoadTripFragment extends Fragment implements IServiceAllRoadTrip,GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    @BindView(R.id.recycler_view_all_road_trip)
    RecyclerView recycler_view_all_road_trip;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewListEmpty)
    TextView textViewListEmpty;
    private GoogleApiClient mGoogleApiClient;
    private PresenterAllRoadTrip presenterAllRoadTrip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_road_trip_fragment,container,false);

        ButterKnife.bind(this, view );

        presenterAllRoadTrip = new PresenterAllRoadTrip(this);
        presenterAllRoadTrip.getAllRoadTrip();

        mGoogleApiClient = new GoogleApiClient.Builder(this.getActivity())
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .build();
        return view;
    }

    @Override
    public void showLoading(boolean bool) {
        if(bool){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void getAllRoadTrip(ArrayList<RoadTrip> listRoadTrip) {
        AllRoadTripAdapter mAdapter = new AllRoadTripAdapter(listRoadTrip , getActivity() ,mGoogleApiClient);
        recycler_view_all_road_trip.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_all_road_trip.setItemAnimator(new DefaultItemAnimator());
        recycler_view_all_road_trip.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

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

    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this.getContext(), "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onStart() {
        if( ! mGoogleApiClient.isConnected()){
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenterAllRoadTrip.getAllRoadTrip();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


}
