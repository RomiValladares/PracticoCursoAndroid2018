package com.mycompany.fragmenttest2.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycompany.fragmenttest2.R;

public class DemoFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_hello_world_background, container, false);
        Bundle args = getArguments();
        ((TextView) rootView.findViewById(R.id.text)).setText(
                Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}
