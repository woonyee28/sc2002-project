package tests;

import models.Holiday;
import serializers.HolidaySerializer;

public class testHoliday {
    public static void main(String[] args) {
        Holiday holiday = new Holiday("National Aunts and Uncles Day","2022-07-26");
        HolidaySerializer.writeToHolidayCSV(holiday);
        for (Holiday m: HolidaySerializer.readFromHolidayCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
