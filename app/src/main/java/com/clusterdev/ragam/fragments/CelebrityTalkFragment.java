package com.clusterdev.ragam.fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.clusterdev.ragam.R;
import com.clusterdev.ragam.WorkshopAdapter;

import java.util.ArrayList;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.clusterdev.ragam.fragments.CelebrityTalkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link com.clusterdev.ragam.fragments.CelebrityTalkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CelebrityTalkFragment extends Fragment {


    private TextView heading,description;
    private ListView list;
    private ArrayList<String> words;
    private SlidingDrawer slidingDrawer;
    private Typeface tf;
    private boolean openingForFirstTime;

    public static Fragment newInstance() {
        Fragment fragment = new CelebrityTalkFragment();

        return fragment;
    }

    public CelebrityTalkFragment() {
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

        View v=inflater.inflate(R.layout.fragment_celebrity_talks, container, false);
        openingForFirstTime=true;
        heading= (TextView) v.findViewById(R.id.workshop_heading);
        description= (TextView) v.findViewById(R.id.description_textview);
        tf= Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue-Thin.otf");
        heading.setTypeface(tf);
        list = (ListView) v.findViewById(R.id.listview);
        slidingDrawer= (SlidingDrawer) v.findViewById(R.id.slidingDrawer);
        words=new ArrayList<String>();
        words.add("Coderz");
        words.add("Google");
        words.add("Android");
        words.add("iPhone");
        words.add("Apple");
        list.setAdapter(new WorkshopAdapter(getActivity(),words));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,long arg3)
            {
                //args2 is the listViews Selected index
                TranslateAnimation SliderAnimationDown = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, slidingDrawer.getHeight());
                SliderAnimationDown.setDuration(1000);


                SliderAnimationDown.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        slidingDrawer.close();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                slidingDrawer.startAnimation(SliderAnimationDown);
                final Animation out = new AlphaAnimation(1.0f, 0.0f);
                out.setDuration(500);
                final Animation in = new AlphaAnimation(0.0f,1.0f);
                in.setDuration(500);
                out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        heading.setText(words.get(arg2));
                        description.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vel lectus nec nisi sodales congue et at justo. In pharetra ligula eget nisi convallis efficitur. Aliquam purus erat, fringilla vulputate eros faucibus, pharetra egestas nisl. Pellentesque at hendrerit nibh. Cras arcu sapien, iaculis quis tellus ac, maximus scelerisque libero. Suspendisse bibendum purus nisl, id faucibus justo sollicitudin in. Donec id lectus sed lacus vestibulum condimentum nec non felis.");
                        heading.startAnimation(in);
                        description.startAnimation(in);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                heading.startAnimation(out);
                description.startAnimation(out);
                description.setTypeface(tf);

            }
        });
        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                final Animation out = new AlphaAnimation(1.0f, 0.0f);
                out.setDuration(500);
                final Animation in = new AlphaAnimation(0.0f,1.0f);
                in.setDuration(500);
                out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        heading.setText("celebrity talks");
                        description.setText("");
                        heading.startAnimation(in);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                if (openingForFirstTime)
                    openingForFirstTime=false;
                else {
                    heading.startAnimation(out);
                    description.startAnimation(out);
                }

            }
        });
        slidingDrawer.open();

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
