package com.mycompany.network.ejemplo_numbers;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class BasicTask extends AsyncTaskLoader<String> {
    private String type;
    private int number;

    public BasicTask(Context context, int number, String type) {
        super(context);
        this.number = number;
        this.type = type;
    }

    @Override
    protected void onStartLoading() {
        forceLoad(); // ejecuta loadInBackground
    }

    @Override
    public String loadInBackground() {
        return new NetworkUtils().getNumberInfo(number, type);
    }
}

