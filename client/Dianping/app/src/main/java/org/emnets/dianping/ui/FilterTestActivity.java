package org.emnets.dianping.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.emnets.dianping.R;

/**
 * Created by kea on 15-8-9.
 */
public class FilterTestActivity extends Activity {
    private static final String[] location = {"0~100", "100~200", "200~300"};
    private static final String[] price = {"0~100", "100~200", "200~300"};

    private TextView locationView;
    private Spinner locationSpinner;
    private TextView priceView;
    private Spinner priceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.filter_main);

//        locationView = findViewById()

        init();
    }

    private void init() {
        priceSpinner = (Spinner) findViewById(R.id.price_spinner);
        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.price_array,
                android.R.layout.simple_spinner_dropdown_item
        );
        priceSpinner.setAdapter(priceAdapter);
        priceSpinner.setPrompt("Price");
        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );
    }
}
