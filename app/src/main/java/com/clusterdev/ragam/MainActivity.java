package com.clusterdev.ragam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SlidingDrawer;

import com.nvanbenschoten.motion.ParallaxImageView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Button tv1,tv2,tv3,tv4,tv6;
    private SlidingDrawer drawer;
    private ParallaxImageView background,logo;

    private String baseUrl="http://www.ragam.org.in/2015/";

    private class PingMac extends AsyncTask<String, Void, String >{

        @Override
        protected String doInBackground(String... params) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(baseUrl+"app/ping/");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                WifiInfo wInfo = wifiManager.getConnectionInfo();
                String macAddress = wInfo.getMacAddress();
                nameValuePairs.add(new BasicNameValuePair("mac", macAddress));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tv1= (Button) findViewById(R.id.dropdown_tv1);
        tv2= (Button) findViewById(R.id.dropdown_tv2);
        tv3= (Button) findViewById(R.id.dropdown_tv3);
        tv4= (Button) findViewById(R.id.dropdown_tv4);

        tv6= (Button) findViewById(R.id.dropdown_tv6);


        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);






        background= (ParallaxImageView) findViewById(R.id.main_bg);
        logo= (ParallaxImageView) findViewById(R.id.logo);

        drawer= (SlidingDrawer) findViewById(R.id.slidingDrawer);

        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue-Thin.otf");
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);

        tv6.setTypeface(tf);

        background.registerSensorManager();
        logo.registerSensorManager();
        logo.setParallaxIntensity((float)1.05);

        drawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                findViewById(R.id.pullup_button).setBackgroundResource(R.drawable.pulldown);
            }

        });
        drawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                findViewById(R.id.pullup_button).setBackgroundResource(R.drawable.pullup);
            }
        });
        new PingMac().execute();
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


            case R.id.dropdown_tv6 :
                intent=new Intent(MainActivity.this,Contents.class);
                intent.putExtra("FRAGMENT",4);
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
