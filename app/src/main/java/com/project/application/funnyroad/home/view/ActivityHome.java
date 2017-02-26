package com.project.application.funnyroad.home.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;
import com.project.application.funnyroad.home.allroadtrip.view.AllRoadTripFragment;
import com.project.application.funnyroad.home.friends.view.FriendsFragment;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.home.presenter.HomeAllRoadTripAdapter;
import com.project.application.funnyroad.home.roadtripsuggested.view.RoadTripSuggestedFragment;
import com.project.application.funnyroad.lieu.view.LieuxFragment;
import com.project.application.funnyroad.photos.view.PhotosFragment;
import com.project.application.funnyroad.profil.view.ProfilFragment;
import com.project.application.funnyroad.roadtrip.view.RoadTripFragment;
import com.project.application.funnyroad.urgence.view.UrgenceFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by you on 23/02/2017.
 */

public class ActivityHome extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbar.setVisibility(View.GONE);

        FragmentHome fragmentHome = new FragmentHome();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment, fragmentHome)
                .commit();
    }

}
