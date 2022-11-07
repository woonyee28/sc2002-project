package tests;
import java.text.ParseException;

import managers.*;
public class testHolPriceConfig {
    public static void main(String[] args){
        SettingsManager sm = new SettingsManager(1);
        sm.printPriceList();
        sm.printHolidayList();
        sm.addHoliday(); //20221128 for new holiday
        MovieBookingManager mbm = new MovieBookingManager(1, 0);
        try {
            mbm.bookings(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
