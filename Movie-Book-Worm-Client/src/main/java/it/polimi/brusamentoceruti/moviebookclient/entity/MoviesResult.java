/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.brusamentoceruti.moviebookclient.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Federico
 */
public class MoviesResult implements Serializable{
    
    private List<String> books;
    private List<MovieBook> movies;
    private int size;
    
    public MoviesResult(){
        this.books = new ArrayList<>();
        this.movies = new ArrayList<>();
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public List<MovieBook> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieBook> movies) {
        this.movies = movies;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
        
    public void addMovie(MovieBook movie){
        this.movies.add(movie);
    }
    
}
