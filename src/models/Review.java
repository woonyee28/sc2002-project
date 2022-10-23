package models;

public class Review {
    private int reviewsID;
    private int movieGoersID;
    private double rating;
    private String reviews;
    private int movieID;

    public Review(int reviewsID, int movieGoersID, double rating, String reviews, int movieID){
        this.reviewsID = reviewsID;
        this.movieGoersID = movieGoersID;
        this.rating = rating;
        this.reviews = reviews;
        this. movieID = movieID;
    }

    // get methods
    public int getReviewsID(){
        return this.reviewsID;
    }

    public int getMovieGoersID(){
        return this.movieGoersID;
    }

    public double getRating(){
        return this.rating;
    }

    public String getReviews(){
        return this.reviews;
    }

    public int getMovieID(){
        return this.movieID;
    }

    // set methods
    public void setReviewsID(int reviewsID){
        this.reviewsID = reviewsID;
    }

    public void setMovieGoersID(int movieGoersID){
        this.movieGoersID = movieGoersID;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public void setReviews(String reviews){
        this.reviews = reviews;
    }

    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    // Generate String representation of the Object
    private static final String CSV_SEPARATOR = ",";
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getReviewsID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMovieGoersID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getRating());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getReviews());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMovieID());

        return oneLine.toString();
    }
}
