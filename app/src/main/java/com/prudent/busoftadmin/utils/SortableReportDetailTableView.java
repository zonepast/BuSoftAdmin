package com.prudent.busoftadmin.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.ReportDetail.Response.Table;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

/**
 * Created by Dharmik Patel on 13-Jun-17.
 */

public class SortableReportDetailTableView extends SortableTableView<Table> {

    public SortableReportDetailTableView(final Context context) {
        this(context, null);
    }

    public SortableReportDetailTableView(final Context context, final AttributeSet attributes) {
        this(context, attributes, android.R.attr.listViewStyle);
    }

    public SortableReportDetailTableView(final Context context, final AttributeSet attributes, final int styleAttributes) {
        super(context, attributes, styleAttributes);

        final SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context, "Account", "Opening", "Credit", "Debit",
                "Closing","Change");
        simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(context, R.color.white));
        simpleTableHeaderAdapter.setTextSize(13);
        setHeaderAdapter(simpleTableHeaderAdapter);

        int colorEvenRows = getResources().getColor(R.color.white);
        int colorOddRows = getResources().getColor(R.color.grey);
        setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());

        final TableColumnWeightModel tableColumnWeightModel = new TableColumnWeightModel(6);
        tableColumnWeightModel.setColumnWeight(0, 2);
        tableColumnWeightModel.setColumnWeight(1, 1);
        tableColumnWeightModel.setColumnWeight(2, 1);
        tableColumnWeightModel.setColumnWeight(3, 1);
        tableColumnWeightModel.setColumnWeight(4, 1);
        tableColumnWeightModel.setColumnWeight(5, 1);
        setColumnModel(tableColumnWeightModel);

        setColumnComparator(0, ReportDetailComparator.getAccountComparator());
        setColumnComparator(1, ReportDetailComparator.getOpeningComparator());
        setColumnComparator(2, ReportDetailComparator.getCreditComparator());
        setColumnComparator(3, ReportDetailComparator.getDebitComparator());
        setColumnComparator(4, ReportDetailComparator.getClosingComparator());
        setColumnComparator(5, ReportDetailComparator.getChangeComparator());
    }

}
