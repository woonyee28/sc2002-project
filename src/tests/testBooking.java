package tests;
import java.text.ParseException;

import managers.*;
public class testBooking {
    public static void main(String [] args){
        while(true){
        SettingsManager sm = new SettingsManager(1);
        MovieBookingManager mbm = new MovieBookingManager(1, 0);
        sm.printPriceList();
        sm.printHolidayList();
        //20221201 weekday
        //20221128 for holiday
        //20221126 weekend
            try {
                mbm.bookings(0);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
    
        }
    }
}
