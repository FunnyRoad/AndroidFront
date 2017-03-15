package com.project.application.funnyroad.detailEndroit.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.addplace.model.Picture;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.detailEndroit.presenter.DetailsEndroitAdapter;
import com.project.application.funnyroad.detailEndroit.presenter.PresenterDetailPlace;
import com.project.application.funnyroad.detailroadtripnew.view.DetailRoadTripActivity;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oa on 20/02/2017.
 */

public class DetailEndroitFragment extends Fragment implements AdapterView.OnItemSelectedListener , IServiceDetailsPlace{

    @BindView(R.id.textViewNameEndroit)
    EditText textViewNameEndroit;
    @BindView(R.id.textViewDescriptionEndroit)
    EditText textViewDescriptionEndroit;
    @BindView(R.id.spinnerDetailPlace)
    Spinner spinnerDetailPlace;
    @BindView(R.id.recycler_view_list_photo_endroit)
    RecyclerView recycler_view_list_photo_endroit;
    @BindView(R.id.buttonUploadPlace)
    Button buttonUploadPlace;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewListPhotoEmpty)
    TextView textViewListPhotoEmpty;
    @BindView(R.id.buttonDeletePlace)
    Button buttonDeletePlace;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    private Place place;
    private int roadTripOwner;
    private int roadTripId;
    private PresenterDetailPlace presenterDetailPlace;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_detail_endroit, container, false);

        ButterKnife.bind(this, view);

        presenterDetailPlace = new PresenterDetailPlace(this);

        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerDetailPlace.setAdapter(adapter);

        spinnerDetailPlace.setOnItemSelectedListener(this);

        Intent i = getActivity().getIntent();
        if (i != null) {
            place = (Place) i.getSerializableExtra("endroitSelected");
            roadTripOwner = i.getIntExtra("roadTripOwner", -1);
            roadTripId = i.getIntExtra("roadTripId" , -1);

            textViewNameEndroit.setText(place.getName());
            textViewDescriptionEndroit.setText(place.getDescription());
            spinnerDetailPlace.setSelection(adapter.getPosition(place.getType()));

            presenterDetailPlace.getPictures(place.getId());

            if (roadTripOwner != Utility.getIdUser(getActivity())){
                textViewNameEndroit.setEnabled(false);
                textViewDescriptionEndroit.setEnabled(false);
                spinnerDetailPlace.setEnabled(false);
                ratingBar.setVisibility(View.GONE);
            }
            else{
                textViewNameEndroit.setEnabled(true);
                textViewDescriptionEndroit.setEnabled(true);
                spinnerDetailPlace.setEnabled(true);

                buttonDeletePlace.setVisibility(View.VISIBLE);
                buttonUploadPlace.setVisibility(View.VISIBLE);
            }

        }

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @OnClick(R.id.buttonUploadPlace)
    public void uploadPlace(){
        Place newPlace = new Place( place.getId() , textViewNameEndroit.getText().toString(), place.getLatitude() ,
                place.getLongitude(), textViewDescriptionEndroit.getText().toString() ,ratingBar.getRating() , (String) spinnerDetailPlace.getSelectedItem());

        presenterDetailPlace.uploadPlace(newPlace);
    }

    @OnClick(R.id.buttonDeletePlace)
    public void deletePlace(){
        presenterDetailPlace.deletePlace(roadTripId , place.getId());
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
    public void getInformationFailed(String msg) {
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
    public void listPhotosEmpty() {
        textViewListPhotoEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void getInformationSuccess(Place place) {
        presenterDetailPlace.ratePlace(place.getId() , ratingBar.getRating());
    }

    @Override
    public void uploadPlaceSuccess(Place place) {
        if (roadTripOwner != Utility.getIdUser(getActivity())){
            textViewNameEndroit.setEnabled(false);
            textViewDescriptionEndroit.setEnabled(false);
            spinnerDetailPlace.setEnabled(false);
            ratingBar.setVisibility(View.GONE);
        }
        else{
            textViewNameEndroit.setEnabled(true);
            textViewDescriptionEndroit.setEnabled(true);
            spinnerDetailPlace.setEnabled(true);
            ratingBar.setVisibility(View.VISIBLE);
            buttonUploadPlace.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void deletePlaceSuccess() {
        Intent intent = new Intent(this.getActivity() , DetailRoadTripActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public void getListPictureModel(ArrayList<Picture> list) {
        DetailsEndroitAdapter mAdapter = new DetailsEndroitAdapter(list , getContext());
        recycler_view_list_photo_endroit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_list_photo_endroit.setItemAnimator(new DefaultItemAnimator());
        recycler_view_list_photo_endroit.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayImage(Bitmap bitmap) {

    }
}
