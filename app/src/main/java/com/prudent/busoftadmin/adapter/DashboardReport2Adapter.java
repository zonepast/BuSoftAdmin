package com.prudent.busoftadmin.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prudent.busoftadmin.Helper.Constant;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.DashboardData.Response.Table4;
import com.prudent.busoftadmin.ui.Report2Detail.Report2Activity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dharmik Patel on 11-Jul-17.
 */

public class DashboardReport2Adapter extends RecyclerView.Adapter<DashboardReport2Adapter.ViewHolder> {

    private List<Table4> dataSet;
    private Activity mContext;
    private Report2Adapter report2Adapter;

    public DashboardReport2Adapter(List<Table4> os_versions, Activity context,Report2Adapter report2Adaptern) {
        this.dataSet = os_versions;
        this.mContext = context;
        this.report2Adapter = report2Adaptern;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.dashboard_report2_list, viewGroup, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Table4 fp = dataSet.get(i);
        String color = fp.getClass_();
        String name = fp.getXname();
        final String xcode = fp.getXcode();

        viewHolder.txtMainReport2.setText(name);
        GradientDrawable drawable = (GradientDrawable) viewHolder.txtMainReport2.getBackground();
        if (color.equals("btn btn-success")) {
            drawable.setColor(Color.parseColor("#26B99A"));
        } else if (color.equals("btn btn-primary")) {
            drawable.setColor(Color.parseColor("#337ab7"));
        } else if (color.equals("btn btn-info")) {
            drawable.setColor(Color.parseColor("#5bc0de"));
        } else if (color.equals("btn btn-warning")) {
            drawable.setColor(Color.parseColor("#f0ad4e"));
        } else if (color.equals("btn btn-danger")) {
            drawable.setColor(Color.parseColor("#d9534f"));
        } else {
            drawable.setColor(Color.BLUE);
        }
        viewHolder.txtMainReport2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report2Adapter.GetReportData(xcode);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_main_report2)
        TextView txtMainReport2;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);

        }

    }

    public interface Report2Adapter{
        void GetReportData(String s);
    }

}
