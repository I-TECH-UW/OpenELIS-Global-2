package org.openelisglobal.dataexchange.common;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.internationalization.MessageUtil;

abstract public class HttpSender implements IExternalSender {

    protected String message;
    protected String url;
    private static final int timeout = 10000;
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

    protected void setTimeout(HttpClient httpclient) {
        HttpConnectionManager connectionManager = httpclient.getHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(timeout);
    }

    protected void setPossibleErrors() {
        switch (returnStatus) {
        case HttpStatus.SC_UNAUTHORIZED: {
            errors.add(MessageUtil.getMessage("http.error.authorization") + url);
            break;
        }
        case HttpStatus.SC_INTERNAL_SERVER_ERROR: {
            errors.add(MessageUtil.getMessage("http.error.internal") + url);
            break;
        }
        case HttpStatus.SC_CONFLICT:
        case HttpStatus.SC_OK: {
            break; // NO-OP
        }
        default: {
            errors.add(MessageUtil.getMessage("http:error.unknown.status") + url);
        }
        }

    }

    protected void sendByHttp(HttpClient httpclient, HttpMethod httpPost) {
        try {
            try {
                httpclient.executeMethod(httpPost);
                returnStatus = httpPost.getStatusCode();
                setPossibleErrors();
            } catch (SocketTimeoutException e) {
                returnStatus = HttpServletResponse.SC_REQUEST_TIMEOUT;
                LogEvent.logError(e.toString(), e);
            } catch (ConnectTimeoutException e) {
                returnStatus = HttpServletResponse.SC_REQUEST_TIMEOUT;
                errors.add(e.getMessage() + " " + url);
                LogEvent.logError(e.toString(), e);
            } catch (HttpException e) {
                errors.add(MessageUtil.getMessage("http.error.unknown") + " " + url);
                LogEvent.logError(e.toString(), e);
            } catch (ConnectException e) {
                returnStatus = HttpServletResponse.SC_BAD_REQUEST;
                errors.add(MessageUtil.getMessage("http.error.noconnection") + " " + url);
                LogEvent.logError(e.toString(), e);
            } catch (UnknownHostException e) {
                returnStatus = HttpServletResponse.SC_NOT_FOUND;
                errors.add(MessageUtil.getMessage("http.error.unknownhost") + " " + url);
                LogEvent.logError(e.toString(), e);
            } catch (IOException e) {
                errors.add(MessageUtil.getMessage("http.error.io") + " " + url);
                LogEvent.logError(e.toString(), e);
            }
        } finally {
            httpPost.releaseConnection();
        }
    }
}