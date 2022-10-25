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
    private ArrayList<Integer> reviewsID;

    public Movie(int movieID, String title, String type, String synopsis, int rating, String showingStatus, String director, ArrayList<String> cast, ArrayList<Integer> reviewsID)
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

    //get methods
    public int getMovieID(){
        return this.movieID;
    }

    public String getTitle(){
        return this.title;
    }

    public String getType(){
        return this.type;
    }

    public String getSynopsis(){
        return this.synopsis;
    }

    public int getRating(){
        return this.rating;
    }

    public String getShowingStatus(){
        return this.showingStatus;
    }

    public String getDirector(){
        return this.director;
    }

	public ArrayList<String> getCasts() {
		return this.cast;
	}

	public ArrayList<Integer> getReviewsID() {
		return this.reviewsID;
	}

    //set methods
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setShowingStatus(String showingStatus) {
		this.showingStatus = showingStatus;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

	public void setReviewID(ArrayList<Integer> reviewsID) {
		this.reviewsID = reviewsID;
	}

    // Generate String representation of the Object
    private static final String CSV_SEPARATOR = ",";
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getMovieID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getTitle());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getType());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getSynopsis());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getRating());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getShowingStatus());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getDirector());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getCasts());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getReviewsID()); 
        return oneLine.toString();
    }
}