package com.mycompany.fragmenttest2.backstack;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mycompany.fragmenttest2.R;

public class FragmentCountingActivity extends AppCompatActivity {

    private int mStackLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_counting);
    }

   public void addFragmentToStack(View v) {
        mStackLevel++;

        // crear instancia de un nuevo fragment
        Fragment newFragment = CountingFragment.newInstance(mStackLevel);

        // agregar el fragment a la activity
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // replace es lo mismo que llamar primero a un remove y luego a un add
        ft.replace(R.id.fragment_container, newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // agregar la transaction al backstack
        ft.addToBackStack(null);
        ft.commit();
    }

}
