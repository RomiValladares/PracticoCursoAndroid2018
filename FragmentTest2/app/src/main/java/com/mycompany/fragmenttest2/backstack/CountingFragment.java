package com.mycompany.fragmenttest2.backstack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycompany.fragmenttest2.R;

public class CountingFragment extends Fragment {
    private static final String ARGS_NUM = "num";
    int mNum;

    static CountingFragment newInstance(int num) {
        CountingFragment f = new CountingFragment();

        // guardar el numero recibido como un argument
        Bundle args = new Bundle();
        args.putInt(ARGS_NUM, num);
        f.setArguments(args);

        return f;
    }

    /**
     * al crear el fragment,
     * intenta obtener el numero desde los arguments
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = 1;
        if (getArguments() != null) {
            mNum = getArguments().getInt(ARGS_NUM);
        }
    }

    /**
     * el fragment solo muestra su numero en un textview
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hello_world, container, false);
        TextView tv = v.findViewById(R.id.text);

        //TODO cambiarle el texto a la TextView, mostrar el numero de fragment.
        tv.setText("Fragment #" + mNum);

        return v;
    }
}

