package models;

import java.util.ArrayList;

public class Sessions {
    int movieID;
    String sessionDate;
    String sessionTime;
    ArrayList<Integer> seatingPlan;

    public Sessions() {
    }

    public Sessions(int movieID, String sessionDate, String sessionTime, ArrayList<Integer> seatingPlan) {
        this.movieID = movieID;
        this.sessionDate = sessionDate;
        this.sessionTime = sessionTime;
        this.seatingPlan = seatingPlan;
    }

    public int getMovieID() {
        return this.movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getSessionDate() {
        return this.sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionTime() {
        return this.sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public ArrayList<Integer> getSeatingPlan() {
        return this.seatingPlan;
    }

    public void setSeatingPlan(ArrayList<Integer> seatingPlan) {
        this.seatingPlan = seatingPlan;
    }

    @Override
    public String toString() {
        return "{" +
            " movieID='" + getMovieID() + "'" +
            ", sessionDate='" + getSessionDate() + "'" +
            ", sessionTime='" + getSessionTime() + "'" +
            ", seatingPlan='" + getSeatingPlan() + "'" +
            "}";
    }
    
}
