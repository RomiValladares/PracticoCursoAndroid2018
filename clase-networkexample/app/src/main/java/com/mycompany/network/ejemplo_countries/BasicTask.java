package com.mycompany.network.ejemplo_countries;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.mycompany.network.ejemplo_countries.model.Country;

import java.util.ArrayList;

public class BasicTask extends AsyncTaskLoader<ArrayList<Country>> {
    private String type;

    public BasicTask(Context context, String type) {
        super(context);

        this.type = type;
    }

    @Override
    protected void onStartLoading() {
        forceLoad(); // ejecuta loadInBackground
    }

    @Override
    public ArrayList<Country> loadInBackground() {
        return new NetworkUtils().getCountries(type);
    }
}

