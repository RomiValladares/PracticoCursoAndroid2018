package com.mycompany.fragmenttest2.multipane;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycompany.fragmenttest2.R;

public class DetailsFragment extends Fragment {
    public static String ARGS_INDEX = "index";

    /**
     * crea una nueva instancia de DetailsFragment,
     * y le pasa un index del item seleccionado
     */
    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        Bundle args = new Bundle();
        //guardarlo para despues poder obtenerlo en onCreateView
        args.putInt(ARGS_INDEX, index);
        f.setArguments(args);

        return f;
    }

    public int getSelectedIndex() {
        return getArguments().getInt(ARGS_INDEX, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        TextView text = rootView.findViewById(R.id.txt_details);

        int selectedIndex = getArguments().getInt(ARGS_INDEX, 0);
        String detalleReceta = Recetas.PASOS_RECETAS[selectedIndex];

        text.setText(detalleReceta);

        return rootView;
    }
}



