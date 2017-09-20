package com.prudent.busoftadmin.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.ReportDetail.Response.Table;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;

/**
 * Created by Dharmik Patel on 13-Jun-17.
 */

public class ReportDetailTableAdapter extends LongPressAwareTableDataAdapter<Table> {

    public ReportDetailTableAdapter(Context context, List<Table> data,final TableView<Table> tableView) {
        super(context, data,tableView);
    }

    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Table car = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderAccount(car);
                break;
            case 1:
                renderedView = renderOpening(car);
                break;
            case 2:
                renderedView = renderCredit(car);
                break;
            case 3:
                renderedView = renderDebit(car);
                break;
            case 4:
                renderedView = renderClosing(car);
                break;
            case 5:
                renderedView = renderChange(car);
                break;
        }
        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final Table car = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            default:
                renderedView = getDefaultCellView(rowIndex, columnIndex, parentView);
        }

        return renderedView;
    }

    private View renderAccount(final Table table) {
        final String priceString = table.getAccount();

        final TextView textView = new TextView(getContext());
        textView.setText(priceString);
        textView.setPadding(20, 10, 20, 10);
        //textView.setTextSize(TEXT_SIZE);

        /*if (car.getPrice() < 50000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_low));
        } else if (car.getPrice() > 100000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_high));
        }*/

        return textView;
    }

    private View renderChange(final Table table) {
        final String priceString = table.getChange();

        final TextView textView = new TextView(getContext());
        textView.setText(priceString);
        textView.setPadding(20, 10, 20, 10);
        //textView.setTextSize(TEXT_SIZE);

        /*if (car.getPrice() < 50000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_low));
        } else if (car.getPrice() > 100000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_high));
        }*/

        return textView;
    }

    private View renderClosing(final Table table) {
        final Double priceString = table.getClosing();

        final TextView textView = new TextView(getContext());
        textView.setText(""+priceString);
        textView.setPadding(20, 10, 20, 10);
        //textView.setTextSize(TEXT_SIZE);

        /*if (car.getPrice() < 50000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_low));
        } else if (car.getPrice() > 100000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_high));
        }*/

        return textView;
    }

    private View renderCredit(final Table table) {
        final Double credit = table.getCredit();

        final TextView textView = new TextView(getContext());
        textView.setText(""+credit);
        textView.setPadding(20, 10, 20, 10);
        //textView.setTextSize(TEXT_SIZE);

        /*if (car.getPrice() < 50000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_low));
        } else if (car.getPrice() > 100000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_high));
        }*/

        return textView;
    }

    private View renderDebit(final Table table) {
        final Double debit = table.getDebit();

        final TextView textView = new TextView(getContext());
        textView.setText(""+debit);
        textView.setPadding(20, 10, 20, 10);
        //textView.setTextSize(TEXT_SIZE);

        /*if (car.getPrice() < 50000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_low));
        } else if (car.getPrice() > 100000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_high));
        }*/

        return textView;
    }

    private View renderOpening(final Table table) {
        final Double opening = table.getOpening();

        final TextView textView = new TextView(getContext());
        textView.setText(""+opening);
        textView.setPadding(20, 10, 20, 10);
        //textView.setTextSize(TEXT_SIZE);

        /*if (car.getPrice() < 50000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_low));
        } else if (car.getPrice() > 100000) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_high));
        }*/

        return textView;
    }
}
