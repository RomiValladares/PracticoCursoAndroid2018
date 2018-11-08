package com.mycompany.network.ejemplo_countries;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.mycompany.network.R;
import com.mycompany.network.ejemplo_countries.list.MyAdapter;
import com.mycompany.network.ejemplo_countries.model.Country;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<Country>>,MyAdapter.CountryAdapterListener {
    private static final String LOADER_ARGS_TYPE = "LOADER_ARGS_TYPE";
    SearchBarHelper mSearchBarHelper = new SearchBarHelper();
    EditText inputSearch;

    private RecyclerView mRecyclerView;
    private ArrayList<Country> myDataset = new ArrayList<>();
    private MyAdapter mAdapter;
    private Toolbar mToolbar;
    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        inputSearch = findViewById(R.id.input_country);
        mRecyclerView = findViewById(R.id.countries_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset, this);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        mSearchBarHelper.setUpOptionsMenu(menu);
        return true;
    }


    private String getSelectedOption() {
        return inputSearch.getText().toString();
    }

    public ActionMode.Callback mActionModeCallback =
            new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    MenuInflater inflater = actionMode.getMenuInflater();
                    inflater.inflate(R.menu.context_menu, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {
                    mActionMode = null;
                }
                // Implement action mode callbacks here
            };

    @Override
    public boolean startActionMode(View v) {
        if (mActionMode != null) return false;

        mActionMode =  startActionMode(mActionModeCallback);
        v.setSelected(true);
        return true;
    }

    class SearchBarHelper implements SearchView.OnQueryTextListener {
        private final int SEARCH_ACTION_WAIT = 1000;

        private SearchView searchView;
        private MenuItem item;
        /**
         * last query that was typed, but not necessarily executed
         */
        private String lastTypedQuery;
        /**
         * last query that was effectively searched for
         */
        private String lastSearchQuery;
        private boolean firstQuery;

        public SearchBarHelper() {
            init();
        }

        private void init() {
            lastTypedQuery = null;
            setLastSearchQuery(null);
            firstQuery = true;
        }

        @Override
        public boolean onQueryTextChange(final String query) {
            lastTypedQuery = query;
            if (query == null || query.isEmpty()) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if ((query == null || query.isEmpty())
                                && (lastTypedQuery == null || lastTypedQuery.isEmpty())) {
                            if (!firstQuery && !searchView.isIconified()) {
                                collapseSearchView();
                            }
                        }
                    }
                }, SEARCH_ACTION_WAIT);
            } else if (mAdapter != null) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (query.equals(lastTypedQuery)) {
                            searchUsers(query);
                            firstQuery = false;
                        }
                    }
                }, SEARCH_ACTION_WAIT);
                return true;
            }
            return false;
        }

        private void collapseSearchView() {
            item.collapseActionView();
            init();
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            return true;
        }

        public void setUpOptionsMenu(Menu menu) {
            item = menu.findItem(R.id.search);
            searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(mSearchBarHelper);
            // When using the support library, the setOnActionExpandListener() method is
            // static and accepts the MenuItem object as an argument

            item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    //the search bar is hiding

                    //TODO - mostrar toda la data de nuevo.

                    return true;  // Return true to collapse action view
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    //the search bar is expanding
                    firstQuery = true;
                    return true;  // Return true to expand action view
                }
            });
        }

        private void searchUsers(String query) {
            setLastSearchQuery(query);
            doSearch();
        }

        private void doSearch() {
            //TODO filtrar la lista
            //y setearsela al adapter
        }

        public void clearState() {
            if (searchView != null && item.isActionViewExpanded()) {
                item.collapseActionView();
            }
        }

        /**
         * make sure there's not a more recent input query than search query
         *
         * @return true if lastSearchQuery == lastTypedQuery, false otherwise
         */
        private boolean isLastSearchValid() {
            if (lastSearchQuery == null) {
                return lastTypedQuery == null;
            }
            return lastSearchQuery.equals(lastTypedQuery);
        }

        public SearchView getSearchView() {
            return searchView;
        }

        public boolean userHasSearched() {
            return searchView != null && !firstQuery;
        }

        /**
         * @return true if the searchview is expanded and the user has searched for something already
         */
        public boolean isSearchViewActive() {
            return userHasSearched() && (searchView != null && !searchView.isIconified());
        }

        private String getLastSearchQuery() {
            return lastSearchQuery;
        }

        private void setLastSearchQuery(String query) {
            this.lastSearchQuery = query;
        }
    }
}
