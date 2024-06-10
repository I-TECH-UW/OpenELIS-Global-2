package org.openelisglobal.dataexchange.common;

import java.util.List;

import org.apache.http.HttpStatus;

abstract public class HttpSender implements IExternalSender {

    protected String message;
    protected String url;
    protected int returnStatus = HttpStatus.SC_CREATED;
    String serviceTargetName = "";
    List<String> errors;

    abstract public boolean sendMessage();

    @Override
    public void setTargetName(String name) {
        serviceTargetName = name != null ? name : "";
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setURI(String url) {
        this.url = url;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public int getSendResponse() {
        return returnStatus;
    }

}