/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.brusamentoceruti.moviebookclient.boundary;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author federico
 */
public class JsonRequest {
    
    public static final String BOOKS = "books";
    public static final String MOVIES = "movies";
    public static final String TITLE = "title";
    public static final String YEAR = "year";
    public static final String DIRECTORS = "directors";
    public static final String ADUDIENCE_RATING = "audience_rating";
    public static final String CRITICS_RATING = "critics_rating";
    public static final String POSTER = "poster";
    
    public static JSONObject doQuery (String Url) throws JSONException, IOException {
        String responseBody = null;
        HttpGet httpget;
        HttpClient httpClient = new DefaultHttpClient();
        try{
            httpget = new HttpGet (Url);
        }catch (IllegalArgumentException iae){
            return null;
        }

        HttpResponse response = httpClient.execute (httpget);
        InputStream contentStream = null;
        try {
            StatusLine statusLine = response.getStatusLine ();
            if (statusLine == null) {
                throw new IOException (
                    String.format ("Unable to get a response from server"));
            }
            int statusCode = statusLine.getStatusCode ();
            if (statusCode < 200 && statusCode >= 300) {
                throw new IOException (
                    String.format ("OWM server responded with status code %d: %s", statusCode, statusLine));
            }
            /* Read the response content */
            HttpEntity responseEntity = response.getEntity ();
            contentStream = responseEntity.getContent ();
            Reader isReader = new InputStreamReader (contentStream);
            int contentSize = (int) responseEntity.getContentLength ();
            if (contentSize < 0)
                contentSize = 8*1024;
            StringWriter strWriter = new StringWriter (contentSize);
            char[] buffer = new char[8*1024];
            int n = 0;
            while ((n = isReader.read(buffer)) != -1) {
                strWriter.write(buffer, 0, n);
            }
            responseBody = strWriter.toString ();
            contentStream.close ();
        } catch (IOException e) {
            throw e;
        } catch (RuntimeException re) {
            httpget.abort ();
            throw re;
        } finally {
            if (contentStream != null)
                contentStream.close ();
        }
        return new JSONObject (responseBody);
    }
    
}
