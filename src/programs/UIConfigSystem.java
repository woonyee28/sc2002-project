package programs;

import java.util.Scanner;

import managers.*;
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
                    printHolidayList();
                    // Print Holiday List
                    break;
				case 2:
					// Create Holiday
                    addHoliday();
					break;
                case 3:
                    updateHoliday();
                    // Update Holiday  
                    break;
                case 4:
                    deleteHoliday();
                    // Delete Holiday
                    break;
                case 5:
                    printPriceList();
                    // Print Price List
                    break;
                case 6:
                    editPrices();
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

    public void printHolidayList(){
        for (Holiday h:serializers.HolidaySerializer.readFromHolidayCSV()){
            h.toString();
            System.out.println(h);
        }

    }

    public void addHoliday(){
        String name = null,date =null;
        int flag=-1;
        Scanner sc =new Scanner(System.in).useDelimiter("\n");
        System.out.println("Adding Holiday...");
        System.out.println("Please enter name of Holiday:");
        name =sc.next();
    
        for (Holiday h: serializers.HolidaySerializer.readFromHolidayCSV()){
            if (h.getName().equals(name)){
                flag =1;
                break;
            }
        }

        if (flag!=1){
            System.out.println("Please enter date of new Holiday (dd-mm-yyy):");
            date =sc.next();
            Holiday newHol = new Holiday(name,date);
            HolidaySerializer.writeToHolidayCSV(newHol);
            System.out.println(name+ " Holiday succesfully created!");

        } else System.out.println(name +"Holiday already exists!");
    }

    public void updateHoliday(){
        String name=null,date=null;
        int flag =-1;
        Scanner sc =new Scanner(System.in).useDelimiter("\n");
        System.out.println("Updating Holiday...");
        System.out.println("Please enter name of Holiday to be updated:");
        name =sc.next();
        for (Holiday h: serializers.HolidaySerializer.readFromHolidayCSV()){
            if (h.getName().equals(name)){
                flag =1;
                break;
            }
        }

        if(flag==1){
            System.out.println("Please enter new date of Holiday (dd-mm-yyy):");
            date =sc.next();
            HolidaySerializer.updateHolidayFromCSV(name, date);
        }else System.out.println(name + " Holiday does not exists!");
    }

    public void deleteHoliday(){
        int flag=-1;
        String name = null;
        Scanner sc =new Scanner(System.in).useDelimiter("\n");
        System.out.println("Deleting Holiday...");
        System.out.println("Please enter name of Holiday to be deleted:");
        name =sc.next();
        for (Holiday h: serializers.HolidaySerializer.readFromHolidayCSV()){
            if (h.getName().equals(name)){
                flag =1;
                break;
            }
        }
        if(flag==1){
          HolidaySerializer.deleteHolidayFromCSV(name);
        }else System.out.println(name + " Holiday does not exists!");

    }

    public void printPriceList(){

    }
    
    public void editPrices(){

    }
}
