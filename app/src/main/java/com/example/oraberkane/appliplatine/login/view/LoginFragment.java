package com.example.oraberkane.appliplatine.login.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oraberkane.appliplatine.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 01/02/2017.
 */

public class LoginFragment extends Fragment implements IServiceLogin {

    @BindView(R.id.login_mail)
    public Toolbar mlogin_mail;

    @BindView(R.id.login_password)
    public Toolbar mlogin_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this, view);

        return view;
    }



    @OnClick(R.id.login_connexion)
    public void goToHome() {
        //appel de la fonction connect qui verifie l'email et le password et fait appel au web service
       // mLoginPresenter.connect(mlogin_mail.getText().toString(), mlogin_password.getText().toString());
    }


    @Override
    public void showLoading(boolean bool) {

    }

    @Override
    public void invalidMail(String msg) {

    }

    @Override
    public void invalidPassword(String msg) {

    }

    @Override
    public void errorLogin(String msgError) {

    }

    @Override
    public void isLoginSuccess() {

    }

    @Override
    public void errorPassword() {

    }
}
