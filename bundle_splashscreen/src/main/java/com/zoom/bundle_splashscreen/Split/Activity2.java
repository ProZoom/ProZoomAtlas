package com.zoom.bundle_splashscreen.Split;

import android.app.Activity;
import android.os.Bundle;

import com.zoom.bundle_splashscreen.R;


public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Preparing the 2 images to be split
        setContentView(R.layout.activity_splash_split_two);
    }

}
