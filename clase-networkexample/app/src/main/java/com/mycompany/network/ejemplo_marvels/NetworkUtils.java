package com.mycompany.network.ejemplo_marvels;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.network.ejemplo_marvels.model.WorldWonder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romina valladaresromina@gmail.com on 10/10/18.
 */
public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // Base URL
    private static final String BASE_URL = "https://api.myjson.com/bins/j9h5c";

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String numberJSONString = null;

    public ArrayList<WorldWonder> getWorldWonders() {
        ArrayList<WorldWonder> result = null;
        try {
            // crear una URI a partir de la finalURL
            // aca podrian agregar parametros,
            // con appendQueryParameter
            Uri builtURI = Uri.parse(BASE_URL)
                    .buildUpon()
                    .build();

            //crear un objeto URL a partir de la URI
            URL requestURL = new URL(builtURI.toString());

            //abrir conexion
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            //en este caso el method es un GET
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // obtener InputStream
            InputStream inputStream = urlConnection.getInputStream();

            // crear un BufferedReader, desde el InputStream obtenido
            reader = new BufferedReader(new InputStreamReader(inputStream));

            Type gsonType = new TypeToken<List<WorldWonder>>() {}.getType();
            result  = new Gson().fromJson(reader, gsonType);

            Log.d("results",result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
