package com.prudent.busoftadmin.ui.ReportDetail;

import com.prudent.busoftadmin.data.api.apihelper.ApiConfig;
import com.prudent.busoftadmin.data.api.apihelper.AppConfig;
import com.prudent.busoftadmin.data.api.model.ReportDetail.Request.ReportDetailRequest;
import com.prudent.busoftadmin.data.api.model.ReportDetail.Response.ReportDetailResponse;
import com.prudent.busoftadmin.ui.base.Presenter;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Dharmik Patel on 12-Jun-17.
 */

public class ReportDetailPresenter implements Presenter<ReportDetailView> {

    private ReportDetailView reportDetailView;

    @Override
    public void onAttach(ReportDetailView view) {
        reportDetailView = view;
    }

    @Override
    public void onDetach() {
        reportDetailView = null;
    }

    public void LoadReport(String mCorpcentre, String mField1, String mReportname,
                           String mUserId){
        reportDetailView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<ReportDetailResponse> call = getResponse.GetReportDetail(new ReportDetailRequest
                (mCorpcentre,mField1,mReportname,mUserId));
        call.enqueue(new Callback<ReportDetailResponse>() {
            @Override
            public void onResponse(Call<ReportDetailResponse> call, retrofit2.Response<ReportDetailResponse> response) {
                ReportDetailResponse serverResponse = response.body();
                reportDetailView.HideProgress();
                if (response.isSuccessful()) {
                    reportDetailView.ReportDetailResponse(serverResponse);
                } else {
                    reportDetailView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<ReportDetailResponse> call, Throwable t) {
                reportDetailView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    reportDetailView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    reportDetailView.Error("Timeout Error", "Please, Try again!");
                } else {
                    reportDetailView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

}
