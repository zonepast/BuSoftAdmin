package com.prudent.busoftadmin.ui.UploadDocument;

import com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Response.DetailUploadDocumentResponse;
import com.prudent.busoftadmin.data.api.model.Login.Response.LoginResponse;
import com.prudent.busoftadmin.data.api.model.ShowDocument.Response.ShowDocumentResponse;
import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Response.TranscationUploadDocumentResponse;
import com.prudent.busoftadmin.data.api.model.UploadDocument.Response.UploadDocumentResponse;
import com.prudent.busoftadmin.ui.base.View;

/**
 * Created by Dharmik Patel on 23-May-17.
 */

public interface UploadDocumentView extends View {

    void ShowProgress();

    void HideProgress();

    void Error(String Title,String Message);

    void ShowDetailResponse(DetailUploadDocumentResponse response);

    void ShowDocumentResponse(ShowDocumentResponse response);

    void UploadDocumentResponse(UploadDocumentResponse response);
}
