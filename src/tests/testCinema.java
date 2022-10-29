package tests;

import java.util.ArrayList;

import models.Cinemas;
import serializers.CinemaSerializer;

public class testCinema {
    public static void main(String[] args) {
        ArrayList<Integer> id = new ArrayList<Integer>();
        // id.add(10);
        ArrayList<String> str = new ArrayList<String>();
        // str.add("202211192000");
        // Cinemas cinema = new Cinemas("AAA","Gold",id,str);
        // CinemaSerializer.writeToCinemaCSV(cinema);
        ArrayList<Cinemas> z = CinemaSerializer.readFromCinemaCSV();

        // System.out.println(z);
        for (Cinemas m: z) {           
            // m.toString();
            System.out.println(m); 
        
        }
    }
}

