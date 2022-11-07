package tests;
import java.text.ParseException;

import managers.*;
public class testBooking {
    public static void main(String [] args){
        while(true){
        MovieBookingManager mbm = new MovieBookingManager(1, 0);
        try {
            mbm.bookings(0);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
    }
    
}
