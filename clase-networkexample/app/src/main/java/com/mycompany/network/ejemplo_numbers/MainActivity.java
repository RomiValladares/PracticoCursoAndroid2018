package com.mycompany.network.ejemplo_numbers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mycompany.network.R;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    private static final String LOADER_ARGS = "loader_args";
    private static final String LIST_ARGS = "list_args";
    private static final String LOADER_ARGS_TYPE = "LOADER_ARGS_TYPE";
    private static final String LOADER_ARGS_NUMBER = "LOADER_ARGS_NUMBER";
    TextView txtResults;
    String lastResult;
    EditText inputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            lastResult = savedInstanceState.getString(LIST_ARGS);
        }

        txtResults = findViewById(R.id.txt_result);
        inputNumber = findViewById(R.id.input_number);

        updateTasksTextView();
    }

    private void updateTasksTextView() {
        txtResults.setText(lastResult);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String numberType = args.getString(LOADER_ARGS_TYPE);
        int number = args.getInt(LOADER_ARGS_NUMBER);
        
        return new BasicTask(this, number, numberType);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d("tasks", System.identityHashCode(this) + " onPostExecute");
        txtResults.setText(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(LIST_ARGS, lastResult);
    }

    public void onReloadClick(View view) {
        String numberType = getSelectedOption();
        int number = getInputNumber();

        Bundle queryBundle = new Bundle();
        queryBundle.putString(LOADER_ARGS_TYPE, numberType);
        queryBundle.putInt(LOADER_ARGS_NUMBER, number);

        getSupportLoaderManager().restartLoader(0, queryBundle, this);
    }

    private int getInputNumber() {
        return Integer.parseInt(inputNumber.getText().toString());
    }

    private String getSelectedOption() {
        RadioGroup rg = findViewById(R.id.group_numbertype);
        RadioButton radioButton = findViewById(rg.getCheckedRadioButtonId());
        
        String radioValue = radioButton.getText().toString();
        
        return radioValue.toLowerCase();
    }



}
