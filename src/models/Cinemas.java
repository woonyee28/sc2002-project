package models;

import java.util.ArrayList;

public class Cinemas {
    String cinemaCode;
    String cinemaClass;
    ArrayList<Integer> seatingPlan;
    ArrayList<String> sessionsID;


    public Cinemas() {
    }

    public Cinemas(String cinemaCode, String cinemaClass, ArrayList<Integer> seatingPlan, ArrayList<String> sessionsID) {
        this.cinemaCode = cinemaCode;
        this.cinemaClass = cinemaClass;
        this.seatingPlan = seatingPlan;
        this.sessionsID = sessionsID;
    }

    public String getCinemaCode() {
        return this.cinemaCode;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    public String getCinemaClass() {
        return this.cinemaClass;
    }

    public void setCinemaClass(String cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    public ArrayList<Integer> getSeatingPlan() {
        return this.seatingPlan;
    }

    public void setSeatingPlan(ArrayList<Integer> seatingPlan) {
        this.seatingPlan = seatingPlan;
    }

    public ArrayList<String> getSessionsID() {
        return this.sessionsID;
    }

    public void setSessionsID(ArrayList<String> sessionsID) {
        this.sessionsID = sessionsID;
    }

    @Override
    public String toString() {
        return "{" +
            " cinemaCode='" + getCinemaCode() + "'" +
            ", cinemaClass='" + getCinemaClass() + "'" +
            ", seatingPlan='" + getSeatingPlan() + "'" +
            ", sessionsID='" + getSessionsID() + "'" +
            "}";
    }

    
}
