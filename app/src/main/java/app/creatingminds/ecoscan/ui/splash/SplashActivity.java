package app.creatingminds.ecoscan.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import app.creatingminds.ecoscan.R;
import app.creatingminds.ecoscan.ui.main.MainActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler splashhandler = new Handler();
        splashhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(SplashActivity.this, MainActivity.class);

                startActivity(splash);
                finish();
            }
        }, 2000);
    }
}
