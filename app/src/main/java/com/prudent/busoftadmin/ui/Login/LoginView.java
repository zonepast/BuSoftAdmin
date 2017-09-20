package com.prudent.busoftadmin.ui.Login;

import com.prudent.busoftadmin.data.api.model.Login.Response.LoginResponse;
import com.prudent.busoftadmin.ui.base.View;

/**
 * Created by Dharmik Patel on 22-May-17.
 */

public interface LoginView extends View {

    void ShowProgress();

    void HideProgress();

    void Error(String Title,String Message);

    void ShowLoginResponse(LoginResponse response);
}
