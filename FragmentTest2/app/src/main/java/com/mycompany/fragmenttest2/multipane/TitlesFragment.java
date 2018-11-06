package com.mycompany.fragmenttest2.multipane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mycompany.fragmenttest2.R;

import static com.mycompany.fragmenttest2.multipane.DetailsFragment.ARGS_INDEX;

public class TitlesFragment extends ListFragment {
    //key para guardar la posicion seleccionada
    private static final String ARG_SELECTED_POS = "selectedPosition";
    //true si se estan mostrando ambos fragments
    boolean isDualPane;
    //posicion de la lista seleccionada
    int mSelectedPosition = 0;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //le agrega a la lista todos los titulos de las recetas
        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, Recetas.TITULOS_RECETAS));

        // chequear si esta el frame para poner el fragment de Details
        // (recordar que tenemos dos layouts, dependiendo de la orientacion del device)
        View detailsFrame = getActivity().findViewById(R.id.details);
        isDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        //obtener la posicion seleccionada
        if (savedInstanceState != null) {
            mSelectedPosition = savedInstanceState.getInt(ARG_SELECTED_POS, 0);
        }

        //en caso de que sea dualPane, mostrar el detalle
        if (isDualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mSelectedPosition);
        }
    }


    public TitlesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_SELECTED_POS, mSelectedPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    /**
     * decide como mostrar el detalle de un item seleccionado,
     * si mostrar un fragment o una nueva activity
     */
    void showDetails(int index) {
        mSelectedPosition = index;

        if (isDualPane) {
            getListView().setItemChecked(index, true);

            FragmentManager manager = getActivity().getSupportFragmentManager();

            DetailsFragment details = (DetailsFragment) manager.findFragmentById(R.id.details);
            //refrescar el fragment en caso de que sea necesario
            if (details == null || details.getSelectedIndex() != index) {
                details = DetailsFragment.newInstance(index);

                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.details, details);

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            //TODO: Iniciar la activity DetailsActivity
            Intent intent = new Intent();
            intent.setClass(getContext(), DetailsActivity.class);
            intent.putExtra(ARGS_INDEX, index);
            startActivity(intent);
        }
    }


}
