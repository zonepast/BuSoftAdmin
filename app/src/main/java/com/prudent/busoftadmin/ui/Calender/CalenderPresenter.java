package com.prudent.busoftadmin.ui.Calender;

import com.prudent.busoftadmin.data.api.apihelper.ApiConfig;
import com.prudent.busoftadmin.data.api.apihelper.AppConfig;
import com.prudent.busoftadmin.data.api.model.Calender.Request.CalenderRequest;
import com.prudent.busoftadmin.data.api.model.Calender.Response.CalenderResponse;
import com.prudent.busoftadmin.ui.base.Presenter;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Dharmik Patel on 06-Jun-17.
 */

public class CalenderPresenter implements Presenter<CalenderView> {

    private CalenderView calenderView;

    @Override
    public void onAttach(CalenderView view) {
        calenderView = view;
    }

    @Override
    public void onDetach() {
        calenderView = null;
    }

    public void Calender(String CorpCentre,String userid,String ip,String date) {
        calenderView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<CalenderResponse> call = getResponse.Calender(new CalenderRequest(CorpCentre,date,ip,userid));
        call.enqueue(new Callback<CalenderResponse>() {
            @Override
            public void onResponse(Call<CalenderResponse> call, retrofit2.Response<CalenderResponse> response) {
                CalenderResponse serverResponse = response.body();
                calenderView.HideProgress();
                if (response.isSuccessful()) {
                    calenderView.CalenderResponse(serverResponse);
                } else {
                    calenderView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<CalenderResponse> call, Throwable t) {
                calenderView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    calenderView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    calenderView.Error("Timeout Error", "Please, Try again!");
                } else {
                    calenderView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }
}
