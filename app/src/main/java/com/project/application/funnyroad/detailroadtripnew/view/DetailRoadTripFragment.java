package com.project.application.funnyroad.detailroadtripnew.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.addplace.view.AddPlaceActivity;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.detailroadtripnew.presenter.ItineraireTask;
import com.project.application.funnyroad.detailroadtripnew.presenter.ListEndroitAdapter;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import com.project.application.funnyroad.detailroadtripnew.presenter.DetailsRoadTripAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by you on 18/02/2017.
 */

public class DetailRoadTripFragment extends Fragment implements OnMapReadyCallback{

    @BindView(R.id.textViewDetailDepart)
    TextView textViewDetailDepart;
    @BindView(R.id.textViewDetailDestination)
    TextView textViewDetailDestination;
    @BindView(R.id.textViewDetailDescription)
    TextView textViewDetailDescription;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;

    RoadTrip roadTrip;

    @BindView(R.id.details_road_recyclerView)
    RecyclerView details_road_recyclerView;
    @BindView(R.id.recycler_view_list_endroit)
    RecyclerView recycler_view_list_endroit;

    private SupportMapFragment gMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_detail_road_trip, container, false);

        ButterKnife.bind(this, view);

        Intent intent = getActivity().getIntent();
        if (intent != null){
            roadTrip = (RoadTrip) intent.getSerializableExtra("roadTripSelected");
            textViewDetailDepart.setText(roadTrip.getBegin());
            textViewDetailDestination.setText(roadTrip.getDestination());
            textViewDetailDescription.setText(roadTrip.getDescription());
        }

        //mPresenterLogin = new PresenterLogin(this);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(200, 100, conf); // this creates a MUTABLE bitmap

        ArrayList<Bitmap> listTestPhotos = new ArrayList<>();
        listTestPhotos.add(bmp);listTestPhotos.add(bmp);listTestPhotos.add(bmp);
        roadTrip.setListPhotos(listTestPhotos);
        ArrayList<Bitmap> listPhotoRoad = roadTrip.getListPhotos();

        DetailsRoadTripAdapter mAdapter = new DetailsRoadTripAdapter(listPhotoRoad);
        details_road_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        details_road_recyclerView.setItemAnimator(new DefaultItemAnimator());
        details_road_recyclerView.setAdapter(mAdapter);

        /****** adapter de la list des endroits****/
        ArrayList<Place> l = new ArrayList<>();
        Place endr1 = new Place("Tour effeil" ,"un truc de fou cet endroit" , 48.8584 , 2.2945);
        Place endr2 = new Place("Palais beaux arts" ," le monde aux années 80" , 48.866667 , 2.333333);
        Place endr3 = new Place("Parc asterix" ," joli endroit", 50.62925 , 3.057256000000052);

        l.add(endr1);l.add(endr2);l.add(endr3);
        ListEndroitAdapter mListEndroitAdapter = new ListEndroitAdapter(l);
        recycler_view_list_endroit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_list_endroit.setItemAnimator(new DefaultItemAnimator());
        recycler_view_list_endroit.setAdapter(mListEndroitAdapter);

        roadTrip.setListPlace(l);

        gMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.mapRoadTrip));
        gMap.getMapAsync(this);

        ((WorkaroundMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapRoadTrip)).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });

        return view;
    }

    @OnClick(R.id.addPlace)
    public void goToAddPlace(){
        Intent intent = new Intent(this.getContext() , AddPlaceActivity.class);
        startActivity(intent);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        new ItineraireTask(this.getContext(), googleMap, roadTrip.getListPlace(),
                roadTrip.getBegin(), roadTrip.getDestination()).execute();
    }

}
