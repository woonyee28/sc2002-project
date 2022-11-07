package tests;
import managers.*;
public class testHolPriceConfig {
    public static void main(String[] args){
        SettingsManager sm = new SettingsManager(1);
        sm.editPrices();
        MovieBookingManager mbm = new MovieBookingManager(1, 0);

    }
}
