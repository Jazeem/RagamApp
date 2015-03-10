package com.clusterdev.ragam.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.clusterdev.ragam.CustomCursorAdapter;
import com.clusterdev.ragam.DataBaseHelper;
import com.clusterdev.ragam.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView eventsList;
    ListView categoriesList;
    DataBaseHelper db;
    Cursor eventsCursor;
    Cursor categoryCursor;
    CustomCursorAdapter eventsAdapter;
    CustomCursorAdapter categoriesAdapter;
    AlphaAnimation customFadeIn;
    AlphaAnimation customFadeOut;
    String category = null;
    TextView heading;
    View rightForeground;
    AdapterView.OnItemClickListener categoryClickListner;
    AdapterView.OnItemClickListener eventClickListner;
    View events_layout;
    TextView description;
    String selectedEvent;
    View backButton;
    boolean isEventSelected=false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public static Fragment newInstance() {
        Fragment fragment = new EventsFragment();
        return fragment;
    }

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventClickListner = new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// previously invisible view

                TextView textView= (TextView) view;

                for(int i=0;i<parent.getChildCount();i++)
                    setSelectedDesign((TextView) parent.getChildAt(i),true);
                setSelectedDesign(textView, false);

// get the center for the clipping circle
                selectedEvent=textView.getText().toString();
                heading.setText(selectedEvent);
                AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                fadeOut.setDuration(500);
                fadeOut.setFillAfter(true);

                Animation scaleAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.scale);
                categoriesList.setOnItemClickListener(null);
                eventsList.setOnItemClickListener(null);
                scaleAnim.setDuration(1200);
                scaleAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.d("Scale", "Stopped");
                        AlphaAnimation newfadeIn;
                        newfadeIn = new AlphaAnimation(0.0f, 1.0f);
                        newfadeIn.setDuration(500);
                        newfadeIn.setFillAfter(true);

                        description.setVisibility(View.VISIBLE);
                        description.startAnimation(newfadeIn);
                        Log.d("Fade In description","Started");
                        isEventSelected=true;

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                rightForeground.startAnimation(scaleAnim);
                events_layout.startAnimation(fadeOut);
// make the view visible and start the animation
                rightForeground.setVisibility(View.VISIBLE);

                //anim.start();


            }
        };

        categoryClickListner = new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;

                if(category!=null && category.equals(textView.getText().toString())){
                    return;
                }



                for (int j = 0; j < parent.getChildCount(); j++)
                    setSelected((TextView) parent.getChildAt(j), false);

                setSelected(textView, true);
            }

        };

        db = new DataBaseHelper(getActivity());

        try {
            db.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.openDataBase();
        categoryCursor = db.getGenres("COMPETITIONS");

        eventsAdapter = new CustomCursorAdapter(getActivity(), null, true);
        categoriesAdapter = new CustomCursorAdapter(getActivity(), categoryCursor, false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_events, container, false);
        eventsList = (ListView) v.findViewById(R.id.events);
        categoriesList = (ListView) v.findViewById(R.id.categories);
        rightForeground = v.findViewById(R.id.right_foreground);
        events_layout= v.findViewById(R.id.events_layout);
        backButton=v.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPressed();
            }
        });
        description= (TextView) v.findViewById(R.id.description);
        eventsList.setOnItemClickListener(eventClickListner);
        categoriesList.setOnItemClickListener(categoryClickListner);




        eventsList.setAdapter(eventsAdapter);
        categoriesList.setAdapter(categoriesAdapter);

        heading = (TextView) v.findViewById(R.id.events_heading);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue-Thin.otf");
        heading.setTypeface(tf);
        description.setTypeface(tf);


//        heading.startAnimation(fadeIn);
//        heading.setVisibility(View.VISIBLE);


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    private void setSelected(TextView view, boolean selected) {
        if (selected) {

            String selectedCategory = view.getText().toString();
            Log.d("Selected ",selectedCategory);
            if (category == null) {
                category = selectedCategory;
                setSelectedDesign(view, selected);
                fadeIn();
                return;
            } else if (selectedCategory.equals(category)) {
                return;
            } else {
                category = selectedCategory;
                setSelectedDesign(view, selected);

                customFadeOut = new AlphaAnimation(1.0f, 0.0f);
                customFadeOut.setDuration(500);
                customFadeOut.setFillAfter(true);
                customFadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        events_layout.setVisibility(View.GONE);

                        fadeIn();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                events_layout.startAnimation(customFadeOut);
                return;
            }
        } else {

            setSelectedDesign(view, selected);

            return;
        }


    }
    public void backPressed() {
        if(!isEventSelected)
            return;
        isEventSelected=false;
        final Animation shrinkAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.shrink);
        categoriesList.setOnItemClickListener(null);
        shrinkAnim.setDuration(1200);
        final AlphaAnimation fadeIn;
        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        fadeIn.setFillAfter(true);
        AlphaAnimation fadeOut;
        fadeOut = new AlphaAnimation(1.0f,0.0f);
        fadeOut.setDuration(500);
        heading.setText("competitions");
        // fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                description.setVisibility(View.GONE);
                events_layout.startAnimation(fadeIn);


                rightForeground.startAnimation(shrinkAnim);
                shrinkAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        eventsList.setOnItemClickListener(eventClickListner);
                        categoriesList.setOnItemClickListener(categoryClickListner);
                        rightForeground.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                rightForeground.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        description.startAnimation(fadeOut);



    }


    private void setSelectedDesign(TextView view, boolean selected) {

        if (selected) {
            view.setBackgroundColor(getResources().getColor(R.color.events_color));
            view.setTextColor(getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.white));
            view.setTextColor(getResources().getColor(R.color.events_color));
        }
    }


    private void fadeIn() {

        eventsCursor = db.getEventsByGenre(category);
        eventsAdapter=new CustomCursorAdapter(getActivity(), eventsCursor,true);
        eventsList.setAdapter(eventsAdapter);
        customFadeIn = new AlphaAnimation(0.0f, 1.0f);
        customFadeIn.setDuration(500);
        customFadeIn.setFillAfter(true);
        events_layout.setVisibility(View.VISIBLE);
        events_layout.startAnimation(customFadeIn);

    }

}
