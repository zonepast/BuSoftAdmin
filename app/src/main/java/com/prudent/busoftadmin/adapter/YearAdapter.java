package com.prudent.busoftadmin.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.CalenderDate;

import java.util.List;

/**
 * Created by Dharmik Patel on 06-Jun-17.
 */

public class YearAdapter extends ArrayAdapter<CalenderDate> {

    private Context context1;
    private List<CalenderDate> data;
    public Resources res;
    LayoutInflater inflater;

    public YearAdapter(Context context, List<CalenderDate> objects) {
        super(context, R.layout.spinner_row, objects);

        context1 = context;
        data = objects;

        inflater = (LayoutInflater) context1
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);
        tvCategory.setText(data.get(position).getYearName());
        tvCategory.setTag(data.get(position).getYearId());
        return row;
    }
}