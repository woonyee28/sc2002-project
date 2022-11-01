package managers;

import serializers.*;
import models.*;
import java.util.*;

public class SettingsManager {  
    private ArrayList<Holiday> hList;
    private ArrayList<Price> pList;

    public SettingsManager(){
        this.hList=HolidaySerializer.readFromHolidayCSV();
        this.pList=PriceSerializer.readFromPriceCSV();
    }

    public ArrayList<Holiday> getHList(){
        return this.hList;

    }

    public ArrayList<Price> getPList(){
        return this.pList;

    }

    public boolean checkExistenceHol(String name){
        boolean exists =false;
        for (Holiday h : this.hList){
            if (h.getName().equals(name)){
                exists = true;
                break;
            }
        }
        return exists;
    }

    public boolean checkExistencePrice(String cat){
        boolean exists =false;
        for (Price p:this.pList){
            if(p.getCat().equals(cat)){
                exists =true;
                break;
            }
        }
        return exists;
    }

    public static void printHolidayList(){
        SettingsManager print =new SettingsManager();
        System.out.println("--------- Holiday list ---------");
        for (Holiday h:print.getHList()){
            h.toString();
            System.out.println(h);
        }
    }

    public static void addHoliday(){
        String name,date;
        SettingsManager add = new SettingsManager();
        Scanner input1 = new Scanner(System.in);
    
        System.out.println("--------- Add holiday ---------");
        System.out.println("Please enter name of holiday to be added:");
        name=input1.nextLine();
        if(!add.checkExistenceHol(name)){
            System.out.println("Please enter date (dd-mm-yyyy):");
            date=input1.nextLine();
            Holiday newH = new Holiday(name, date);
            HolidaySerializer.writeToHolidayCSV(newH);
            printHolidayList();
        }else System.out.println(name +" holiday not found!");
        ;

    }

   

    public static void updateHoliday(){
            String name,newDate;
            SettingsManager update = new SettingsManager();
            Scanner input1 = new Scanner(System.in);
    
            System.out.println("--------- Update holiday ---------");
            System.out.println("Please enter name of holiday to be updated:");
            name=input1.nextLine();
            if(update.checkExistenceHol(name)){
                System.out.println("Please enter new date (dd-mm-yyyy):");
                newDate=input1.nextLine();
                HolidaySerializer.updateHolidayFromCSV(name, newDate);
                printHolidayList();
            }else System.out.println(name +" holiday not found!");
            ;
    }

    public static void deleteHoliday(){
        String name;
        SettingsManager delete = new SettingsManager();
        Scanner input1 = new Scanner(System.in);

        System.out.println("--------- Delete holiday ---------");
        System.out.println("Please enter name of holiday to be deleted:");
        name=input1.nextLine();
        if(delete.checkExistenceHol(name)){
            HolidaySerializer.deleteHolidayFromCSV(name);
            printHolidayList();
        }else System.out.println(name +" holiday not found!");
        ;
    }

    public static void printPriceList(){
        SettingsManager print =new SettingsManager();
        System.out.println("--------- Price list ---------");
        for (Price p:print.getPList()){
            p.toString();
            System.out.println(p);
        }
    }

    public static void editPrices(){
        String cat;
        double newPrice;
        SettingsManager edit = new SettingsManager();
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        System.out.println("--------- Edit prices ---------");
        System.out.println("Please enter category to be edited:");
        cat=input1.nextLine();
        if(edit.checkExistencePrice(cat)){
            System.out.println("Please enter new price:");
            newPrice=input2.nextDouble();
            PriceSerializer.updatePriceFromCSV(cat, newPrice);
            printPriceList();
        }else System.out.println(cat +" type not found!");
        ;
        ;
    }
    

    
}

