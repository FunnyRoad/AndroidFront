package com.project.application.funnyroad.register.view;

/**
 * Created by oraberkane on 02/02/2017.
 */

public interface IServiceRegister {


    public void emailInvalid(String msg);
    public void passwordInvalid(String msg);
    public void confirmPasswordInvalid(String msg);
    public void showLoading(boolean bool);
    public void registrationSuccess();
    public void registrationFailed(String msg);

}
