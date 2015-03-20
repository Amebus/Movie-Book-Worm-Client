/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.brusamentoceruti.moviebookclient.gui;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Mattia
 */
@Named
@SessionScoped
@ManagedBean
public class SearchBean implements Serializable{
    
    private String title;
    
    private static final String baseURL = "http://localhost:8080/MovieBookREST/webresources/movie?q=";

    public SearchBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void search(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(baseURL+this.title);
        } catch (IOException ex) {
            Logger.getLogger(SearchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    
}
