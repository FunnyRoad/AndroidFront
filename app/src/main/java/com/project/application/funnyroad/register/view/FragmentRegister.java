package com.project.application.funnyroad.register.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.project.application.funnyroad.MainActivity;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.login.view.ActivityLogin;
import com.project.application.funnyroad.register.presenter.PresenterRegister;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 02/02/2017.
 */

public class FragmentRegister extends Fragment implements IServiceRegister {


    /****************COMPOSANTS*******************/
    @BindView(R.id.register_name)
    EditText mregister_name;

    @BindView(R.id.register_lastName)
    EditText mregister_lastName;

    @BindView(R.id.register_password)
    EditText mregister_password;

    @BindView(R.id.register_passwordConfirm)
    EditText mregister_passwordConfirm;

    @BindView(R.id.register_ok)
    Button mregister_ok;

    @BindView(R.id.register_cancel)
    Button mregister_cancel;

    @BindView(R.id.register_adress_email)
    EditText mregister_adress_email; // l'email de l'utilisateur

    @BindView(R.id.progressBar)
    ProgressBar mprogressBar; // l'email de l'utilisateur

    /****************PRESENTER*******************/
    private PresenterRegister mPresenterRegisterLogin;




    /*****************ONCREATE******************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register_login, container, false);
        ButterKnife.bind(this, view);

        mPresenterRegisterLogin = new PresenterRegister(this);

        return view;
    }


    /****************CLIC QUR LE BOUTON OK*******************/
    @OnClick(R.id.register_ok)
    public void addUser() {
        //appel de la fonction connect qui verifie l'email et le password et fait appel au web service
        mPresenterRegisterLogin.register(mregister_name.getText().toString(), mregister_lastName.getText().toString(), mregister_adress_email.getText().toString(), mregister_password.getText().toString(), mregister_passwordConfirm.getText().toString(),  mregister_adress_email.getText().toString() );
    //    Intent intent = new Intent(getActivity(), ActivityLogin.class);
      //  startActivity(intent);


    }



    /*****************CLIC SUR LE BOUTON Annuler**********************/

    @OnClick(R.id.register_cancel)
    public void goToRegister() {
        Intent intent = new Intent(getActivity(), ActivityLogin.class);
        startActivity(intent);
    }



    /***************************************/
    @Override
    public void emailInvalid(String msg) {
        mregister_adress_email.setError(msg);
    }



    /***************************************/
    @Override
    public void passwordInvalid(String msg) {
        mregister_password.setError(msg);
    }



    /***************************************/
    @Override
    public void confirmPasswordInvalid(String msg) {
        mregister_passwordConfirm.setError(msg);
    }



    /***************************************/
    @Override
    public void showLoading(boolean bool) {
        if (bool) {
            mprogressBar.setVisibility(View.VISIBLE);
        } else
            mprogressBar.setVisibility(View.GONE);
    }



    /***************************************/
    @Override
    public void registrationSuccess() {
        // on met a jour le token
     //   registerPresenter.fcmRegistration();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }



    /***************************************/
    @Override
    public void registrationFailed(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
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


}
