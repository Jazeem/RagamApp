package com.clusterdev.ragam;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by Jazeem on 05/03/15.
 */
public class Gallery extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gallery_layout);
    }
}
