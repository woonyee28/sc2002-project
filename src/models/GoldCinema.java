package models;

import java.util.ArrayList;

public class GoldCinema extends Cinemas{

    public GoldCinema(String cinemaCode, String cinemaClass, ArrayList<Integer> seatingPlan, ArrayList<String> sessionsID) {
		super(cinemaCode,cinemaClass,seatingPlan,sessionsID);
	}

    @Override
    public String getCinemaClass() {
        return "Gold";
    }
    
}
