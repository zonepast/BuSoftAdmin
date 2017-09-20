package com.prudent.busoftadmin.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prudent.busoftadmin.Helper.Constant;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.DashboardData.Response.Table3;
import com.prudent.busoftadmin.ui.ReportDetail.ReportDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dharmik Patel on 10-Jun-17.
 */

public class DashboardReportAdapter extends RecyclerView.Adapter<DashboardReportAdapter.ViewHolder> {

    private List<Table3> dataSet;
    private Activity mContext;

    public DashboardReportAdapter(List<Table3> os_versions, Activity context) {
        this.dataSet = os_versions;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.layout_dashboard_report_list, viewGroup, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Table3 fp = dataSet.get(i);
        String color = fp.getColor();
        String labelColor = fp.getPCColor();
        final String ActType = fp.getActype();
        final String Amount = fp.getAmount();
        String Label = fp.getLable();
        long percentage = fp.getPc();
        //long id = fp.getId();

        viewHolder.txtDashboardReportTitle.setText(ActType);
        viewHolder.txtDashboardReportNo.setText(Amount);
        if (color.equals("green")){
            viewHolder.txtDashboardReportNo.setTextColor(Color.parseColor("#1ABB9C"));
        }else {
            viewHolder.txtDashboardReportNo.setTextColor(Color.RED);
        }
        if (labelColor.equals("green")){
            viewHolder.txtDashboardReportLabel.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_drop_up_graph, 0, 0, 0);
            viewHolder.txtDashboardReportLabel.setTextColor(Color.parseColor("#1ABB9C"));
        }else {
            viewHolder.txtDashboardReportLabel.setTextColor(Color.RED);
            viewHolder.txtDashboardReportLabel.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_drop_down_graph, 0, 0, 0);
        }
        viewHolder.txtDashboardReportSublabel.setText(Label);
        viewHolder.txtDashboardReportLabel.setText(String.format("%.2f", Double.longBitsToDouble(percentage))+"%");

        viewHolder.cardViewGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ReportDetailActivity.class);
                i.putExtra(Constant.REPORTNAME,ActType);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_dashboard_report_title)
        TextView txtDashboardReportTitle;
        @BindView(R.id.layout_dashboard_report_title)
        LinearLayout layoutDashboardReportTitle;
        @BindView(R.id.txt_dashboard_report_no)
        TextView txtDashboardReportNo;
        @BindView(R.id.txt_dashboard_report_label)
        TextView txtDashboardReportLabel;
        @BindView(R.id.txt_dashboard_report_sublabel)
        TextView txtDashboardReportSublabel;
        @BindView(R.id.layout_dashboard_report_label)
        LinearLayout layoutDashboardReportLabel;
        @BindView(R.id.cardView_graph)
        CardView cardViewGraph;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);

        }

    }

}
