package com.example.oraberkane.appliplatine.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.oraberkane.appliplatine.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oraberkane on 01/02/2017.
 */

public class LayoutCommonActivity extends AppCompatActivity {

    //public Toolbar toolbar;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_activity);

      //  toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);//relié à LayoutCommonActivity
        ButterKnife.bind(mToolbar);//pour y acceder depuis les autres activity (qui etendent LayoutCommonActivity )
    }

}
