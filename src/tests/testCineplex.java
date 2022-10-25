package tests;

import java.util.ArrayList;
import models.Cineplexes;
import serializers.CineplexSerializer;

import models.Cineplexes;
import serializers.CineplexSerializer;

public class testCineplex {
    public static void main(String[] args) {
        ArrayList<String> str = new ArrayList<String>();
        str.add("CCA");
        str.add("CCB");
        str.add("CCC");
        Cineplexes cineplexes = new Cineplexes("CC","Calypso",str);
        CineplexSerializer.writeToCineplexesCSV(cineplexes);
        for (Cineplexes m: CineplexSerializer.readFromCineplexesCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
