package com.mycompany.network.ejemplo_marvels;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mycompany.network.R;
import com.mycompany.network.ejemplo_marvels.list.MyAdapter;
import com.mycompany.network.ejemplo_marvels.model.WorldWonder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<WorldWonder>>, MyAdapter.WorldWonderListListener {
    private RecyclerView mRecyclerView;
    private ArrayList<WorldWonder> myDataset = new ArrayList<>();
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worldwonders);
        mRecyclerView = findViewById(R.id.list_worldwonders);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset, this);
        mRecyclerView.setAdapter(mAdapter);

        Bundle queryBundle = new Bundle();

        getSupportLoaderManager().restartLoader(0, queryBundle, this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<WorldWonder>> onCreateLoader(int id, @Nullable Bundle args) {
        return new BasicTask(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<WorldWonder>> loader, ArrayList<WorldWonder> countries) {
        Log.d("tasks", System.identityHashCode(this) + " onPostExecute");
        this.myDataset = countries;
        mAdapter.setData(myDataset);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<WorldWonder>> loader) {

    }

    @Override
    public void onClick(WorldWonder data) {
        Intent intent = new Intent(this, WonderDetailActivity.class);
        intent.putExtra(WonderDetailActivity.EXTRA_DATA, data);
        startActivity(intent);
    }
}
