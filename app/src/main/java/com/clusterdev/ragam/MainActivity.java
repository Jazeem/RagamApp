package com.clusterdev.ragam;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.nvanbenschoten.motion.ParallaxImageView;


public class MainActivity extends ActionBarActivity {

    private TextView tv1,tv2,event,exhibition,proshow,workshop;
    private SlidingDrawer drawer;
    private ParallaxImageView background,logo;
    private Button slideDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //tv1= (TextView) findViewById(R.id.textView);
        //tv2= (TextView) findViewById(R.id.textView2);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        final float dpHeight = outMetrics.heightPixels / density;



        background= (ParallaxImageView) findViewById(R.id.main_bg);
        logo= (ParallaxImageView) findViewById(R.id.logo);
        event= (TextView) findViewById(R.id.event_textview);
        exhibition= (TextView) findViewById(R.id.exhibition_textview);
        proshow= (TextView) findViewById(R.id.proshow_textview);
        workshop= (TextView) findViewById(R.id.workshop_textview);
        drawer= (SlidingDrawer) findViewById(R.id.slidingDrawer);
        //slideDown= (Button) findViewById(R.id.slide_down);
        /*slideDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("test","inside button");
                TranslateAnimation translateAnimation = new TranslateAnimation(0,0, Animation.RELATIVE_TO_SELF,dpHeight+logo.getHeight());
                translateAnimation.setDuration(1000);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        logo.unregisterSensorManager();
                        logo.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                logo.startAnimation(translateAnimation);
            }
        });*/
        //drawer.setVisibility(View.GONE);
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue-Thin.otf");
        //tv1.setTypeface(tf);
        //tv2.setTypeface(tf);
        event.setTypeface(tf);
        exhibition.setTypeface(tf);
        proshow.setTypeface(tf);
        workshop.setTypeface(tf);
        background.registerSensorManager();
        logo.registerSensorManager();
        logo.setParallaxIntensity((float)1.1);

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
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",0);
                startActivity(intent);
                break;
            case R.id.workshops :
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",1);
                startActivity(intent);
                break;
            case R.id.proshows :
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",2);
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
