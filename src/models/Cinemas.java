package models;

import java.util.ArrayList;

public class Cinemas {
    String cinemaCode;
    String cinemaClass;
    ArrayList<Integer> seatingPlan;
    ArrayList<String> sessionsID;


    public Cinemas() {
    }

    /**
     * Initialise variables of any instance of Cinemas class
     * @param cinemaCode
     * @param cinemaClass
     * @param seatingPlan
     * @param sessionsID
     */
    public Cinemas(String cinemaCode, String cinemaClass, ArrayList<Integer> seatingPlan, ArrayList<String> sessionsID) {
        this.cinemaCode = cinemaCode;
        this.cinemaClass = cinemaClass;
        this.seatingPlan = seatingPlan;
        this.sessionsID = sessionsID;
    }

    /**
     * return cinemaCode
     * @return
     */
    public String getCinemaCode() {
        return this.cinemaCode;
    }

    /**
     * set cinemaCode
     * @param cinemaCode
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     * returns cinemaClass of a Cinema object - regular, gold, platinum
     * @return
     */
    public String getCinemaClass() {
        return this.cinemaClass;
    }

    /**
     * set cinemaClass
     * @param cinemaClass
     */
    public void setCinemaClass(String cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    /**
     * returns seating plan of a Cinema object
     * @return
     */
    public ArrayList<Integer> getSeatingPlan() {
        return this.seatingPlan;
    }

    /**
     * set seatingPlan for a Cinema object
     * @param seatingPlan
     */
    public void setSeatingPlan(ArrayList<Integer> seatingPlan) {
        this.seatingPlan = seatingPlan;
    }

    /**
     * returns sessionID of a Cinema Object
     * @return
     */
    public ArrayList<String> getSessionsID() {
        return this.sessionsID;
    }

    /**
     * set sessionID of a Cinema Object
     * @param sessionsID
     */
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
