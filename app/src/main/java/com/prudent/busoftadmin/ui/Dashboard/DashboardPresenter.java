package com.prudent.busoftadmin.ui.Dashboard;

import com.prudent.busoftadmin.data.api.apihelper.ApiConfig;
import com.prudent.busoftadmin.data.api.apihelper.AppConfig;
import com.prudent.busoftadmin.data.api.model.DashboardData.Request.DashboardDataRequest;
import com.prudent.busoftadmin.data.api.model.DashboardData.Response.DashboardDataResponse;
import com.prudent.busoftadmin.data.api.model.Logout.Request.LogoutRequest;
import com.prudent.busoftadmin.data.api.model.Logout.Response.LogoutResponse;
import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Request.TranscationUploadDocumentRequest;
import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Response.TranscationUploadDocumentResponse;
import com.prudent.busoftadmin.ui.base.Presenter;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Dharmik Patel on 22-May-17.
 */

public class DashboardPresenter implements Presenter<DashboardView> {

    private DashboardView dashboardView;

    @Override
    public void onAttach(DashboardView view) {
        dashboardView = view;
    }

    @Override
    public void onDetach() {
        dashboardView = null;
    }

    public void LoadTransaction(String module, String CorpCentre,String userid,
                                String type) {
        dashboardView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<TranscationUploadDocumentResponse> call = getResponse.LoadTranscation
                (new TranscationUploadDocumentRequest(module,userid,CorpCentre,
                        type));
        call.enqueue(new Callback<TranscationUploadDocumentResponse>() {
            @Override
            public void onResponse(Call<TranscationUploadDocumentResponse> call, retrofit2.Response<TranscationUploadDocumentResponse> response) {
                TranscationUploadDocumentResponse serverResponse = response.body();
                dashboardView.HideProgress();
                if (response.isSuccessful()) {
                    dashboardView.ShowTransactionResponse(serverResponse);
                } else {
                    dashboardView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<TranscationUploadDocumentResponse> call, Throwable t) {
                dashboardView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    dashboardView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    dashboardView.Error("Timeout Error", "Please, Try again!");
                } else {
                    dashboardView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void Logout(String module, String Control,String CorpCentre,String userid,
                      String ip,String username,String password) {
        dashboardView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<LogoutResponse> call = getResponse.Logout(new LogoutRequest(Control,
                CorpCentre,ip,module,password,userid,username));
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, retrofit2.Response<LogoutResponse> response) {
                LogoutResponse serverResponse = response.body();
                dashboardView.HideProgress();
                if (response.isSuccessful()) {
                    dashboardView.LogoutResponse(serverResponse);
                } else {
                    dashboardView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                dashboardView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    dashboardView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    dashboardView.Error("Timeout Error", "Please, Try again!");
                } else {
                    dashboardView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void LoadDashboardData(String reportname, String CorpCentre,String userid) {
        dashboardView.ShowProgressBar();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<DashboardDataResponse> call = getResponse.GetDashboard(
                new DashboardDataRequest(CorpCentre, reportname,userid));
        call.enqueue(new Callback<DashboardDataResponse>() {
            @Override
            public void onResponse(Call<DashboardDataResponse> call, retrofit2.Response<DashboardDataResponse> response) {
                DashboardDataResponse serverResponse = response.body();
                dashboardView.HideProgressBar();
                if (response.isSuccessful()) {
                    dashboardView.DashboardResponse(serverResponse);
                } else {
                    dashboardView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<DashboardDataResponse> call, Throwable t) {
                dashboardView.HideProgressBar();
                if (t instanceof java.net.ConnectException) {
                    dashboardView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    dashboardView.Error("Timeout Error", "Please, Try again!");
                } else {
                    dashboardView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }
}
