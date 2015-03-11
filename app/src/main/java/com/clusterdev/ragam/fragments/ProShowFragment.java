package com.clusterdev.ragam.fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clusterdev.ragam.R;

import couk.jenxsol.parallaxscrollview.views.ObservableScrollView;

/**
 * Created by Jazeem on 10/03/15.
 */
public class ProShowFragment extends Fragment {
    private TextView heading,name1,day1,month1,desc1,name2,day2,month2,desc2,name3,day3,month3,desc3;
    private Typeface tf;
    private LinearLayout observableScroll;
    public static Fragment newInstance() {
        Fragment fragment = new ProShowFragment();

        return fragment;
    }

    public ProShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_pro_show, container, false);

        observableScroll= (LinearLayout) v.findViewById(R.id.observable_scroll);
        Log.v("scrollviewhight",observableScroll.getMeasuredHeight()+"");
        heading= (TextView) v.findViewById(R.id.pro_heading);
        name1= (TextView) v.findViewById(R.id.tv_name_1);
        day1= (TextView) v.findViewById(R.id.tv_day_1);
        month1= (TextView) v.findViewById(R.id.tv_month_1);
        desc1= (TextView) v.findViewById(R.id.tv_desc_1);
        name2= (TextView) v.findViewById(R.id.tv_name_2);
        day2= (TextView) v.findViewById(R.id.tv_day_2);
        month2= (TextView) v.findViewById(R.id.tv_month_2);
        desc2= (TextView) v.findViewById(R.id.tv_desc_2);
        name3= (TextView) v.findViewById(R.id.tv_name_3);
        day3= (TextView) v.findViewById(R.id.tv_day_3);
        month3= (TextView) v.findViewById(R.id.tv_month_3);
        desc3= (TextView) v.findViewById(R.id.tv_desc_3);
        tf= Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue-Thin.otf");
        heading.setTypeface(tf);
        name1.setTypeface(tf);
        month1.setTypeface(tf);
        day1.setTypeface(tf);
        desc1.setTypeface(tf);
        name2.setTypeface(tf);
        month2.setTypeface(tf);
        day2.setTypeface(tf);
        desc2.setTypeface(tf);
        name3.setTypeface(tf);
        month3.setTypeface(tf);
        day3.setTypeface(tf);
        desc3.setTypeface(tf);
        return v;
    }
    public void onButtonPressed(Uri uri) {

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
