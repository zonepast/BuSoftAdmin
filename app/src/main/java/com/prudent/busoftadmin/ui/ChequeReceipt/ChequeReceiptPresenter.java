package com.prudent.busoftadmin.ui.ChequeReceipt;

import com.google.gson.JsonElement;
import com.prudent.busoftadmin.data.api.apihelper.ApiConfig;
import com.prudent.busoftadmin.data.api.apihelper.AppConfig;
import com.prudent.busoftadmin.data.api.model.CheckReceiptAmountCalc.Request.CheckReceiptAmountRequest;
import com.prudent.busoftadmin.data.api.model.CheckReceiptAmountCalc.Response.ChequeReceiptAmountResponse;
import com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Request.ChequeReceiptBaseRequest;
import com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Response.ChequeReceiptBaseResponse;
import com.prudent.busoftadmin.data.api.model.CheckReceiptSave.Request.CheckReceiptSaveRequest;
import com.prudent.busoftadmin.data.api.model.CheckReceiptSave.Response.CheckReceiptSaveResponse;
import com.prudent.busoftadmin.ui.base.Presenter;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Dharmik Patel on 14-Jul-17.
 */

public class ChequeReceiptPresenter implements Presenter<ChequeReceiptView> {

    private ChequeReceiptView chequeReceiptView;

    @Override
    public void onAttach(ChequeReceiptView view) {
       chequeReceiptView = view;
    }

    @Override
    public void onDetach() {
       chequeReceiptView = null;
    }

    public void Base(String type,String CorpCentre,String userId) {
        chequeReceiptView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<ChequeReceiptBaseResponse> call = getResponse.GetChequeReceiptBase(new ChequeReceiptBaseRequest
                (userId, CorpCentre,type));
        call.enqueue(new Callback<ChequeReceiptBaseResponse>() {
            @Override
            public void onResponse(Call<ChequeReceiptBaseResponse> call, retrofit2.Response<ChequeReceiptBaseResponse> response) {
                ChequeReceiptBaseResponse serverResponse = response.body();
                chequeReceiptView.HideProgress();
                if (response.isSuccessful()) {
                    chequeReceiptView.ShowChequeReceiptResponse(serverResponse);
                } else {
                    chequeReceiptView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<ChequeReceiptBaseResponse> call, Throwable t) {
                chequeReceiptView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    chequeReceiptView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    chequeReceiptView.Error("Timeout Error", "Please, Try again!");
                } else {
                    chequeReceiptView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void AmountCalc(String control,String date1,String party,String rate,
                           String Bank,String date2,String days,String amount) {
        chequeReceiptView.ShowBottomSheetProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<ChequeReceiptAmountResponse> call = getResponse.GetChequeReceiptAmount(new CheckReceiptAmountRequest(control,
                "",date1,party,"",rate,"",Bank,"",date2,"","",days,amount));
        call.enqueue(new Callback<ChequeReceiptAmountResponse>() {
            @Override
            public void onResponse(Call<ChequeReceiptAmountResponse> call, retrofit2.Response<ChequeReceiptAmountResponse> response) {
                ChequeReceiptAmountResponse serverResponse = response.body();
                chequeReceiptView.HideBottomSheetProgress();
                if (response.isSuccessful()) {
                   chequeReceiptView.ShowChequeReceiptAmountResponse(serverResponse);
                } else {
                    chequeReceiptView.ErrorAmountDialog("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<ChequeReceiptAmountResponse> call, Throwable t) {
                chequeReceiptView.HideBottomSheetProgress();
                if (t instanceof java.net.ConnectException) {
                    chequeReceiptView.ErrorAmountDialog("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    chequeReceiptView.ErrorAmountDialog("Timeout Error", "Please, Try again!");
                } else {
                    chequeReceiptView.ErrorAmountDialog("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void SaveChequeReceipt(String srno, String date, String party,
                                  String rate, String chqno, String bankname,
                                  String chqdate, String total, String amount,
                                  String interest, String netamt, String adjamt,
                                  String payable, String userid,
                                  String entrydatetime, String editedby,
                                  String editdatetime, String corpcentre,
                                  String unitCorp, String terminal) {
        chequeReceiptView.ShowBottomSheetProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<CheckReceiptSaveResponse> call = getResponse.SaveChequeReceiptAmount
                (new CheckReceiptSaveRequest(srno, date,party,rate,chqno,bankname,
                        chqdate, total,amount,interest,netamt,adjamt,payable,userid,
                        entrydatetime,editedby,editdatetime,corpcentre,unitCorp,
                        terminal));
        call.enqueue(new Callback<CheckReceiptSaveResponse>() {
            @Override
            public void onResponse(Call<CheckReceiptSaveResponse> call, retrofit2.Response<CheckReceiptSaveResponse> response) {
                CheckReceiptSaveResponse serverResponse = response.body();
                chequeReceiptView.HideBottomSheetProgress();
                if (response.isSuccessful()) {
                    chequeReceiptView.ShowChequeReceiptSaveResponse(serverResponse);
                } else {
                    chequeReceiptView.ErrorSaveDialog("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<CheckReceiptSaveResponse> call, Throwable t) {
                chequeReceiptView.HideBottomSheetProgress();
                if (t instanceof java.net.ConnectException) {
                    chequeReceiptView.ErrorSaveDialog("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    chequeReceiptView.ErrorSaveDialog("Timeout Error", "Please, Try again!");
                } else {
                    chequeReceiptView.ErrorSaveDialog("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }
}
