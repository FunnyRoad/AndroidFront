package com.project.application.funnyroad.register.presenter;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.register.service.IWebServiceRegister;
import com.project.application.funnyroad.register.view.IServiceRegister;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.RestAdapter;

/**
 * Created by oraberkane on 03/02/2017.
 */

public class PresenterRegister {

    private IServiceRegister mIServiceRegister;

 //initialisation du web service
    IWebServiceRegister mWebService = ConnexionWebService.restAdapter
                                        .create(IWebServiceRegister.class);


    public PresenterRegister(IServiceRegister iServiceRegister) {
        // mContext = context;
        mIServiceRegister = iServiceRegister;
    }



/******************************EMAIL****************************/
    public boolean verifyEmail(String email) {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        if (m.matches()){
            return true;
        }else {
            mIServiceRegister.emailInvalid("mail doit être sous forme: exemple@gmail.com");
            return false;
        }
    }




    /******************************PASSWORD****************************/
    public boolean verifyPassword(String pwd, String pwdConfirm) {
        if (pwd.length() < 6) {
            mIServiceRegister.passwordInvalid("le mot de passe doit contenir au minimum 6 caractères");
            return false;
        }

        if (pwd.equals(pwdConfirm)) {
            return true;
        }
        if (!(pwd.equals(pwdConfirm))) {
            mIServiceRegister.confirmPasswordInvalid("le mot de passe de confirmation ne correspond pas au mot de passe");
            return false;
        }
        return true;
    }





    /******************************ENREGISTREMENT****************************/
    public void register(String email, String name, String lastName, String pseudo, String birthDay, String pwd, String pwdConfirm) {
        if (verifyEmail(email) && verifyPassword(pwd, pwdConfirm)) {
           /* mIServiceRegister.showLoading(true);
            mWebService.createUser(name, lastName, adress, pwd, pwdConfirm, email, new Callback<String>() {
                @Override
                public void success(User user, Response response) {
                    mIServiceRegister.showLoading(false);
                    if (user.getId() != null ) {
                        //putId(mContext, id);
                        mIServiceRegister.registrationSuccess();
                    } else {
                        mIServiceRegister.registrationFailed("Ce mail existe déjà!");
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    mIServiceRegister.showLoading(false);
                    mIServiceRegister.registrationFailed("Impossible de se connecter à Internet\n\nVérifiez que vous avez activé votre connexion");
                }
            });*/


            mIServiceRegister.showLoading(false);
            mIServiceRegister.registrationSuccess();

        }
    }








/*
    public void fcmRegistration() {

        FcmService fcmService = new FcmService(MySharedPreferences.getStoredId(mContext));
        fcmService.onTokenRefresh();
    }*/


    // cette fonction sauvegarde id de l'utilisateur
   /* public void putId(Context ctx, String userId) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences("myPref", MODE_PRIVATE).edit();
        editor.putString("idName", userId);
        editor.commit();

    }*/



}
