package com.clusterdev.ragam;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SlidingDrawer;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView tv1,tv2,event,exhibition,proshow,workshop;
    private SlidingDrawer drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //tv1= (TextView) findViewById(R.id.textView);
        //tv2= (TextView) findViewById(R.id.textView2);
        event= (TextView) findViewById(R.id.event_textview);
        exhibition= (TextView) findViewById(R.id.exhibition_textview);
        proshow= (TextView) findViewById(R.id.proshow_textview);
        workshop= (TextView) findViewById(R.id.workshop_textview);
        drawer= (SlidingDrawer) findViewById(R.id.slidingDrawer);
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue-Thin.otf");
        //tv1.setTypeface(tf);
        //tv2.setTypeface(tf);
        event.setTypeface(tf);
        exhibition.setTypeface(tf);
        proshow.setTypeface(tf);
        workshop.setTypeface(tf);


        drawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {

            }
        });
    }



    public void pageSelected(View view){
        Intent intent;
        switch(view.getId()){

            case R.id.events :
                intent=new Intent(MainActivity.this,Events.class);
                startActivity(intent);
                break;
            case R.id.workshops :
                intent=new Intent(MainActivity.this,Workshop.class);
                startActivity(intent);
                break;
            case R.id.proshows :
                intent=new Intent(MainActivity.this,Gallery.class);
                startActivity(intent);
                break;
            default:
                break;


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
