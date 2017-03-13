package com.project.application.funnyroad.newroadtrip.listroadtrip.view.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.Variable;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter.ListPlacesAdapter;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter.PresenterListRoadTrip;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.utils.PlaceJSONParser;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.utils.CustomPlace;
import com.project.application.funnyroad.newroadtrip.view.ActivityCreateNewRoadTrip;
import com.project.application.funnyroad.newroadtrip.view.ActivityNewMRoadTrip;
import com.project.application.funnyroad.newroadtrip.view.FragmentCreateNewRoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.view.VisualRoadTripFragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 03/02/2017.
 */

public class ListPlacesFragment extends Fragment {

    @BindView(R.id.recycler_view_list_places)
    RecyclerView recycler_view_list_places;

    GoogleApiClient mGoogleApiClient;

    ListPlacesAdapter listPlacesAdapter;

    List<String> listUrl = new ArrayList<String>();
    List<LatLng> listPointsOnTrack = new ArrayList<LatLng>();

    List<LatLng> listPointsForNewTrack = new ArrayList<LatLng>();

    List<String> listId = new ArrayList<String>();
    List<CustomPlace> listPlaces = new ArrayList<CustomPlace>();

    List<CustomPlace> listPlacesWithFilter = new ArrayList<CustomPlace>();

    Location locationD = new Location("Departure");
    Location locationA = new Location("Arrival");

    Variable variables;

    Place placeDeparture;
    Place placeArrival;

    //PresenterListRoadTrip presenterListRoadTrip = new PresenterListRoadTrip();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_list_places,container,false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this,view);

        variables = (Variable) getActivity().getApplicationContext();

        this.listPlaces.clear();
        this.listId.clear();
        variables.getListPlaceChosen().clear();

        this.listPointsOnTrack = variables.getPlacesOnTrack();

        this.setThePlaces();

        super.onCreate(savedInstanceState);

        variables.setListTypes();

        return view;
    }

    public void setThePlaces() {


        for (int i=0;i<this.listPointsOnTrack.size();i++) {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
            sb.append("location=" + this.listPointsOnTrack.get(i).latitude + "," + this.listPointsOnTrack.get(i).longitude);
            sb.append("&radius=5000");
            sb.append("&types=amusement_park|aquarium|art_gallery|campground|museum|park|stadium|zoo");
            sb.append("&sensor=true");
            sb.append("&key=AIzaSyA3xM5CozOoOzJ_jpKd_p0s6F84KfLrD3A");

            listUrl.add(sb.toString());
        }

        // Invokes the "doInBackground()" method of the class PlaceTask
        for (int i=0;i<this.listUrl.size();i++) {
            //for (int j=0;j<)
            startMyTask(new PlacesTask(), this.listUrl.get(i));
        }
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }

        return data;
    }

    /** A class, to download Google Places */
    private class PlacesTask extends AsyncTask<String, Integer, String> {

        String data = null;

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result) {
            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParseTask
            startMyTask(new ParserTask(),result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>> {

        JSONObject jObject;

        // Invoked by execute() method of this object
        @Override
        protected List<HashMap<String,String>> doInBackground(String... jsonData) {

            List<HashMap<String,String>> places = null;
            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try {
                jObject = new JSONObject(jsonData[0]);

                // Getting the parsed data as a List construct
                places = placeJsonParser.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(List<HashMap<String,String>> list) {

            for (int i=0; i<list.size(); i++) {
                HashMap<String,String> hmPlace = list.get(i);

                if (!hmPlace.isEmpty()) {
                    String placeId = hmPlace.get("place_id");
                    String placeName = hmPlace.get("place_name");
                    String placeGrade = hmPlace.get("place_grade");
                    String placeType = hmPlace.get("place_type");

                    if (!listId.contains(placeId)) {
                        listPlaces.add(new CustomPlace(placeId, placeName, placeGrade, placeType));
                        listId.add(placeId);
                    }
                }
            }

            //variables.setListPlacesFromQuery(listPlaces);
            //variables.setListPlacesWithFilter(listPlacesWithFilter);
            listPlacesAdapter = new ListPlacesAdapter(getActivity(),listPlaces);
            recycler_view_list_places.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recycler_view_list_places.setItemAnimator(new DefaultItemAnimator());
            recycler_view_list_places.setAdapter(listPlacesAdapter);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    void startMyTask(AsyncTask asyncTask, String... params) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        else
            asyncTask.execute(params);
    }

    @OnClick(R.id.buttonAdd)
    public void sendCheckedBox(){
        final ArrayList<CustomPlace> listPlacesChecked = listPlacesAdapter.listChecked;
        for (int i=0;i<listPlacesChecked.size();i++) {
            variables.getListPlaceChosen().add(listPlacesChecked.get(i));
            PendingResult<PlaceBuffer> placeTemp = Places.GeoDataApi.getPlaceById(variables.getGoogleApiClient(), listPlacesChecked.get(i).getPlaceId());
            placeTemp.setResultCallback(new ResultCallback<PlaceBuffer>() {
                @Override
                public void onResult(@NonNull PlaceBuffer places) {
                    if (!places.getStatus().isSuccess()) {
                        return;
                    }
                    listPointsForNewTrack.add(places.get(0).getLatLng());
                }
            });
        }
        //variables.setIdPlacesOnTrack(listPlacesChecked);
        variables.setPointsToDraw(listPointsForNewTrack);

        Intent intent = new Intent(getActivity(), ActivityCreateNewRoadTrip.class);

        startActivity(intent);
    }
}
