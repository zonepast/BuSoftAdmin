package com.prudent.busoftadmin.ui.Dashboard;

import com.prudent.busoftadmin.data.api.model.DashboardData.Response.DashboardDataResponse;
import com.prudent.busoftadmin.data.api.model.Logout.Response.LogoutResponse;
import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Response.TranscationUploadDocumentResponse;
import com.prudent.busoftadmin.ui.base.View;

/**
 * Created by Dharmik Patel on 22-May-17.
 */

public interface DashboardView extends View {

    void ShowProgress();

    void HideProgress();

    void ShowProgressBar();

    void HideProgressBar();

    void Error(String Title,String Message);

    void ShowTransactionResponse(TranscationUploadDocumentResponse response);

    void LogoutResponse(LogoutResponse response);

    void DashboardResponse(DashboardDataResponse response);
}
