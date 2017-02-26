package com.project.application.funnyroad.detailEndroit.view;

/**
 * Created by you on 23/02/2017.
 */

public interface IServiceDetailsPlace {

    public void showLoading(boolean bool);
    public void getInformationFailed(); // probleme lors de l'appel web service
    public void listPhotosEmpty();
    public void getInformationSuccess(); // connexion reussie
}
