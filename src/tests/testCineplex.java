package tests;

import java.util.ArrayList;
import models.Cineplexes;
import serializers.CineplexSerializer;

import models.Cineplexes;
import serializers.CineplexSerializer;

public class testCineplex {
    public static void main(String[] args) {
        CineplexSerializer cp = new CineplexSerializer();
        ArrayList<String> str = new ArrayList<String>();
        str.add("CCA");
        str.add("CCB");
        str.add("CCC");
        Cineplexes cineplexes = new Cineplexes("CC","Calypso",str);
        cp.writeToCSV(cineplexes);
        for (Cineplexes m: cp.readFromCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
