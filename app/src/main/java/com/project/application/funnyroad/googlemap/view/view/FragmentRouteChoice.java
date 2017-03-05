package com.project.application.funnyroad.googlemap.view.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.view.ActivityNewMRoadTrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 04/02/2017.
 */

public class FragmentRouteChoice extends Fragment implements OnMapReadyCallback {


   /* private EditText editDepart;
    private EditText editArrivee;
    private Button btnSearch;
*/

    /****************ATTRIBUTS*******************/
    @BindView(R.id.editDepart)
    public EditText meditDepart;
    @BindView(R.id.editArrivee)
    public EditText meditArrivee;
    @BindView(R.id.submit)
    Button buttonSubmit;
    @BindView(R.id.editTextNameRoadTrip)
    EditText editTextNameRoadTrip;

    private SupportMapFragment gMap;


    //On récupère les composants graphiques
 /*   editDepart = (EditText) findViewById(R.id.editDepart);
    editArrivee = (EditText) findViewById(R.id.editArrivee);
    btnRechercher = (Button) findViewById(R.id.btnSearch);*/



    /***********************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_routechoice, container, false);
        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this, view);

        gMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.choiceMap));

        //mPresenterLogin = new PresenterLogin(this);


        return view;
    }


    /****************CLIC QUR LE BOUTON CONNEXION*******************/
    @OnClick(R.id.btnSearch)
   // btnRechercher.setOnClickListener(new OnClickListener() {

         //{@inheritDoc}

       // @Override
       // public void onClick(final View v) {
       public void goToMap() {
            if("".equals(meditDepart.getText().toString().trim())) {
                Toast.makeText(getActivity(), "Merci de saisir un lieu de départ", Toast.LENGTH_SHORT).show();
            }
            else if("".equals(meditArrivee.getText().toString().trim())) {
                Toast.makeText(getActivity(), "Merci de saisir un lieu d'arrivée", Toast.LENGTH_SHORT).show();
            }
            else if ("".equals(editTextNameRoadTrip.getText().toString().trim())) {
                Toast.makeText(getActivity(), "Merci de saisir un nom du roadtrip", Toast.LENGTH_SHORT).show();
            }
            else {
                buttonSubmit.setVisibility(View.VISIBLE);
                gMap.getMapAsync(this);
            }
        }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        new ItineraireTask(getContext(), googleMap, meditDepart.getText().toString()
                , meditArrivee.getText().toString()).execute();

    }

    @OnClick(R.id.submit)
    public void goToNewRoadTrip() {
        Intent intent = new Intent(getActivity(), ActivityNewMRoadTrip.class);
        intent.putExtra("DEPARTURE", meditDepart.getText().toString());
        intent.putExtra("ARRIVAL", meditArrivee.getText().toString());
        intent.putExtra("NAMEROADTRIP", editTextNameRoadTrip.getText().toString());
        startActivity(intent);
    }
}
