/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.brusamentoceruti.moviebookclient.gui;

import javax.ejb.Stateless;

/**
 *
 * @author federico
 */
@Stateless
public class ResultsBean {

    private static final String localhost = "http://localhost:8080/";
    private static final String baseURL = "MovieBookREST/webresources/movie?q=";
    
    private static final String otherHost = "/";
    
    public ResultsBean (){
        
    }
}
