package com.clusterdev.ragam.fragments;

import android.app.Activity;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.clusterdev.ragam.ContactInfo;
import com.clusterdev.ragam.CustomContactAdapter;
import com.clusterdev.ragam.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {

    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16;
    private LatLng NIT= new LatLng(11.322266, 75.930453);
    private LatLng NITMarker=new LatLng(11.3193295,75.93319);
    private LatLng India=new LatLng(10.4754981,79.258464);
    private GoogleMap map;

    private ScrollView scrollView;

    private boolean mapAnimated;
    private MapView mapView;
    private Bundle mBundle;
    private ImageView mapMask;
    private ListView listView;
    private Typeface tf;
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        Fragment fragment = new AboutFragment();
        return fragment;

    }

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle=savedInstanceState;
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        tv1 = (TextView) v.findViewById(R.id.tv_about_heading);
        tv2 = (TextView) v.findViewById(R.id.tv_about_ragam);
        tv3 = (TextView) v.findViewById(R.id.tv_about_ragam_desc);
        tv4 = (TextView) v.findViewById(R.id.tv_about_reach);
        tv5 = (TextView) v.findViewById(R.id.tv_about_reach_desc);
        tv6 = (TextView) v.findViewById(R.id.tv_about_contacts);
        tv7 = (TextView) v.findViewById(R.id.tv_about_dev);
        tv8 = (TextView) v.findViewById(R.id.tv_ajnas);
        tv9 = (TextView) v.findViewById(R.id.tv_ajnas_mail);
        tv10 = (TextView) v.findViewById(R.id.tv_ajnas_desc);
        tv11 = (TextView) v.findViewById(R.id.tv_arjun);
        tv12 = (TextView) v.findViewById(R.id.tv_arjun_mail);
        tv13 = (TextView) v.findViewById(R.id.tv_arjun_desc);
        tv14 = (TextView) v.findViewById(R.id.tv_jazeem);
        tv15 = (TextView) v.findViewById(R.id.tv_jazeem_mail);
        tv16 = (TextView) v.findViewById(R.id.tv_jazeem_desc);

        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue-Thin.otf");
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        tv5.setTypeface(tf);
        tv6.setTypeface(tf);
        tv7.setTypeface(tf);
        tv8.setTypeface(tf);
        tv9.setTypeface(tf);
        tv10.setTypeface(tf);
        tv11.setTypeface(tf);
        tv12.setTypeface(tf);
        tv13.setTypeface(tf);
        tv14.setTypeface(tf);
        tv15.setTypeface(tf);
        tv16.setTypeface(tf);



        List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
        contactInfos.add(new ContactInfo("Dilber Shahul","general secretary","+919567670916"));
        contactInfos.add(new ContactInfo("Abdul Wasih","convenor","+918547562834"));
        contactInfos.add(new ContactInfo("Sunil Mathew","faculty in charge","+919349769083"));
        contactInfos.add(new ContactInfo("Hafiz Muhammed","events co-ordinator","+918089219732"));
        contactInfos.add(new ContactInfo("Nandagopal R","events co-ordinator","+919567035935"));
        contactInfos.add(new ContactInfo("Christopher Jacob","marketing co-ordinator","+919846095751"));
        contactInfos.add(new ContactInfo("Kiran Ananth","public relations","+919947786864"));
        contactInfos.add(new ContactInfo("Shamil Puthukkot","public relations","919633425202"));
        contactInfos.add(new ContactInfo("Harikrishnan Subash","registrations","+919400575471"));
        contactInfos.add(new ContactInfo("Flemill Jose","hospitality","+919497416791"));
        contactInfos.add(new ContactInfo("Aby Anil John","workshops","+919746479909"));


        listView= (ListView) v.findViewById(R.id.listview);
        listView.setAdapter(new CustomContactAdapter(getActivity(),contactInfos));
        setListViewHeightBasedOnChildren(listView);



        try {
            MapsInitializer.initialize(getActivity());
        } catch (Exception e){}

        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(mBundle);
        setUpMapIfNeeded(v);




        mapAnimated=false;
        scrollView= (ScrollView) v.findViewById(R.id.scrollview);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.v("test","touched");
                if (!mapAnimated)
                    checkIfMapVisible();
                return false;
            }
        });

        mapMask = (ImageView) v.findViewById(R.id.map_mask);
        mapMask.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        scrollView.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });


        return v;
    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    private void setUpMapIfNeeded(View v) {
        if (map == null) {
            map = ((MapView) v.findViewById(R.id.map)).getMap();
            if (map != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLng(India));

    }

    private void checkIfMapVisible() {
        Rect scrollBounds = new Rect();
        scrollView.getHitRect(scrollBounds);
        if (mapView.getLocalVisibleRect(scrollBounds)) {
            // Any portion of the imageView, even a single pixel, is within the visible window
            mapAnimated=true;
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(NITMarker)      // Sets the center of the map to Mountain View
                    .zoom(14)                   // Sets the zoom
                            //.bearing(90)                // Sets the orientation of the camera to east
                            //.tilt(30)                   // Sets the tilt of the camera to 30 degrees

                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            final Marker nitMarker = map.addMarker(new MarkerOptions()
                    .position(NITMarker)
                    .title("Ragam")
                    .snippet("Don Your Capes.")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker)));




        } else {
            // NONE of the imageView is within the visible window
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroyView() {
        mapView.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
