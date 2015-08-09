package org.emnets.dianping.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import org.emnets.dianping.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final boolean b = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Context ctx = SplashActivity.this;
                SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
                String number = sp.getString("STRING_NUMBER", "none");
                if (number.equals("none")) {
                    Intent i = new Intent(SplashActivity.this, TimeLineActivity.class);
                    SplashActivity.this.startActivity(i);
                    SplashActivity.this.finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, TimeLineActivity.class);
                    SplashActivity.this.startActivity(i);
                    SplashActivity.this.finish();
                }
            }
        }, 3000);
    }
}
