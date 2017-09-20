package com.prudent.busoftadmin.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.DashboardData.Response.Table2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dharmik Patel on 10-Jun-17.
 */

public class DashboardStatementAdapter extends RecyclerView.Adapter<DashboardStatementAdapter.ViewHolder> {

    private List<Table2> dataSet;
    private Activity mContext;

    public DashboardStatementAdapter(List<Table2> os_versions, Activity context) {
        this.dataSet = os_versions;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.layout_dashboard_statement_list, viewGroup, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Table2 fp = dataSet.get(i);
        String color = fp.getColor();
        String ActType = fp.getActype();
        Double percentage = fp.getPerformance();
        //long id = fp.getId();

        viewHolder.txtDashboardStatement.setText(ActType);
        viewHolder.progressBarDashboardStatement.setProgress(percentage.intValue());
        if (color.equals("Green")) {
            viewHolder.progressBarDashboardStatement.setProgressColor(Color.parseColor("#1ABB9C"));
        } else {
            viewHolder.progressBarDashboardStatement.setProgressColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_dashboard_statement)
        TextView txtDashboardStatement;
        @BindView(R.id.progress_bar_dashboard_statement)
        RoundCornerProgressBar progressBarDashboardStatement;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);

        }

    }

}