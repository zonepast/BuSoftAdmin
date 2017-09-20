package com.prudent.busoftadmin.ui.AddEvent;

import com.prudent.busoftadmin.ui.base.View;

/**
 * Created by Dharmik Patel on 07-Jun-17.
 */

public interface AddEventView extends View {
    void ShowProgress();

    void HideProgress();

    void Error(String Title,String Message);
}
