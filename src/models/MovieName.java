//@Author ZK

package models;
import java.util.*;

public class MovieName {
    private String movieName;
    private String movieDate;
    private Theatre theatre;

    private ArrayList<MovieSeat> seats;
    
    public MovieName(String movieName, String movieDate, Theatre theatre)
    {
        this.movieName = movieName;
        this.movieDate = movieDate;
        this.theatre = theatre;
    }

    public void loadMovieSeats()
    {
        for(layouts row : theatre.getRows())
        {
            for (MovieSeat seat: row.getSeats())
            {
                seats.add(seat);
            }
        }
    }

    public String getMovieName()
    {
        return this.movieName;
    }

    public Theatre getTheatre()
    {
        return this.theatre;
    }

    public ArrayList<MovieSeat> getSeats()
    {
        return seats;
    }
    public void setMovieName(String movieName)
    {
        this.movieName = movieName;
    }
    public void setMovieDate(String movieDate)
    {
        this.movieDate = movieDate;
    }
    public void setTheatre(Theatre theatre)
    {
        this.theatre = theatre;
    }

}
