package tests;

import models.Holiday;
import serializers.HolidaySerializer;

public class testHoliday {
    public static void main(String[] args) {
        HolidaySerializer hs = new HolidaySerializer();
        Holiday holiday = new Holiday("National Aunts and Uncles Day","2022-07-26");
        hs.writeToCSV(holiday);
        for (Holiday m: hs.readFromCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
