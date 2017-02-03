package com.example.oraberkane.appliplatine.lieu.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oraberkane.appliplatine.R;

/**
 * Created by sameur on 03/02/2017.
 */

public class LieuxFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Retourne votre fichier layout
        // Changer R.layout.yourlayoutfilename pour vos fragments
        return inflater.inflate(R.layout.lieux_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Vous pouvez changer le titre dans la toolbar de vos differents fragments
        getActivity().setTitle("Lieux");
    }
}
