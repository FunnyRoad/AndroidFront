package com.project.application.funnyroad.home.roadtripfollow.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.android.gms.location.places.Places;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.home.allroadtrip.presenter.AllRoadTripAdapter;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.home.roadtripfollow.presenter.FollowRoadTripAdapter;
import com.project.application.funnyroad.home.roadtripfollow.presenter.PresenterRoadTripFollow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oa on 23/02/2017.
 */

public class FollowRoadTripFragment extends Fragment implements IServiceFollowRoadTrip ,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{

    private PresenterRoadTripFollow presenterRoadTripFollow;
    private GoogleApiClient mGoogleApiClient;
    @BindView(R.id.recyclerViewFollow)
    RecyclerView recyclerViewFollow;
    @BindView(R.id.textViewListEmpty)
    TextView textViewListEmpty;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_road_trip_follow_fragment,container,false);

        ButterKnife.bind(this , view);

        presenterRoadTripFollow = new PresenterRoadTripFollow(this);
        presenterRoadTripFollow.getFollowedRoadTrip(Utility.getIdUser(getActivity()));

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
    public void showAllRoadTripFollow(ArrayList<RoadTrip> listRoadTrips) {
        FollowRoadTripAdapter followRoadTripAdapter = new FollowRoadTripAdapter(listRoadTrips, getActivity() , mGoogleApiClient , this);
        recyclerViewFollow.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewFollow.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFollow.setAdapter(followRoadTripAdapter);
        followRoadTripAdapter.notifyDataSetChanged();
    }

    @Override
    public void listUsersEmpty() {
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
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this.getContext(), "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        if( ! mGoogleApiClient.isConnected()){
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenterRoadTripFollow.getFollowedRoadTrip(Utility.getIdUser(getActivity()));
    }
}
