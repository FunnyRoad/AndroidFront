package com.project.application.funnyroad.login.view;

/**
 * Created by oraberkane on 02/02/2017.
 */

public interface IServiceLogin {

    public void showLoading(boolean bool);

    public void invalidMail( String msg);

    public void invalidPassword(String msg);

    public void errorLogin(String msgError);

    public void isLoginSuccess();


}
