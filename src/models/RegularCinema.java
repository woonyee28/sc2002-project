package models;

import java.util.ArrayList;

public class RegularCinema extends Cinemas{
    public RegularCinema(String cinemaCode, String cinemaClass, ArrayList<Integer> seatingPlan, ArrayList<String> sessionsID) {
		super(cinemaCode,cinemaClass,seatingPlan,sessionsID);
	}

    @Override
    public String getCinemaClass() {
        return "Regular";
    }
}
