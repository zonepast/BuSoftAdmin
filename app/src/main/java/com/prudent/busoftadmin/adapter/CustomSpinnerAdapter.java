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
import com.prudent.busoftadmin.data.db.Local.Login.LoginRealm;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by Dharmik Patel on 22-May-17.
 */

public class CustomSpinnerAdapter extends ArrayAdapter<LoginRealm> {

    private Context context1;
    private RealmResults<LoginRealm> data;
    public Resources res;
    LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, RealmResults<LoginRealm> objects) {
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
        tvCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_white, 0);
        tvCategory.setText(data.get(position).getXname());
        tvCategory.setTag(data.get(position).getXcode());
        return row;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);
        tvCategory.setText(data.get(position).getXname());
        tvCategory.setTag(data.get(position).getXcode());
        return row;
    }
}
