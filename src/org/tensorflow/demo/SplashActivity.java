package org.tensorflow.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by DELL on 10/28/2017.
 */

public class SplashActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, ClassifierActivity.class);
        startActivity(intent);
        finish();
    }
}