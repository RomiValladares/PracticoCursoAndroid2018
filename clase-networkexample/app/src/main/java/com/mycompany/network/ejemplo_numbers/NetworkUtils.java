package com.mycompany.network.ejemplo_numbers;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by romina valladaresromina@gmail.com on 10/10/18.
 */
public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // Base URL
    private static final String BASE_URL = "http://numbersapi.com/%s/%s";

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String numberJSONString = null;

    /**
     * @param type puede ser trivia o math
     */
    public String getNumberInfo(int number, String type) {
        try {
            //agregar los parametros a la URL
            String finalURL = String.format(BASE_URL, number, type);

            // crear una URI a partir de la finalURL
            // aca podrian agregar parametros,
            // con appendQueryParameter
            Uri builtURI = Uri.parse(finalURL)
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

            // Usar un StringBuilder para la respuesta
            StringBuilder builder = new StringBuilder();

            //leer cada linea
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // no hay nada que leer
                return null;
            }

            //obtener un String con el JSON
            numberJSONString = builder.toString();
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


            Log.d(LOG_TAG, numberJSONString);
        }

        return numberJSONString;
    }
}
