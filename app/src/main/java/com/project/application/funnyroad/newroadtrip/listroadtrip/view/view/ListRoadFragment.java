package com.project.application.funnyroad.newroadtrip.listroadtrip.view.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter.ListRoadTripAdapter;
import com.project.application.funnyroad.newroadtrip.view.ActivityNewMRoadTrip;
import com.project.application.funnyroad.newroadtrip.view.PagerAdapter;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Endroit;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.view.VisualRoadTripFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 03/02/2017.
 */

public class ListRoadFragment extends Fragment {

    @BindView(R.id.recycler_view_list_endroit)
    RecyclerView recycler_view_list_endroit;
    ListRoadTripAdapter listRoadTripAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_list_road,container,false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this,view);

        List<Endroit> listEndroits = new ArrayList<>();

        Endroit e1 = new Endroit("Tour effeil" ,"un truc de fou cet endroit" , 48.8584 , 2.2945);
        Endroit e2 = new Endroit("Palais beaux arts" ," le monde aux années 80" , 48.866667 , 2.333333);
        Endroit e3 = new Endroit("Parc asterix" ," joli endroit", 50.62925 , 3.057256000000052);

        listEndroits.add(e1); listEndroits.add(e2); listEndroits.add(e3);

        listRoadTripAdapter = new ListRoadTripAdapter(listEndroits);
        recycler_view_list_endroit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_list_endroit.setItemAnimator(new DefaultItemAnimator());
        recycler_view_list_endroit.setAdapter(listRoadTripAdapter);

        return view;
    }

    @OnClick(R.id.buttonAdd)
    public void sendCheckedBox(){
        ArrayList<Endroit> listEndroitChecked = listRoadTripAdapter.listChecked;
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listEndroitChecked" , listEndroitChecked);
        Intent i = new Intent(getActivity() , ActivityNewMRoadTrip.class);
        i.putExtras(bundle);
        startActivity(i);
    }
}
