package com.project.application.funnyroad.login.view;

import com.project.application.funnyroad.login.model.User;

import java.util.ArrayList;

/**
 * Created by oraberkane on 02/02/2017.
 */

public interface IServiceLogin {

    public void isLoginSuccess(int userId);
    public void isLoginFailed(String msg);
    public void createRoadTrip();
    public void verifyExistingUser(ArrayList<User> listUsers);
}
