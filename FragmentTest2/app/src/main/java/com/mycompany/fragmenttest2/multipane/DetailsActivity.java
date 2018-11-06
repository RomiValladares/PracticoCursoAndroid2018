package com.mycompany.fragmenttest2.multipane;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            //si la pantalla esta en landscape, no mostramos la activity,
            //dejamos la MainActivity con los dos fragments
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // setup inicial
            DetailsFragment details = new DetailsFragment();
            details.setArguments(getIntent().getExtras());

            //android.R.id.content = nos da el root ViewGroup
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}

