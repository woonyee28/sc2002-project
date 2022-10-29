package tests;
import models.SettingsUI;

public class testSettingsUI {
    public static void main(String[] args){
        SettingsUI.printHolList();
        SettingsUI.createNewHoliday();
        SettingsUI.printHolList();
        SettingsUI.deleteHoliday();
        SettingsUI.printHolList();


    }
    
}
