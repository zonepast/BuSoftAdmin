package com.prudent.busoftadmin.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Response.Table;
import com.prudent.busoftadmin.model.CustomSpinner;

import java.util.List;

/**
 * Created by Dharmik Patel on 14-Jul-17.
 */

public class ChequeReceiptBasePartySpinner extends ArrayAdapter<CustomSpinner> {

    private Context context1;
    private List<CustomSpinner> data;
    public Resources res;
    LayoutInflater inflater;

    public ChequeReceiptBasePartySpinner(Context context, List<CustomSpinner> objects) {
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

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinnerrow_title, parent, false);
        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);
        tvCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop_down, 0);
        tvCategory.setText(data.get(position).getmXname());
        tvCategory.setTag(data.get(position).getmXcode());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            tvCategory.setTextAppearance(context1, android.support.v7.appcompat.R.style.Base_TextAppearance_AppCompat_Small);
        } else {
            tvCategory.setTextAppearance(android.support.v7.appcompat.R.style.Base_TextAppearance_AppCompat_Small);
        }
        tvCategory.setTextColor(Color.BLACK);

        return row;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);

        tvCategory.setText(data.get(position).getmXname());
        tvCategory.setTag(data.get(position).getmXcode());
        return row;
    }

}
