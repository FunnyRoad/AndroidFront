package com.project.application.funnyroad.profil.view.view;

import com.project.application.funnyroad.login.model.User;

/**
 * Created by you on 28/02/2017.
 */

public interface IServiceProfil {

    public void showLoading(boolean bool);
    public void showInformationsUser( User user);
    public void errorGetInformations(String msg);
}
