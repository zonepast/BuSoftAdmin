package com.prudent.busoftadmin.events;

/**
 * Created by Dharmik Patel on 24-May-17.
 */

public class LoadDocumentEvent {

    public String getDocument() {
        return document;
    }

    public LoadDocumentEvent setDocument(String document) {
        this.document = document;
        return this;
    }

    public String getDocumentname() {
        return documentname;
    }

    public LoadDocumentEvent setDocumentname(String documentname) {
        this.documentname = documentname;
        return this;
    }

    public String getDocumenttype() {
        return documenttype;
    }

    public LoadDocumentEvent setDocumenttype(String documenttype) {
        this.documenttype = documenttype;
        return this;
    }

    String document;
    String documentname;
    String documenttype;

    public LoadDocumentEvent(String document, String documentname, String documenttype) {
        this.document = document;
        this.documentname = documentname;
        this.documenttype = documenttype;
    }

}
