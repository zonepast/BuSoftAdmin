package com.prudent.busoftadmin.ui.UploadDocument;

import com.prudent.busoftadmin.data.api.apihelper.ApiConfig;
import com.prudent.busoftadmin.data.api.apihelper.AppConfig;
import com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Request.DetailUploadDocumentRequest;
import com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Response.DetailUploadDocumentResponse;
import com.prudent.busoftadmin.data.api.model.ShowDocument.Request.ShowDocumentRequest;
import com.prudent.busoftadmin.data.api.model.ShowDocument.Response.ShowDocumentResponse;
import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Request.TranscationUploadDocumentRequest;
import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Response.TranscationUploadDocumentResponse;
import com.prudent.busoftadmin.data.api.model.UploadDocument.Request.UploadDocumentRequest;
import com.prudent.busoftadmin.data.api.model.UploadDocument.Response.UploadDocumentResponse;
import com.prudent.busoftadmin.ui.base.Presenter;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Dharmik Patel on 23-May-17.
 */

public class UploadDocumentPresenter implements Presenter<UploadDocumentView> {

    private UploadDocumentView uploadDocumentView;

    @Override
    public void onAttach(UploadDocumentView view) {
        uploadDocumentView = view;
    }

    @Override
    public void onDetach() {
        uploadDocumentView = null;
    }

    public void LoadDetail(String module,String Control,String userid,String CorpCentre,String para1,
                                String type) {
        uploadDocumentView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<DetailUploadDocumentResponse> call = getResponse.LoadDetail(
                new DetailUploadDocumentRequest(module,Control,userid,CorpCentre,
                        para1,type));
        call.enqueue(new Callback<DetailUploadDocumentResponse>() {
            @Override
            public void onResponse(Call<DetailUploadDocumentResponse> call, retrofit2.Response<DetailUploadDocumentResponse> response) {
                DetailUploadDocumentResponse serverResponse = response.body();
                uploadDocumentView.HideProgress();
                if (response.isSuccessful()) {
                    uploadDocumentView.ShowDetailResponse(serverResponse);
                } else {
                    uploadDocumentView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<DetailUploadDocumentResponse> call, Throwable t) {
                uploadDocumentView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    uploadDocumentView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    uploadDocumentView.Error("Timeout Error", "Please, Try again!");
                } else {
                    uploadDocumentView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void LoadDocument(String module,String userid,String CorpCentre,String type,String para1,
                           String para2) {
        uploadDocumentView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<ShowDocumentResponse> call = getResponse.ShowDocument(
                new ShowDocumentRequest(module,userid,CorpCentre,type,
                        para1,para2));
        call.enqueue(new Callback<ShowDocumentResponse>() {
            @Override
            public void onResponse(Call<ShowDocumentResponse> call, retrofit2.Response<ShowDocumentResponse> response) {
                ShowDocumentResponse serverResponse = response.body();
                uploadDocumentView.HideProgress();
                if (response.isSuccessful()) {
                    uploadDocumentView.ShowDocumentResponse(serverResponse);
                } else {
                    uploadDocumentView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<ShowDocumentResponse> call, Throwable t) {
                uploadDocumentView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    uploadDocumentView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    uploadDocumentView.Error("Timeout Error", "Please, Try again!");
                } else {
                    uploadDocumentView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void UploadDocument(String voucher,String srno,String date,String particular,
                               String doc_value, String doc_name, String doc_ext,
                               String doc_size,String corpCentre,String userid,
                               String EntryDateTime) {
        uploadDocumentView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<UploadDocumentResponse> call = getResponse.UploadDocument(
                new UploadDocumentRequest(voucher,srno,date,particular,doc_value,doc_name,
                        doc_ext,doc_size,corpCentre,userid,EntryDateTime));
        call.enqueue(new Callback<UploadDocumentResponse>() {
            @Override
            public void onResponse(Call<UploadDocumentResponse> call, retrofit2.Response<UploadDocumentResponse> response) {
                UploadDocumentResponse serverResponse = response.body();
                uploadDocumentView.HideProgress();
                if (response.isSuccessful()) {
                    uploadDocumentView.UploadDocumentResponse(serverResponse);
                } else {
                    uploadDocumentView.Error("Try Again","Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<UploadDocumentResponse> call, Throwable t) {
                uploadDocumentView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    uploadDocumentView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    uploadDocumentView.Error("Timeout Error", "Please, Try again!");
                } else {
                    uploadDocumentView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }
}
