package com.project.application.funnyroad.home.friends.view;

import com.project.application.funnyroad.login.model.User;

import java.util.ArrayList;

/**
 * Created by you on 24/02/2017.
 */

public interface IServiceFriends {

    public void showLoading(boolean bool);
    public void getAllFriends(ArrayList<User> listUsers);
    public void listUsersEmpty();
    public void loadingListError(String msg);
}
