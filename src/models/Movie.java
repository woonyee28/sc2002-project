package models;

import java.util.*;

public class Movie{
    private int movieID;
    private String title;
    private String type;
    private String synopsis;
    private int rating;
    private String showingStatus;
    private String director;
    private ArrayList<String> cast;
    private ArrayList<int> reviewsID;

    public Movie(int movieID, String title, String type, String synopsis, int rating, String showingStatus, String director, ArrayList<String> cast, ArrayList<int> reviewsID)
    {
        this.movieID = movieID;
        this.title = title;
        this.type = type;
        this.synopsis = synopsis;
        this.rating = rating;
        this.showingStatus = showingStatus;
        this.director = director;
        this.cast = cast;
        this.reviewsID = reviewsID;   
    }
}