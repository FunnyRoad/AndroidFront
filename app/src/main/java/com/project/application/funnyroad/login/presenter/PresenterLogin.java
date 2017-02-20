package com.project.application.funnyroad.login.presenter;

import com.project.application.funnyroad.login.service.IWebServiceLogin;
import com.project.application.funnyroad.login.view.IServiceLogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.RestAdapter;

/**
 * Created by oraberkane on 02/02/2017.
 */



public class PresenterLogin {


    IWebServiceLogin iWebServiceLogin = new RestAdapter.Builder()
            .setEndpoint("lien du webService")
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build()
            .create(IWebServiceLogin.class);





    private IServiceLogin mIServiceLogin;


    public PresenterLogin(IServiceLogin iLoginService) {
        // mContext = context;
        mIServiceLogin = iLoginService;
    }



    public void connect(String mail, String password) {
        if (verifyEmail(mail) && verifyPassword(password)) {
            /*
            mIServiceLogin.showLoading(true);
            iWebServiceLogin.userLogin(mail, password, new Callback<UUID>() {
                @Override //connexion webService OK
                public void success(UUID id, Response response) {
                    mIServiceLogin.showLoading(false);
                    mIServiceLogin.isLoginSuccess();

                }
                @Override //connexion webService KO
                public void failure(RetrofitError error) {
                    mIServiceLogin.showLoading(false);
                    mIServiceLogin.errorLogin("Impossible de se connecter à Internet\n\nVérifiez que vous avez activé votre connexion");
                }
            });*/


            mIServiceLogin.showLoading(false);
            mIServiceLogin.isLoginSuccess();

        }
    }





    public boolean verifyEmail(String email) {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            mIServiceLogin.invalidMail("mail invalide , exemple@gmail.com");
            return false;
        }


    }


    public boolean verifyPassword(String pwd) {
        Pattern p = Pattern.compile("^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$");
        Matcher m = p.matcher(pwd);
        if (pwd.length() < 6) {
            mIServiceLogin.invalidPassword("le mot de passe doit contenir au minimum 6 caractères");
            return false;
        }
        if (m.matches()) {
            return true;
        } else {
            mIServiceLogin.invalidPassword("le mot de passe doit contenir au moins une lettre, un chiffre et doit être supérieurs à 6 caractères");
            return false;
        }
    }



}
