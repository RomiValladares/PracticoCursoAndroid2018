package com.mycompany.network.ejemplo_marvels;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.mycompany.network.ejemplo_marvels.model.WorldWonder;

import java.util.ArrayList;

public class BasicTask extends AsyncTaskLoader<ArrayList<WorldWonder>> {
    public BasicTask(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad(); // ejecuta loadInBackground
    }

    @Override
    public ArrayList<WorldWonder> loadInBackground() {
        return new NetworkUtils().getWorldWonders();
    }
}

