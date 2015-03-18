package com.clusterdev.ragam;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jazeem on 05/03/15.
 */
public class WorkshopAdapter extends CursorAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Typeface tf;

    public WorkshopAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
        this.tf = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue-Thin.otf");
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue-Thin.otf");
        int position=cursor.getPosition();
        View rowView = inflater.inflate(R.layout.workshop_row_odd, null);
        TextView title = (TextView) rowView.findViewById(R.id.workshop_tv_heading);
        TextView date = (TextView) rowView.findViewById(R.id.workshop_tv_date);
        TextView time = (TextView) rowView.findViewById(R.id.workshop_tv_time);
        TextView desc = (TextView) rowView.findViewById(R.id.workshop_tv_desc);
        title.setTypeface(tf);
        date.setTypeface(tf);
        time.setTypeface(tf);
        desc.setTypeface(tf);
        return rowView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int position=cursor.getPosition();
        View rowView;
        if (position % 2 == 0)
            view.setBackgroundColor(context.getResources().getColor(R.color.workshop_row_even));
        else
            view.setBackgroundColor(context.getResources().getColor(R.color.workshop_row_odd));

        TextView title = (TextView) view.findViewById(R.id.workshop_tv_heading);
        TextView date = (TextView) view.findViewById(R.id.workshop_tv_date);
        TextView time = (TextView) view.findViewById(R.id.workshop_tv_time);
        TextView desc = (TextView) view.findViewById(R.id.workshop_tv_desc);
        ImageView image= (ImageView) view.findViewById(R.id.workshop_tv_image);
        Date timeStamp=null;
        try {
             timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(cursor.getString(cursor.getColumnIndex("time")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int month=timeStamp.getMonth();
        int day=timeStamp.getDate();
        int hour=timeStamp.getHours();
        int mins=timeStamp.getMinutes();
        int h=hour%12;
        String f;
        if(hour>=12)
            f=" pm";
        else
            f=" am";

        if(h==0)
            h=12;

        date.setText(Integer.toString(day)+"mar");
        time.setText(h+":"+String.format("%02d",mins)+"\n"+f);

        String code=cursor.getString(cursor.getColumnIndex("code"));
        String name=cursor.getString(cursor.getColumnIndex("name"));
        code=code.toLowerCase();
        Log.d(name,code);

        int resID = context.getResources().getIdentifier(code, "drawable", context.getPackageName());
        Log.d("Res Id",Integer.toString(resID));
        image.setImageResource(resID);
        title.setText(name);
        desc.setText(cursor.getString(cursor.getColumnIndex("description")));

    }
}
