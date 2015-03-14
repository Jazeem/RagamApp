package com.clusterdev.ragam.fragments;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.clusterdev.ragam.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalleryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    private TextView tv1,tv2,tv3,tv4,tv5;
    private LatLng NIT= new LatLng(11.3193295,75.93319);
    private LatLng India=new LatLng(10.4754981,79.258464);
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private ScrollView scrollView;
    private ImageView mapMask;
    private boolean mapAnimated;
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
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue-Thin.otf");
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        tv5.setTypeface(tf);
        mapMask= (ImageView) v.findViewById(R.id.map_mask);
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

        return v;
    }

    private void checkIfMapVisible() {
        Rect scrollBounds = new Rect();
        scrollView.getHitRect(scrollBounds);
        if (mapMask.getLocalVisibleRect(scrollBounds)) {
            // Any portion of the imageView, even a single pixel, is within the visible window
            mapAnimated=true;
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(NIT)      // Sets the center of the map to Mountain View
                    .zoom(14)                   // Sets the zoom
                    //.bearing(90)                // Sets the orientation of the camera to east
                    //.tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            //map.addMarker(new MarkerOptions().position(NIT));
        } else {
            // NONE of the imageView is within the visible window
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (map == null) {
            map = mapFragment.getMap();

            map.moveCamera(CameraUpdateFactory.newLatLng(India));
        }
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
