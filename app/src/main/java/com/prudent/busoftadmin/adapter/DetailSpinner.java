package com.prudent.busoftadmin.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Response.Table;

import java.util.List;

/**
 * Created by Dharmik Patel on 23-May-17.
 */

public class DetailSpinner extends ArrayAdapter<Table> {

    private Context context1;
    private List<Table> data;
    public Resources res;
    LayoutInflater inflater;

    public DetailSpinner(Context context, List<Table> objects) {
        super(context, R.layout.spinner_layout, objects);
        context1 = context;
        data = objects;
        inflater = (LayoutInflater) context1
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinnerrow_title, parent, false);
        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);
        tvCategory.setText(data.get(position).getXname());
        tvCategory.setTag(data.get(position).getXcode());
        tvCategory.setTextColor(context1.getResources().getColor(R.color.primary_text));
        row.setPadding(0, row.getPaddingTop(), row.getPaddingRight(), row.getPaddingBottom());
        return row;
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);
        tvCategory.setText(data.get(position).getXname());
        tvCategory.setTag(data.get(position).getXcode());
        row.setPadding(0, row.getPaddingTop(), row.getPaddingRight(), row.getPaddingBottom());
        return row;
    }
}
