/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.brusamentoceruti.moviebookclient.gui;

import it.polimi.brusamentoceruti.moviebookclient.boundary.JsonRequest;
import it.polimi.brusamentoceruti.moviebookclient.entity.MovieBook;
import it.polimi.brusamentoceruti.moviebookclient.entity.MoviesResult;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author federico
 */
@SessionScoped
@ManagedBean
public class ResultsBean implements Serializable{

    private static final String localhost = "http://localhost:8080/";
    private static final String baseURL = "MovieBookREST/webresources/movie?q=";
    
    private static final String otherHost = "/";
    
    private String searchedTitle;
    private String searchedLimit;
    private JSONObject jsonResult;
    private MoviesResult results;

    public String getTitle() {
        return searchedTitle;
    }

    public void setTitle(String title) {
        this.searchedTitle = title;
    }

    public String getLimit() {
        return searchedLimit;
    }

    public void setLimit(String limit) {
        this.searchedLimit = limit;
    }

    public JSONObject getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JSONObject jsonResult) {
        this.jsonResult = jsonResult;
    }

    public MoviesResult getResults() {
        return results;
    }

    public void setResults(MoviesResult results) {
        this.results = results;
    }
    
    public ResultsBean (){
        results = new MoviesResult();
    }

    @PostConstruct
    private void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String s = request.getParameter("q");
        this.searchedTitle =  s;
        s= request.getParameter("limit");
        this.searchedLimit = s;
        s=createURL();
        obtainResults(s);
    } 
    
    private String createURL() {
        Integer i = Integer.parseInt(this.searchedLimit);
        return (i <= 0) ? (localhost+baseURL+this.searchedTitle) : localhost+baseURL+this.searchedTitle + "&limit=" + this.searchedLimit;
    }

    private void obtainResults(String s) {
        
        JSONArray jsa;
        List<String> l = new ArrayList();
        List<MovieBook> m = new ArrayList();
        try {
            
            jsonResult = JsonRequest.doQuery(s);
            jsa = jsonResult.getJSONArray(JsonRequest.BOOKS);
            
            for (int i = 0; i < jsa.length(); i++) {
                l.add(jsa.getString(i));
            }
            results.setBooks(l);
            
            jsa = jsonResult.getJSONArray(JsonRequest.MOVIES);
            
            for (int i = 0; i < jsa.length(); i++) {
                m.add(extractMovie(jsa.getJSONObject(i)));
            }
            
            results.setMovies(m);
        } catch (JSONException ex) {
            Logger.getLogger(ResultsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResultsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MovieBook extractMovie(JSONObject jsonObject) throws JSONException {
        JSONArray jsa;
        List<String> l = new ArrayList();
        MovieBook mb = new MovieBook(jsonObject.getString(JsonRequest.TITLE));
        
        mb.setAudience_rating(Integer.parseInt(jsonObject.getString(JsonRequest.ADUDIENCE_RATING)));
        mb.setCritics_rating(Integer.parseInt(jsonObject.getString(JsonRequest.CRITICS_RATING)));

        try
        {
            jsa = jsonObject.getJSONArray(JsonRequest.DIRECTORS);
            for (int i = 0; i < jsa.length(); i++) {
                l.add(jsa.getString(i));
            }
        }
        catch (JSONException ex){
            
        }
        mb.setDirectors(l);
        mb.setPoster(jsonObject.getString(JsonRequest.POSTER));
        mb.setYear(jsonObject.getString(JsonRequest.YEAR));
        
        return mb;
    }
    
    
    
}
