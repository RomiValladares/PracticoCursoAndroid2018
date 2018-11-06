package com.mycompany.network.ejemplo_numbers;

import android.os.AsyncTask;

/**
 * Created by romina valladaresromina@gmail.com on 10/11/18.
 */
public class BasicAsyncTask extends AsyncTask<NumberFilter, String, String> {
    @Override
    protected String doInBackground(NumberFilter... numberFilters) {
        NumberFilter numberFilter = numberFilters[0];
        int number = numberFilter.getNumber();
        String factType = numberFilter.getFactType();

        String result = new NetworkUtils().getNumberInfo(number, factType);

        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
