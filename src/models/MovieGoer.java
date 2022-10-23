package models;

public class MovieGoer {
    private int movieGoersID;
    private String name;
    private String email;
    private int age;
    private String passwordHashed;
    private int mobileNumber;
    private String TID;

    public MovieGoer(int movieGoersID, String name, String email, int age, String passwordHashed, int mobileNumber, String TID){
        this.movieGoersID = movieGoersID;
        this.name = name;
        this.email = email;
        this.age = age;
        this.passwordHashed = passwordHashed;
        this.mobileNumber = mobileNumber;
        this.TID = TID;
    }

    // get methods
    public int getMovieGoersID()
}
