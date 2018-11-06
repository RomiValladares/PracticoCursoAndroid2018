package com.mycompany.network.ejemplo_countries;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mycompany.network.R;
import com.mycompany.network.ejemplo_countries.list.MyAdapter;
import com.mycompany.network.ejemplo_countries.model.Country;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<Country>> {
    
    private static final String LOADER_ARGS_TYPE = "LOADER_ARGS_TYPE";
    
    EditText inputSearch;
    
    private RecyclerView mRecyclerView;
    private ArrayList<Country> myDataset = new ArrayList<>();
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        
        inputSearch = findViewById(R.id.input_country);
        mRecyclerView = findViewById(R.id.countries_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    @NonNull
    @Override
    public Loader<ArrayList<Country>> onCreateLoader(int id, @Nullable Bundle args) {
        String searchQuery = args.getString(LOADER_ARGS_TYPE);

        return new BasicTask(this, searchQuery);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Country>> loader, ArrayList<Country> countries) {
        Log.d("tasks", System.identityHashCode(this) + " onPostExecute");
        this.myDataset = countries;
        mAdapter.setData(myDataset);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Country>> loader) {

    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        //bundle.putString(LIST_ARGS, lastResult);
    }

    public void onReloadClick(View view) {
        String searchQuery = getSelectedOption();

        Bundle queryBundle = new Bundle();
        queryBundle.putString(LOADER_ARGS_TYPE, searchQuery);

        getSupportLoaderManager().restartLoader(0, queryBundle, this);
    }
    
    private String getSelectedOption() {
        return inputSearch.getText().toString();
    }
}
