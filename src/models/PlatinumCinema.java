package models;

import java.util.ArrayList;

public class PlatinumCinema extends Cinemas{
    public PlatinumCinema(String cinemaCode, String cinemaClass, ArrayList<Integer> seatingPlan, ArrayList<String> sessionsID) {
		super(cinemaCode,cinemaClass,seatingPlan,sessionsID);
	}

    @Override
    public String getCinemaClass() {
        return "Platinum";
    }
}
