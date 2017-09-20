package com.prudent.busoftadmin.events;

import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Response.Table;

import java.util.List;

/**
 * Created by Dharmik Patel on 24-May-17.
 */

public class TranscationDocumentEvent {

    private List<Table> response;

    public TranscationDocumentEvent(List<Table> response) {
        this.response = response;
    }

    public List<Table> getResponse() {
        return response;
    }
}
