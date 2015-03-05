package com.clusterdev.ragam;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jazeem on 05/03/15.
 */
public class WorkshopAdapter extends BaseAdapter {
    private ArrayList<String> workshops;
    private Activity activity;
    private LayoutInflater inflater;
    private Typeface tf;
    public WorkshopAdapter(Activity activity, ArrayList<String> workshops){
        this.activity=activity;
        this.workshops=workshops;
        this.tf=Typeface.createFromAsset(activity.getAssets(),"fonts/HelveticaNeue-Thin.otf");
    }
    @Override
    public int getCount() {
        return workshops.size();
    }

    @Override
    public Object getItem(int position) {
        return workshops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            if (position % 2 == 0)
                convertView = inflater.inflate(R.layout.workshop_row_odd, null);
            else
                convertView = inflater.inflate(R.layout.workshop_row_even,null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.workshop_tv_heading);
        TextView date = (TextView) convertView.findViewById(R.id.workshop_tv_date);
        TextView time = (TextView) convertView.findViewById(R.id.workshop_tv_time);
        TextView desc = (TextView) convertView.findViewById(R.id.workshop_tv_desc);
        title.setTypeface(tf);
        date.setTypeface(tf);
        time.setTypeface(tf);
        desc.setTypeface(tf);
        String s = workshops.get(position);


        title.setText(s);



        return convertView;
    }
}
