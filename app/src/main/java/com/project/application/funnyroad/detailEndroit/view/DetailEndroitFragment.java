package com.project.application.funnyroad.detailEndroit.view;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailEndroit.presenter.DetailsEndroitAdapter;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oa on 20/02/2017.
 */

public class DetailEndroitFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    @BindView(R.id.textViewNameEndroit)
    EditText textViewNameEndroit;
    @BindView(R.id.textViewDescriptionEndroit)
    EditText textViewDescriptionEndroit;
    @BindView(R.id.spinnerDetailPlace)
    Spinner spinnerDetailPlace;
    @BindView(R.id.recycler_view_list_photo_endroit)
    RecyclerView recycler_view_list_photo_endroit;

    Place place;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_detail_endroit, container, false);

        ButterKnife.bind(this, view);

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
        }

        textViewNameEndroit.setText(place.getName());
        textViewDescriptionEndroit.setText(place.getDescription());
        spinnerDetailPlace.setSelection(adapter.getPosition(place.getType()));

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(200, 100, conf); // this creates a MUTABLE bitmap

        ArrayList<Bitmap> list = new ArrayList<>();
        list.add(bmp); list.add(bmp);list.add(bmp);
       // place.setListPhotos(list);
        DetailsEndroitAdapter mAdapter = new DetailsEndroitAdapter(list);
        recycler_view_list_photo_endroit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_list_photo_endroit.setItemAnimator(new DefaultItemAnimator());
        recycler_view_list_photo_endroit.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
