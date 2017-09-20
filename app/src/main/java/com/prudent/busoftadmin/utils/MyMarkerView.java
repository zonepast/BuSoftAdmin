package com.prudent.busoftadmin.utils;

import android.content.Context;
import android.widget.TextView;

import com.github.library.bubbleview.BubbleImageView;
import com.github.library.bubbleview.BubbleTextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.prudent.busoftadmin.R;

/**
 * Created by Dharmik Patel on 04-Jul-17.
 */

public class MyMarkerView extends MarkerView {

    private BubbleTextView tvContent;

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = (BubbleTextView) findViewById(R.id.tvContent);
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText("" + ce.getHigh());
        } else {

            tvContent.setText("" + e.getVal());
        }
    }

    @Override
    public int getXOffset() {
        // this will center the marker-view horizontally
        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset() {
        // this will cause the marker-view to be above the selected value
        return -getHeight();
    }

    private void ShowArrow(){

    }
}
