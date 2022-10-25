package tests;

import java.util.ArrayList;

import models.Cinemas;
import serializers.CinemaSerializer;

public class testCinema {
    public static void main(String[] args) {
        ArrayList<Integer> id = new ArrayList<Integer>();
        id.add(10);
        id.add(11);
        ArrayList<String> str = new ArrayList<String>();
        str.add("202211192000");
        Cinemas cinema = new Cinemas("AAA","Gold",id,str);
        CinemaSerializer.writeToCinemaCSV(cinema);
        for (Cinemas m: CinemaSerializer.readFromCinemaCSV()) {           
            m.toString();
            System.out.println(m); 
        }
        CinemaSerializer.deleteCinemasFromCSV("AAA");
        CinemaSerializer.updateCinemasFromCSV("AAA", "Gold", id, str);
    }
}
