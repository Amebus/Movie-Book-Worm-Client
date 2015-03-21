/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.brusamentoceruti.moviebookclient.gui;

import it.polimi.brusamentoceruti.moviebookclient.boundary.JsonRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
    
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
    private String limit;
    private JSONObject result;
    
    public ResultsBean (){
    }

    @PostConstruct
    private void init(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String s = request.getParameter("q");
        this.title =  s;
        s= request.getParameter("limit");
        this.limit = s;
        s=createURL();
        
        try {
            result = JsonRequest.doQuery(s);
        } catch (JSONException ex) {
            Logger.getLogger(ResultsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResultsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    private String createURL() {
        Integer i = Integer.parseInt(this.limit);
        return (i <= 0) ? (localhost+baseURL+this.title) : localhost+baseURL+this.title + "&limit=" + this.limit;
    }
    
    
    
}
