package com.prudent.busoftadmin.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.DashboardData.Response.Table1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dharmik Patel on 07-Jul-17.
 */

public class DashboardGraphAdapter extends RecyclerView.Adapter<DashboardGraphAdapter.ViewHolder> {

    private List<Table1> dataSet;
    private Activity mContext;

    public DashboardGraphAdapter(List<Table1> os_versions, Activity context) {
        this.dataSet = os_versions;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.layout_dashboard_report_graph_list, viewGroup, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Table1 fp = dataSet.get(i);
        String x = fp.getX();
        double y = fp.getY();
        double z = fp.getZ();
        double a = fp.getA();

        viewHolder.xTextDashboradReportGraph.setText(x);
        viewHolder.yTextDashboradReportGraph.setText(""+y);
        viewHolder.zTextDashboradReportGraph.setText(""+z);
        viewHolder.aTextDashboradReportGraph.setText(""+a);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.x_text_dashborad_report_graph)
        TextView xTextDashboradReportGraph;
        @BindView(R.id.y_text_dashborad_report_graph)
        TextView yTextDashboradReportGraph;
        @BindView(R.id.z_text_dashborad_report_graph)
        TextView zTextDashboradReportGraph;
        @BindView(R.id.a_text_dashborad_report_graph)
        TextView aTextDashboradReportGraph;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);

        }

    }

}