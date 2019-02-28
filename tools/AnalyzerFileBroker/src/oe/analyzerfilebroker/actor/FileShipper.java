package oe.analyzerfilebroker.actor;

import oe.analyzerfilebroker.config.GeneralConfig;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.HttpStatus;

import java.io.*;


public class FileShipper {
    private final String url;
    private final String name;
    private final String password;
    private final boolean pre53; //special case to handle messages from OE before 5.3


    public FileShipper(GeneralConfig config){
        url = config.getUrl();
        name = config.getName();
        password = config.getPassword();
        pre53 = config.isPre53();
    }


    public int sendFile( File textFile){
            return sendFile(textFile,false);
    }

    public int sendFile(File textFile, boolean isValidation){
        CloseableHttpClient httpclient = HttpClients.createDefault();
// The underlying HTTP connection is still held by the response object
// to allow the response content to be streamed directly from the network socket.
// In order to ensure correct deallocation of system resources
// the user MUST call CloseableHttpResponse#close() from a finally clause.
// Please note that if response content is not fully consumed the underlying
// connection cannot be safely re-used and will be shut down and discarded
// by the connection manager.

        int returnStatus;
        HttpPost httpPost = new HttpPost(url);
        FileBody fileBody = new FileBody(textFile);

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addTextBody("user", name)
                .addTextBody("password", password)
                .addPart("bin", fileBody)
                .build();


        httpPost.setEntity(reqEntity);

        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            //System.out.println(response.getStatusLine());
            returnStatus = response.getStatusLine().getStatusCode();
            HttpEntity httpEntity = response.getEntity();
            EntityUtils.consume(httpEntity);
            /*
            This is a special case for older servers, the server doesn't know how to handle ping.txt so it is
            expected to return a BAD_REQUEST
             */
            if( isValidation && returnStatus == HttpStatus.SC_BAD_REQUEST){
                returnStatus = HttpStatus.SC_OK;
            }
        }
        catch( Exception e){
            returnStatus = HttpStatus.SC_NOT_FOUND;
        }
        finally {
            if( response != null) {
                try {
                    response.close();
                }catch (IOException e){
                    returnStatus = 0;
                }
            }
        }

        return returnStatus;
    }
}
