package com.mycompany.network.ejemplo_marvels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mycompany.network.R;
import com.mycompany.network.ejemplo_marvels.list.ImageListAdapter;
import com.mycompany.network.ejemplo_marvels.model.WorldWonder;

public class WonderDetailActivity extends AppCompatActivity {
    public static String EXTRA_DATA = "extra_data";
    private WorldWonder data;
    private RecyclerView mRecyclerView;
    private ImageListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonder_detail);

        Intent intent = getIntent();
        data = intent.getParcelableExtra(EXTRA_DATA);

        mRecyclerView = findViewById(R.id.images_list);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ImageListAdapter(data.getImagenes());
        mRecyclerView.setAdapter(mAdapter);
        //todo fill other data

        TextView txtTitle = findViewById(R.id.txt_list_item);
        TextView txtEpoch = findViewById(R.id.txt_epoch);
        TextView txtCountry = findViewById(R.id.txt_country);
        TextView txtExtraInfo = findViewById(R.id.txt_detail);

        txtTitle.setText(data.getNombre());
        txtEpoch.setText(data.getEpoca());
        txtCountry.setText(data.getPais());
        txtExtraInfo.setText(data.getExtraInfo());
    }
}
