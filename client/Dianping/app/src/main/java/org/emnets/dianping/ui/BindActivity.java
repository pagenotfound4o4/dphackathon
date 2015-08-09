package org.emnets.dianping.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.emnets.dianping.R;

/**
 * Created by kea on 15-8-9.
 */
public class BindActivity extends Activity {
    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);

        init();
    }

    private void init() {
        button = (Button)findViewById(R.id.bind_button);
        editText = (EditText)findViewById(R.id.bind_edittext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                editText.setFocusable(false);
                System.out.println(editText.getText());
                BindTask task = new BindTask();
                task.execute(editText.getText().toString());
            }
        });
    }

    class BindTask extends AsyncTask<String, Integer, String>  {
        @Override
        protected String doInBackground(String... params) {
            return params.toString();
        }

        @Override
        protected void onPostExecute(String rst) {
            super.onPostExecute(rst);
            SharedPreferences sp = getSharedPreferences("SP", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("STRING_NUMBER", rst);
            editor.commit();

            Intent i = new Intent(BindActivity.this, MainActivity.class);
            BindActivity.this.startActivity(i);
            BindActivity.this.finish();
        }
    }
}
