package com.prudent.busoftadmin.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.Calender.Response.Table;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dharmik Patel on 08-Jun-17.
 */

public class EventAdapater extends RecyclerView.Adapter<EventAdapater.ViewHolder> {

    private List<Table> dataSet;
    private Activity mContext;

    public EventAdapater(List<Table> os_versions, Activity context) {
        this.dataSet = os_versions;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.calender_list, viewGroup, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Table fp = dataSet.get(i);
        String color = fp.getColor();
        String name = fp.getName();
        String starttime = fp.getStartTime();
        String endtime = fp.getEndTime();
        String date = fp.getFromDate();
        long id = fp.getId();

        String Date2 = date.replaceAll("T", " ");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dt1 = null;
        try {
            dt1 = format1.parse(Date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format = new SimpleDateFormat("dd MMM, yyyy");
        String finalDate = format.format(dt1);

        SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
        Date starttimeDt = null;
        try {
            starttimeDt = _24HourSDF.parse(starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endtimeDt = null;
        try {
            endtimeDt = _24HourSDF.parse(endtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String[] colorList = color.split(", ");
        int r = Integer.parseInt(colorList[0]);
        int g = Integer.parseInt(colorList[1]);
        int b = Integer.parseInt(colorList[1]);

        viewHolder.txtEventListName.setText(name);
        viewHolder.txtEventListDate.setText("Date : "+finalDate);
        viewHolder.txtEventListTime.setText(_12HourSDF.format(starttimeDt)+" - "+_12HourSDF.format(endtimeDt));
        viewHolder.txtEventListColor.setBackgroundColor(Color.rgb(r, g, b));

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_event_list_name)
        TextView txtEventListName;
        @BindView(R.id.txt_event_list_date)
        TextView txtEventListDate;
        @BindView(R.id.txt_event_list_time)
        TextView txtEventListTime;
        @BindView(R.id.txt_event_list_color)
        TextView txtEventListColor;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);

        }

    }

}

