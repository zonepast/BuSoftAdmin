package com.prudent.busoftadmin.ui.ChequeReceipt;

import com.google.gson.JsonElement;
import com.prudent.busoftadmin.data.api.model.CheckReceiptAmountCalc.Response.ChequeReceiptAmountResponse;
import com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Response.ChequeReceiptBaseResponse;
import com.prudent.busoftadmin.data.api.model.CheckReceiptSave.Request.CheckReceiptSaveRequest;
import com.prudent.busoftadmin.data.api.model.CheckReceiptSave.Response.CheckReceiptSaveResponse;
import com.prudent.busoftadmin.ui.base.View;

/**
 * Created by Dharmik Patel on 13-Jul-17.
 */

public interface ChequeReceiptView extends View {

    void ShowProgress();

    void HideProgress();

    void ShowBottomSheetProgress();

    void HideBottomSheetProgress();

    void Error(String Title,String Message);

    void ErrorAmountDialog(String Title,String Message);

    void ErrorSaveDialog(String Title,String Message);

    void ShowChequeReceiptResponse(ChequeReceiptBaseResponse response);

    void ShowChequeReceiptAmountResponse(ChequeReceiptAmountResponse response);

    void ShowChequeReceiptSaveResponse(CheckReceiptSaveResponse response);
}
