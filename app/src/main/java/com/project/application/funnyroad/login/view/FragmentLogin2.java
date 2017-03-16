package com.project.application.funnyroad.login.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.home.model.Departure;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.home.view.ActivityHome2;
import com.project.application.funnyroad.login.model.User;
import com.project.application.funnyroad.login.presenter.PresenterLogin;
import com.project.application.funnyroad.newroadtrip.Variable;
import com.project.application.funnyroad.profil.view.presenter.PresenterProfil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by you on 27/02/2017.
 */

public class FragmentLogin2 extends Fragment implements GoogleApiClient.OnConnectionFailedListener, IServiceLogin{

    private static final String TAG = "FragmentLogin2";
    private static final int RC_SIGN_IN = 007;

    private Variable variable;
    private static GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    @BindView(R.id.llProfile)
    LinearLayout llProfileLayout;
    @BindView(R.id.imgProfilePic)
    ImageView imgProfilePic;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtEmail)
    TextView txtEmail;
    @BindView(R.id.btn_sign_in)
    SignInButton btnSignIn;
    @BindView(R.id.btn_sign_out)
    Button btnSignOut;
    @BindView(R.id.btn_revoke_access)
    Button btnRevokeAccess;

    PresenterLogin presenterLogin;
    private String firebaseIdRetrieve;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_login_2, container, false);
        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this, view);

        presenterLogin = new PresenterLogin(this);

        variable = (Variable) getActivity().getApplicationContext();
        /*Departure d1 = new Departure(50.633333 , 3.066667 , null);
        Departure d2 = new Departure( 48.856614 , 2.35222199 , null);
        Departure d3 = new Departure( 43.610769 , 3.87671599 , null);
        Departure d4 = new Departure( 50.3500000 , 3.5333300 , null);

        ArrayList<Integer> listPlaces = new ArrayList<>();
        listPlaces.add(4);
        listPlaces.add(3);

        RoadTrip roadTrip = new RoadTrip("RTTest1" , 14 , d1 , "ChIJgcpR9-gnVQ0RiXo5ewOGY3k" , listPlaces, null );
        RoadTrip roadTrip2 = new RoadTrip("RTTest2" , 14 , d2 , "ChIJ_1J17G-7rhIRMBBBL5z2BgQ", listPlaces, null );
        RoadTrip roadTrip3 = new RoadTrip("RTTest3" , 61 , d3 , "ChIJsZ3dJQevthIRAuiUKHRWh60" , listPlaces, null );
        RoadTrip roadTrip4 = new RoadTrip("RTTest4" , 61 , d4 , "ChIJ5TCOcRaYpBIRCmZHTz37sEQ" , listPlaces, null );

        presenterLogin.createRoadTrip(roadTrip);
        presenterLogin.createRoadTrip(roadTrip2);
        presenterLogin.createRoadTrip(roadTrip3);
        presenterLogin.createRoadTrip(roadTrip4);
        */

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customizing G+ button
        //btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        //btnSignIn.setScopes(gso.getScopeArray());

        return view;
    }

    @OnClick(R.id.btn_sign_in)
    void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.btn_sign_out)
    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        //updateUI(false);
                    }
                });
    }

    @OnClick(R.id.btn_revoke_access)
    void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e(TAG, "display name: " + acct.getDisplayName());
            String personName = acct.getDisplayName();
            String lastName = acct.getFamilyName();
            String email = acct.getEmail();
            String userName = acct.getGivenName();
            String firebaseId = acct.getId();

            firebaseIdRetrieve = firebaseId;

            String personPhotoUrl = "";
            if(acct.getPhotoUrl() != null)
                personPhotoUrl = acct.getPhotoUrl().toString();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            txtName.setText(personName);
            txtEmail.setText(email);
            Glide.with(getContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);

            Log.d(TAG, "handleSignInResult: email exemple " + email);
            Utility.storeInformationUser(getActivity() , "pictureGmail" , personPhotoUrl );
            Utility.storeInformationUser(getActivity(),"firebaseId" , firebaseId);
            Utility.storeInformationUser(getActivity(), "email" , email);
            Utility.storeInformationUser(getActivity(), "lastName" , lastName);
            Utility.storeInformationUser(getActivity(), "personName" , personName);
            Utility.storeInformationUser(getActivity(), "userName" , userName);

            // on sauvegarde firebaseId DANS L'APPLICATION
            Utility.storeFirebaseId(getActivity(), firebaseId);
            user = new User(email, firebaseId, personName, lastName, userName, null, "");
            variable.setUser(user);

            Log.d(TAG, "handleSignInResult: recup: "+Utility.getInformationUser(getActivity() ,"firebaseId" ));
            presenterLogin.getUsers();
        }
        else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(connectionResult.getErrorMessage());
        builder.setCancelable(true);
        builder.setPositiveButton("Fermer" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this.getContext());
            mProgressDialog.setMessage("loading");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void isLoginSuccess(int userId) {
        Utility.storeIdUser(getActivity(),userId);
        Log.d(TAG, "isLoginSuccess: id login: " + userId);
        /*SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("idUser", userId );
        editor.commit();
        */
        Intent intent = new Intent(getActivity() , ActivityHome2.class);
        startActivity(intent);
    }

    @Override
    public void isLoginFailed(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("Fermer" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void createRoadTrip() {
        Toast.makeText(getContext(), "road trip créer avec succes", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "createRoadTrip: roadtrip creer avec success");
    }

    @Override
    public void verifyExistingUser(ArrayList<User> listUsers) {
        boolean res = false;
        int id = -1;
        for(User u : listUsers){
            if (u.getFirebaseId().equals(firebaseIdRetrieve)){
                id = u.getId();
                res = true;
                break;
            }
        }
        if(res){
            Utility.storeIdUser(getActivity() , id);
            Intent intent = new Intent(getContext() , ActivityHome2.class);
            startActivity(intent);
        }
        else{
            presenterLogin.connect(user);
        }

    }
}
