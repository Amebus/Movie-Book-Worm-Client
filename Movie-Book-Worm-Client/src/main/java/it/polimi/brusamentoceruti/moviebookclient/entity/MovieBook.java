/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.brusamentoceruti.moviebookclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Federico
 */
public class MovieBook implements Serializable{
    
    private String title;
    private String year;
    private List<String> directors;
    private String poster;
    private int audience_rating;
    private int critics_rating;
    private List<String> books;
    
    public MovieBook(){  
    }
   
    
    public MovieBook(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public String getDirectorsAsString(){
        String dir = "";
        
        for(String s : this.directors){
            dir+=(s+";");
        }
        
        return dir;
    }
    
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getAudience_rating() {
        return audience_rating;
    }

    public void setAudience_rating(int audience_rating) {
        this.audience_rating = audience_rating;
    }

    public int getCritics_rating() {
        return critics_rating;
    }

    public void setCritics_rating(int critics_rating) {
        this.critics_rating = critics_rating;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
    
    
    
    
    
    
}
