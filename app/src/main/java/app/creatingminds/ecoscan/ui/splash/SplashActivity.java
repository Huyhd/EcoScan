package app.creatingminds.ecoscan.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;

import app.creatingminds.ecoscan.EcoApp;
import app.creatingminds.ecoscan.R;
import app.creatingminds.ecoscan.data.DataManager;
import app.creatingminds.ecoscan.data.model.Food;
import app.creatingminds.ecoscan.ui.main.MainActivity;
import app.creatingminds.ecoscan.utils.Const;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final DataManager dataManager = EcoApp.getDataManager();

        // TODO: Optimize
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Food> foodList = dataManager.getDatabase().foodDao().getAll();
                dataManager.setCachedFoodList(foodList);

                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // This will run on main thread
                        Intent splash = new Intent(SplashActivity.this, MainActivity.class);

                        startActivity(splash);
                        finish();
                    }
                }, Const.DEFAULT_SPLASH_DELAY);
            }
        }).start();
    }
}
