package com.prudent.busoftadmin.utils;

import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ToggleButton;

/**
 * Created by Dharmik Patel on 13-Jul-17.
 */

public class ToggleAnimation {

    public ToggleAnimation(){

    }

    public ScaleAnimation GetToggleAnimation(){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);

        return scaleAnimation;
    }
}
