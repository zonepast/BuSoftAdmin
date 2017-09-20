package com.prudent.busoftadmin.ui.Login;

import com.prudent.busoftadmin.data.api.apihelper.ApiConfig;
import com.prudent.busoftadmin.data.api.apihelper.AppConfig;
import com.prudent.busoftadmin.data.api.model.Login.Request.LoginRequest;
import com.prudent.busoftadmin.data.api.model.Login.Response.LoginResponse;
import com.prudent.busoftadmin.ui.base.Presenter;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Dharmik Patel on 22-May-17.
 */

public class LoginPresenter implements Presenter<LoginView> {

    private LoginView loginView;

    @Override
    public void onAttach(LoginView view) {
        loginView = view;
    }

    @Override
    public void onDetach() {
        loginView = null;
    }

    public void Login(String module, String Control,String CorpCentre,String userid,
                      String ip,String username,String password) {
        loginView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<LoginResponse> call = getResponse.Login(new LoginRequest(module,Control,
                CorpCentre,userid,ip,username,password));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                LoginResponse serverResponse = response.body();
                loginView.HideProgress();
                if (response.isSuccessful()) {
                    loginView.ShowLoginResponse(serverResponse);
                } else {
                    loginView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    loginView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    loginView.Error("Timeout Error", "Please, Try again!");
                } else {
                    loginView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }
}
