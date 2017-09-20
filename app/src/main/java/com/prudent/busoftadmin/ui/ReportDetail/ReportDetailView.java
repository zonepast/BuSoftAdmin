package com.prudent.busoftadmin.ui.ReportDetail;

import com.prudent.busoftadmin.data.api.model.ReportDetail.Response.ReportDetailResponse;
import com.prudent.busoftadmin.ui.base.View;

/**
 * Created by Dharmik Patel on 12-Jun-17.
 */

public interface ReportDetailView extends View {

    void ShowProgress();

    void HideProgress();

    void Error(String Title,String Message);

    void ReportDetailResponse(ReportDetailResponse reportDetailResponse);
}
