package com.project.application.funnyroad.home.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.project.application.funnyroad.R;
//import com.project.application.funnyroad.googlemap.view.view.ActivityNewRoadTripRouteChoice;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.googlemap.view.view.ActivityNewRoadTripRouteChoice;
import com.project.application.funnyroad.lieu.view.LieuxFragment;
import com.project.application.funnyroad.login.view.ActivityLogin;
import com.project.application.funnyroad.login.view.FragmentLogin2;
import com.project.application.funnyroad.newroadtrip.view.ActivityNewMRoadTrip;
import com.project.application.funnyroad.photos.view.PhotosFragment;
import com.project.application.funnyroad.profil.view.view.ProfilFragment;
import com.project.application.funnyroad.urgence.view.UrgenceFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by you on 24/02/2017.
 */

public class ActivityHome2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.OnConnectionFailedListener{

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float moveFactor = (navigationView.getWidth() * slideOffset);

            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
        //TextView userName = (TextView) header.findViewById(R.id.userNameNav);
        TextView email = (TextView) header.findViewById(R.id.emailNav);
        //ImageView image = (ImageView) header.findViewById(R.id.imageViewNav);
        //userName.setText(Utility.getInformationUser(this ,"personName"));
        email.setText(Utility.getInformationUser(this , "email"));

        /*
        Glide.with(this).load(Utility.getInformationUser(this ,"pictureGmail"))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
        */
        Fragment fragmentHome = new FragmentHome();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragmentHome);
        ft.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    // 3.4 and 3.8
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_road_trip) {
            fragment = new FragmentHome();
        }
        else if (id == R.id.nav_profil) {
            fragment = new ProfilFragment();
        }
        else if (id == R.id.nav_urgence) {
            fragment = new UrgenceFragment();
        }
        else if (id == R.id.nav_photos) {
            fragment = new PhotosFragment();
        }
        else if (id == R.id.nav_lieux) {
            fragment = new LieuxFragment();
        }

        else if (id == R.id.nav_sign_out){
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                            startActivity(intent);                        }
                    });

        }

        if (fragment != null) {
            FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.floatingActionButton)
    public void goToAddRoadTrip(){
        Intent intent = new Intent(this , ActivityNewRoadTripRouteChoice.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    @Override
    public void onStart(){
        super.onStart();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mGoogleApiClient.connect();

    }
}
