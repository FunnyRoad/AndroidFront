package com.example.oraberkane.appliplatine.login.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.oraberkane.appliplatine.MainActivity;
import com.example.oraberkane.appliplatine.R;
import com.example.oraberkane.appliplatine.login.presenter.PresenterLogin;
import com.example.oraberkane.appliplatine.register.view.ActivityRegister;

import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 01/02/2017.
 */

public class FragmentLogin extends Fragment implements IServiceLogin {



    /****************ATTRIBUTS*******************/
    @BindView(R.id.login_mail)
    public EditText mlogin_mail;

    @BindView(R.id.login_password)
    public EditText mlogin_password;

    @BindView(R.id.progressBar)
    public ProgressBar MprogressBar;


    private PresenterLogin mPresenterLogin;




    /***********************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this, view);

        mPresenterLogin = new PresenterLogin(this);


        return view;
    }




    /****************CLIC QUR LE BOUTON CONNEXION*******************/
    @OnClick(R.id.login_connexion)
    public void goToHome() {
        //appel de la fonction connect qui verifie l'email et le password et fait appel au web service
      //  mPresenterLogin.connect(mlogin_mail.getText().toString(), mlogin_password.getText().toString());
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);

    }



    /*****************CLIC SUR LE BOUTON INSCRIPTION**********************/

    @OnClick(R.id.login_register)
    public void goToRegister() {
        Intent intent = new Intent(getActivity(), ActivityRegister.class);
        startActivity(intent);
    }

    /***************************************/
    @Override
    public void showLoading(boolean bool) {
        if (bool) {
            MprogressBar.setVisibility(View.VISIBLE);
        } else {
            MprogressBar.setVisibility(View.GONE);
        }
    }


    /***********************************/
    @Override
    public void invalidMail(String msg) {
        mlogin_mail.setError(msg);
    }




    /***********************************/
    @Override
    public void invalidPassword(String msg) {
        mlogin_password.setError(msg);
    }



    /***********************************/
    @Override
    public void errorLogin(String msgError) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msgError);
        builder.setCancelable(true);
        builder.setPositiveButton("Réessayez", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    /***********************************/
    @Override
    public void isLoginSuccess() {
        //mPresenterLogin.fcmRegistration();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


}
