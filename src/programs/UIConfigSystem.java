package programs;

import java.util.Scanner;

import managers.SettingsManager;
import models.*;
import serializers.*;

public class UIConfigSystem {
    private final String menuOptions[] = {
        "Print Holiday List",
        "Add New Holiday",
        "Update Holiday",
        "Delete Holiday",
        "Print Price List",
        "Edit Prices",
        "Exit System Config"
    };
    public static void main(String[] args) {
		UIConfigSystem app = new UIConfigSystem();
        app.run();
	}

    public void run(){
        int i,choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================ConfigureSystemSettings======================\n");
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
			choice = Integer.valueOf(sc.next());
			System.out.println();
            
			
            switch (choice) {
				case 1:
                    SettingsManager.printHolidayList();
                    // Print Holiday List
                    break;
				case 2:
					// Create Holiday
                    SettingsManager.addHoliday();
					break;
                case 3:
                    SettingsManager.updateHoliday();
                    // Update Holiday  
                    break;
                case 4:
                    SettingsManager.deleteHoliday();
                    // Delete Holiday
                    break;
                case 5:
                    SettingsManager.printPriceList();
                    // Print Price List
                    break;
                case 6:
                    SettingsManager.editPrices();
                    // Edit Prices
                    break;
                case 7:
                    System.out.println("Exiting to the previous level...");
                    break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 7);
    }
}
