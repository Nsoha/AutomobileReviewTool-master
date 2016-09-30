package com.example.labuser.automobilereviewtool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<String> {


    public ItemAdapter(Context context, ArrayList<String> records) {

        super(context,0, records);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String s = getItem(position);

        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_detail_view,parent,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.txtReviewer);
        TextView age = (TextView) convertView.findViewById(R.id.txtTitle);

        String [] record = s.split(",");
        name.setText(record[0]);
        age.setText(record[1]);

        return convertView;
    }
}
