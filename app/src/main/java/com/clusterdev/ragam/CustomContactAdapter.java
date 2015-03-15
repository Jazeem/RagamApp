package com.clusterdev.ragam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jazeem on 15/03/15.
 */
public class CustomContactAdapter extends ArrayAdapter {
    public List<ContactInfo> contactInfos;
    private Context context;
    private TextView tv1;
    private TextView tv2;
    private ImageButton call;
    private Typeface tf;
    public CustomContactAdapter(Context context, List<ContactInfo> contactInfos) {
        super(context,0, contactInfos);
        this.contactInfos=contactInfos;
        this.context=context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.contact_row,parent,false);

        tf = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue-Thin.otf");
            tv1 =(TextView) convertView.findViewById(R.id.tv_contact_name);
            tv2 = (TextView) convertView.findViewById(R.id.tv_contact_designation);
            call= (ImageButton) convertView.findViewById(R.id.call_button);
        final ContactInfo contactInfo = (ContactInfo)contactInfos.get(position);
        tv1.setText(contactInfo.name);
        tv2.setText(contactInfo.designation);
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        // set the name to the text;
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactInfo.mobileNo));
                context.startActivity(intent);
            }
        });
        return convertView;

    }
}
