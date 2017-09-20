package com.prudent.busoftadmin.ui.Calender;

import com.prudent.busoftadmin.data.api.model.Calender.Response.CalenderResponse;
import com.prudent.busoftadmin.ui.base.View;

/**
 * Created by Dharmik Patel on 06-Jun-17.
 */

public interface CalenderView extends View {
    void ShowProgress();

    void HideProgress();

    void Error(String Title,String Message);

    void CalenderResponse(CalenderResponse response);

}
