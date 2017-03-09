package com.project.application.funnyroad.detailEndroit.view;

import android.graphics.Bitmap;

import com.project.application.funnyroad.addplace.model.Picture;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

/**
 * Created by you on 23/02/2017.
 */

public interface IServiceDetailsPlace {

    public void showLoading(boolean bool);
    public void getInformationFailed(String msg); // probleme lors de l'appel web service
    public void listPhotosEmpty();
    public void getInformationSuccess(); // connexion reussie
    public void uploadPlaceSuccess(Place place);
    public void deletePlaceSuccess();
    public void getListPictureModel(ArrayList<Picture> list);
    public void displayImage(Bitmap bitmap);
}
