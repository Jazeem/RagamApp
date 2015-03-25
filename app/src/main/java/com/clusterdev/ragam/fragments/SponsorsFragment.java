package com.clusterdev.ragam.fragments;

import android.app.Activity;
import android.content.Intent;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class SponsorsFragment extends Fragment {

    private Typeface tf;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15;
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        Fragment fragment = new SponsorsFragment();
        return fragment;

    }

    public SponsorsFragment() {
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
        View v = inflater.inflate(R.layout.fragment_sponsors, container, false);
        tv1=(TextView)v.findViewById(R.id.tv_sponsor_heading);
        tv2=(TextView)v.findViewById(R.id.tv_sponsor_1);
        tv3=(TextView)v.findViewById(R.id.tv_sponsor_2);
        tv4=(TextView)v.findViewById(R.id.tv_sponsor_3);
        tv5=(TextView)v.findViewById(R.id.tv_sponsor_4);
        tv6=(TextView)v.findViewById(R.id.tv_sponsor_5);
        tv7=(TextView)v.findViewById(R.id.tv_sponsor_6);
        tv8=(TextView)v.findViewById(R.id.tv_sponsor_7);
        tv9=(TextView)v.findViewById(R.id.tv_sponsor_8);
        tv10=(TextView)v.findViewById(R.id.tv_sponsor_9);
        tv11=(TextView)v.findViewById(R.id.tv_sponsor_10);
        tv12=(TextView)v.findViewById(R.id.tv_sponsor_11);
        tv13=(TextView)v.findViewById(R.id.tv_sponsor_12);
        tv14=(TextView)v.findViewById(R.id.tv_sponsor_13);
        tv15=(TextView)v.findViewById(R.id.tv_sponsor_14);


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

        return v;
    }



    @Override
    public void onResume() {
        super.onResume();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {

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
