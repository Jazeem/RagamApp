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

    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,event,exhibition,proshow,workshop;
    private SlidingDrawer drawer;
    private ParallaxImageView background,logo;
    private Button slideDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tv1= (TextView) findViewById(R.id.dropdown_tv1);
        tv2= (TextView) findViewById(R.id.dropdown_tv2);
        tv3= (TextView) findViewById(R.id.dropdown_tv3);
        tv4= (TextView) findViewById(R.id.dropdown_tv4);
        tv5= (TextView) findViewById(R.id.dropdown_tv5);
        tv6= (TextView) findViewById(R.id.dropdown_tv6);
        tv7= (TextView) findViewById(R.id.dropdown_tv7);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        final float dpHeight = outMetrics.heightPixels / density;



        background= (ParallaxImageView) findViewById(R.id.main_bg);
        logo= (ParallaxImageView) findViewById(R.id.logo);

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
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        tv5.setTypeface(tf);
        tv6.setTypeface(tf);
        tv7.setTypeface(tf);

        background.registerSensorManager();
        logo.registerSensorManager();
        logo.setParallaxIntensity((float)1.05);

        drawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                findViewById(R.id.pullup_button).setRotation(180);
            }

        });
        drawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                findViewById(R.id.pullup_button).setRotation(0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        logo.registerSensorManager();
        background.registerSensorManager();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        logo.unregisterSensorManager();
        background.unregisterSensorManager();
    }

    @Override
    protected void onPause() {
        super.onPause();
        logo.unregisterSensorManager();
        background.unregisterSensorManager();
    }

    public void pageSelected(View view){
        Log.v("test","im here");
        Intent intent;
        switch(view.getId()){

            case R.id.dropdown_tv1 :
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",0);
                startActivity(intent);
                break;
            case R.id.dropdown_tv2 :
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",1);
                startActivity(intent);
                break;
            case R.id.dropdown_tv3 :
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",2);
                startActivity(intent);
                break;
            case R.id.dropdown_tv4 :
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",3);
                startActivity(intent);
                break;

            case R.id.dropdown_tv5 :
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",4);
                startActivity(intent);
                break;

            case R.id.dropdown_tv6 :
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",5);
                startActivity(intent);
                break;
            case R.id.dropdown_tv7:
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",6);
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
